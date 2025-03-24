import classes from "./EmployeesPage.module.scss";
import { useEffect } from "react";
import EmployeesList from "../components/EmployeesList";
import Button from "../components/Button/Button";
import { useSelector } from "react-redux";
import { RootState } from "../state/store";
import { fetchEmployees, removeEmployee } from "../state/employeeSlice";
import { useAppDispatch } from "../state/hooks";
import { useNavigate } from "react-router";

const EmployeesPage = () => {
  const navigate = useNavigate();
  const employees = useSelector(
    (state: RootState) => state.employees.employees
  );
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(fetchEmployees());
  }, [dispatch]);

  const handleDelete = async (id: number) => {
    dispatch(removeEmployee(id));
  };

  const handleClick = () => {
    navigate("/employees/new");
  };

  return (
    <div className={classes.container}>
      <article className={classes.title}>create employee app</article>
      <section className={classes.btn_create}>
        <Button variant="primary" onClick={handleClick}>
          Create new employee
        </Button>
      </section>
      <EmployeesList employees={employees} onDelete={handleDelete} />
    </div>
  );
};

export default EmployeesPage;
