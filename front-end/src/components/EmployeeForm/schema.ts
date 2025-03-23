import * as z from "zod";

export const employeeStatus = ["PERMANENT", "CONTRACT", "CASUAL"] as const;

export const schema = z.object({
  firstName: z.string().min(1),
  middleName: z.string(),
  lastName: z.string().min(1),
  emailAddress: z.string().email(),
  mobileNumber: z.string().min(10),
  //startDate: z.coerce.date(),
  startDate: z.string().min(1),
  hoursPerWeek: z.coerce.number().min(15),
  employeeStatus: z.enum(employeeStatus),
  onGoing: z.coerce.boolean(),
});

export type EmployeeFormData = z.infer<typeof schema>;
