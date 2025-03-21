import classes from "./EmployeesPage.module.scss";
import { useEffect, useState } from "react";
import { Employee, getAllEmployees } from "../services/employeeServices";
import EmployeesList from "../components/EmployeesList";
import Button from "../components/Button/Button";

const EmployeesPage = () => {
  const [employees, setEmployees] = useState<Employee[]>([]);

  useEffect(() => {
    getAllEmployees()
      .then((employees) => setEmployees(employees))
      .catch((e) => console.log(e));
  }, []);

  return (
    <div className={classes.container}>
      <article className={classes.title}>create_employee_app</article>
      <section className={classes.btn_create}>
        <Button variant="primary">Create new employee</Button>
      </section>
      <EmployeesList employees={employees} />
    </div>
  );
};

export default EmployeesPage;
