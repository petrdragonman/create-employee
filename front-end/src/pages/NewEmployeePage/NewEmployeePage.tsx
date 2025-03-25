import { useSelector } from "react-redux";
import EmployeeForm from "../../components/EmployeeForm/EmployeeForm";
import { EmployeeFormData } from "../../components/EmployeeForm/schema";
import { createNewEmployee } from "../../state/employeeSlice";
import classes from "./NewEmployeePage.module.scss";
import { useNavigate } from "react-router";
import { RootState } from "../../state/store";
import { useAppDispatch } from "../../state/hooks";

const NewEmployeePage = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const { status, error } = useSelector((state: RootState) => state.employees);

  const onSubmit = (data: EmployeeFormData) => {
    dispatch(createNewEmployee(data))
      .unwrap()
      .then(() => {
        navigate("/employees");
      })
      .catch((error) => {
        console.error("Failed to create employee:", error);
      });
  };

  return (
    <>
      <section className={classes.container}>
        <article className={classes.title}>
          <p>Create a new employee</p>
        </article>
        <section className={classes.messages}>
          {status === "loading" && (
            <p className={classes.text}>Creating employee...</p>
          )}
          {status === "failed" && <p className={classes.error}>{error}</p>}
        </section>
        <EmployeeForm onSubmit={onSubmit} />
      </section>
    </>
  );
};

export default NewEmployeePage;
