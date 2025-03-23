import { Employee } from "../../services/employeeServices";
import classes from "./EmployeeRow.module.scss";

interface EmployeeRowProps {
  employee: Employee;
  onDelete: (id: number) => void;
}

const EmployeeRow = ({ employee, onDelete }: EmployeeRowProps) => {
  const handleDelete = () => {
    onDelete(employee.id);
  };

  return (
    <main>
      <section className={classes.row}>
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
          <p className={classes.item}>{employee.status}</p>
        </article>
        <section className={classes.group_icons}>
          {/* <article className={classes.item_icon}>
            <img
              src="./info.svg"
              alt="trash bin icon"
              width={"8px"}
              className={classes.item}
            />
          </article> */}
          <article className={classes.item_icon}>
            <img
              src="./pen.svg"
              alt="trash bin icon"
              width={"18px"}
              className={classes.item}
            />
          </article>
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

/**
 * export interface Employee {
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
   status: EmployeeStatus; // Union type for status
   updatedAt: string; // ISO date string
 }
 */
