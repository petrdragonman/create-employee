import * as z from "zod";

export const employeeStatus = ["PERMANENT", "CONTRACT", "CASUAL"] as const;

export const schema = z.object({
  firstName: z.string().min(1, { message: "First name is required" }),
  middleName: z.string().optional(), // optional
  lastName: z.string().min(1, { message: "Last name is required" }),
  emailAddress: z
    .string()
    .min(1, { message: "Email is required" })
    .email({ message: "Invalid email address" }),
  mobileNumber: z
    .string()
    .min(10, { message: "Mobile number must be at least 10 digits" }),
  startDate: z
    .string()
    .regex(/^\d{4}-\d{2}-\d{2}$/, {
      message: "Start date must be in YYYY-MM-DD format",
    })
    .refine((date) => new Date(date) >= new Date("1900-01-01"), {
      message: "Start date must be after 1900-01-01",
    }),
  // startDate: z.coerce
  //   .date()
  //   .min(new Date("1900-01-01"), { message: "Invalid start date" }),
  hoursPerWeek: z.coerce
    .number()
    .min(15, { message: "Hours per week must be at least 15" }),
  employeeStatus: z.enum(employeeStatus, {
    errorMap: () => ({ message: "Invalid employment status" }),
  }),
  onGoing: z.coerce.boolean(),
});

export type EmployeeFormData = z.infer<typeof schema>;
