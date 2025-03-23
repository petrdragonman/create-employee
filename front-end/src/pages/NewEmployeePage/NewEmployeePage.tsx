import EmployeeForm from "../../components/EmployeeForm/EmployeeForm";
import { EmployeeFormData } from "../../components/EmployeeForm/schema";
import classes from "./NewEmployeePage.module.scss";

const NewEmployeePage = () => {
  const onSubmit = (data: EmployeeFormData) => {
    console.log(data);
  };

  return (
    <>
      <section className={classes.container}>
        <article className={classes.title}>
          <p>Create a new employee</p>
        </article>

        <EmployeeForm onSubmit={onSubmit} />
      </section>
    </>
  );
};

export default NewEmployeePage;
