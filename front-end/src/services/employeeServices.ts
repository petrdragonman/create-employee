import { EmployeeFormData } from "../components/EmployeeForm/schema";

export interface Employee {
  createdAt: string; // ISO date string
  emailAddress: string;
  firstName: string;
  hoursPerWeek: number;
  id: number;
  lastName: string;
  middleName: string | null; // middleName can be a string or null
  mobileNumber: string;
  onGoing: boolean;
  startDate: string; // Date in YYYY-MM-DD format
  employeeStatus: EmployeeStatus; // Union type for status
  updatedAt: string; // ISO date string
}

type EmployeeStatus = "PERMANENT" | "CONTRACT" | "CASUAL";

export const getAllEmployees = async () => {
  const response = await fetch("http://localhost:8080/employees");
  if (!response.ok) {
    throw new Error("Failed to fetch");
  }
  return (await response.json()) as Employee[];
};

export const deleteEmployee = async (id: number) => {
  try {
    const response = await fetch("http://localhost:8080/employees/" + id, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("failed delete employee");
    }
  } catch (error) {
    console.error("Error deleting employee: ", error);
  }
};

export const createEmployee = async (data: EmployeeFormData) => {
  const response = await fetch("http://localhost:8080/employees", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    throw new Error("failed to create employee");
  }
  return (await response.json()) as Employee;
};
