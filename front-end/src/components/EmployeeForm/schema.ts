import * as z from "zod";

export const employeeStatus = [
  "PERMANENT_FULL_TIME",
  "PERMANENT_PART_TIME",
  "CONTRACT",
  "CASUAL",
] as const;

export const schema = z
  .object({
    firstName: z.string().min(1, { message: "First name is required" }),
    middleName: z.string().optional(),
    lastName: z.string().min(1, { message: "Last name is required" }),
    emailAddress: z
      .string()
      .min(1, { message: "Email is required" })
      .email({ message: "Invalid email address" }),
    mobileNumber: z
      .string()
      .min(10, { message: "Mobile number must be at least 10 digits" }),
    address: z.string().min(10, { message: "Address is required" }),
    startDate: z
      .string()
      .regex(/^\d{4}-\d{2}-\d{2}$/, {
        message: "Start date must be in YYYY-MM-DD format",
      })
      .refine((date) => new Date(date) >= new Date("1900-01-01"), {
        message: "Start date must be after 1900-01-01",
      }),
    endDate: z
      .string()
      .optional()
      .refine((val) => !val || /^\d{4}-\d{2}-\d{2}$/.test(val), {
        message: "End date must be in YYYY-MM-DD format",
      })
      .refine((val) => !val || new Date(val!) >= new Date("1900-01-01"), {
        message: "End date must be after 1900-01-01",
      }),
    hoursPerWeek: z.coerce
      .number()
      .min(1, { message: "Hours per week must be between 1 and 40." })
      .max(40, { message: "Hours per week must be between 1 and 40." }),
    employeeStatus: z.enum(employeeStatus, {
      errorMap: () => ({ message: "Invalid employment status" }),
    }),
  })
  .refine(
    (data) =>
      !data.endDate || new Date(data.endDate) >= new Date(data.startDate),
    {
      message: "End date must be after start date",
      path: ["endDate"],
    }
  );

export type EmployeeFormData = z.infer<typeof schema>;
