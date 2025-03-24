import { Employee } from "../services/employeeServices";
import EmployeeRow from "./EmployeeRow/EmployeeRow";

interface EmployeesListProps {
  employees: Employee[];
  onDelete: (id: number) => void;
}

const EmployeesList = ({ employees, onDelete }: EmployeesListProps) => {
  if (employees.length === 0) {
    return null;
  }
  return (
    <>
      {employees.map((employee) => (
        <EmployeeRow
          key={employee.id}
          employee={employee}
          onDelete={onDelete}
        />
      ))}
    </>
  );
};

export default EmployeesList;
