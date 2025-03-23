import { useForm } from "react-hook-form";
import { EmployeeFormData, employeeStatus, schema } from "./schema";
import { capitaliseEachWord } from "./utils";
import Button from "../Button/Button";
import classes from "./EmployeeForm.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";

interface EmployeeFormProps {
  onSubmit: (data: EmployeeFormData) => unknown;
}

const EmployeeForm = ({ onSubmit }: EmployeeFormProps) => {
  const {
    handleSubmit,
    register,
    formState: { isSubmitSuccessful, errors },
    reset,
  } = useForm<EmployeeFormData>({ resolver: zodResolver(schema) });
  isSubmitSuccessful && reset();

  return (
    <form onSubmit={handleSubmit(onSubmit)} className={classes.form}>
      <article className={classes.field}>
        <label>first name</label>
        <input
          type="text"
          {...register("firstName")}
          className={classes.input}
        />
        {errors?.firstName && (
          <small className={classes.error_text}>
            {errors?.firstName?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>middle name</label>
        <input
          type="text"
          {...register("middleName")}
          className={classes.input}
        />
        {errors?.middleName && (
          <small className={classes.error_text}>
            {errors?.middleName?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>last name</label>
        <input
          type="text"
          {...register("lastName")}
          className={classes.input}
        />
        {errors?.lastName && (
          <small className={classes.error_text}>
            {errors?.lastName?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>email address</label>
        <input
          type="text"
          {...register("emailAddress")}
          className={classes.input}
        />
        {errors?.emailAddress && (
          <small className={classes.error_text}>
            {errors?.emailAddress?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>mobile</label>
        <input
          type="text"
          {...register("mobileNumber")}
          className={classes.input}
        />
        {errors?.mobileNumber && (
          <small className={classes.error_text}>
            {errors?.mobileNumber?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>start date</label>
        <input
          type="text"
          {...register("startDate")}
          className={classes.input}
        />
        {errors?.startDate && (
          <small className={classes.error_text}>
            {errors?.startDate?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>hours per week</label>
        <input
          type="number"
          {...register("hoursPerWeek")}
          className={classes.input}
        />
        {errors?.hoursPerWeek && (
          <small className={classes.error_text}>
            {errors?.hoursPerWeek?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>employment status</label>
        <select {...register("employeeStatus")} className={classes.input}>
          {employeeStatus.map((status) => (
            <option key={status} value={status}>
              {capitaliseEachWord(status)}
            </option>
          ))}
        </select>
        {errors?.employeeStatus && (
          <small className={classes.error_text}>
            {errors?.employeeStatus?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>on going</label>
        <input type="text" {...register("onGoing")} className={classes.input} />
        {errors?.onGoing && (
          <small className={classes.error_text}>
            {errors?.onGoing?.message}
          </small>
        )}
      </article>
      <article>
        <Button variant={"primary"}>Create New Employee</Button>
      </article>
    </form>
  );
};

export default EmployeeForm;
