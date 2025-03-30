import { Employee } from "../../services/employeeServices";
import classes from "./EmployeeRow.module.scss";

interface EmployeeRowProps {
  employee: Employee;
  onDelete: (id: number) => void;
  onUpdate: (id: number) => void;
}

const EmployeeRow = ({ employee, onDelete, onUpdate }: EmployeeRowProps) => {
  const handleDelete = (e: { stopPropagation: () => void }) => {
    e.stopPropagation();
    onDelete(employee.id);
  };
  const handleUpdate = () => {
    onUpdate(employee.id);
  };

  return (
    <main>
      <section
        className={`${classes.row} ${classes[employee.employeeStatus]}`}
        onClick={handleUpdate}
      >
        <section className={classes.item_id}>
          <p className={classes.item}>{employee.id}</p>
        </section>
        <section className={classes.group_name}>
          <p className={classes.item}>{employee.firstName}</p>
          <p className={classes.item}>{employee.middleName}</p>
          <p className={classes.item}>{employee.lastName}</p>
        </section>
        <article className={classes.item_mobile}>
          <p className={classes.item}>{employee.mobileNumber}</p>
        </article>
        <article className={classes.item_email}>
          <p className={classes.item}>{employee.emailAddress}</p>
        </article>
        <article className={classes.item_status}>
          <p className={classes.item}>{employee.employeeStatus}</p>
        </article>
        <article className={classes.item_hours}>
          <p className={classes.item}>{employee.hoursPerWeek} Hours per Week</p>
        </article>
        <section className={classes.group_icons}>
          <article className={classes.item_icon}>
            <img
              src="./bin-red.svg"
              alt="trash bin icon"
              width={"14px"}
              className={classes.item}
              onClick={handleDelete}
            />
          </article>
        </section>
      </section>
    </main>
  );
};

export default EmployeeRow;
