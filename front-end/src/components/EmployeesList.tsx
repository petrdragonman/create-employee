import { Employee } from "../services/employeeServices";
import EmployeeRow from "./EmployeeRow/EmployeeRow";

interface EmployeesListProps {
  employees: Employee[];
  onDelete: (id: number) => void;
  onUpdate: (id: number) => void;
}

const EmployeesList = ({
  employees,
  onDelete,
  onUpdate,
}: EmployeesListProps) => {
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
          onUpdate={onUpdate}
        />
      ))}
    </>
  );
};

export default EmployeesList;
