import { EmployeeFormData } from "../components/EmployeeForm/schema";
import { ValidationError } from "./error";

export interface Employee {
  emailAddress: string;
  firstName: string;
  hoursPerWeek: number;
  id: number;
  lastName: string;
  middleName: string | null; // can be a string or null
  mobileNumber: string;
  address: string;
  onGoing: boolean;
  startDate: string; // Date in YYYY-MM-DD format
  endDate: string | null;
  employeeStatus: EmployeeStatus; // Union type
  updatedAt: string; // ISO date string
}

type EmployeeStatus =
  | "PERMANENT_FULL_TIME"
  | "PERMANENT_PART_TIME"
  | "CONTRACT"
  | "CASUAL";

export const getAllEmployees = async () => {
  const response = await fetch("http://localhost:8080/employees");
  if (!response.ok) {
    throw new Error("Failed to fetch");
  }
  return (await response.json()) as Employee[];
};

export const getEmployeeById = async (id: number) => {
  const response = await fetch("http://localhost:8080/employees/" + id);
  if (!response.ok) {
    throw new Error("Employee noy found.");
  }
  return (await response.json()) as Employee;
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
    const errorData = await response.json();
    if (errorData.errors && typeof errorData.errors === "object") {
      throw new ValidationError(errorData);
    }
    throw new Error(errorData.message || "Failed create employee");
  }
  return (await response.json()) as Employee;
};

export const updateEmployeeById = async (
  id: number,
  data: EmployeeFormData
) => {
  const response = await fetch("http://localhost:8080/employees/" + id, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    throw new Error("Failed to update employee id: " + id);
  }
  return (await response.json()) as Employee;
};
