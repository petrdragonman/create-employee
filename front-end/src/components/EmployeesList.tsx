import { Employee } from "../services/employeeServices";
import EmployeeRow from "./EmployeeRow/EmployeeRow";

interface EmployeesListProps {
  employees: Employee[];
}

const EmployeesList = ({ employees }: EmployeesListProps) => {
  if (employees.length === 0) {
    return null;
  }
  return (
    <>
      {employees.map((employee) => (
        <EmployeeRow key={employee.id} employee={employee} />
      ))}
    </>
  );
};

export default EmployeesList;
