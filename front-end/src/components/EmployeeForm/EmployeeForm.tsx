import { useForm } from "react-hook-form";
import { EmployeeFormData, employeeStatus, schema } from "./schema";
import { capitaliseEachWord } from "./utils";
import Button from "../Button/Button";
import classes from "./EmployeeForm.module.scss";
import { zodResolver } from "@hookform/resolvers/zod";
import { useNavigate } from "react-router";

interface EmployeeFormProps {
  onSubmit: (data: EmployeeFormData) => unknown;
  defaultValues?: EmployeeFormData;
  mode?: "create" | "update";
}

const EmployeeForm = ({
  onSubmit,
  defaultValues,
  mode = "create",
}: EmployeeFormProps) => {
  const navigate = useNavigate();
  const {
    handleSubmit,
    register,
    formState: { isSubmitSuccessful, errors },
    reset,
  } = useForm<EmployeeFormData>({
    resolver: zodResolver(schema),
    defaultValues,
  });

  //isSubmitSuccessful && reset();

  const handleCancelClick = () => {
    navigate("/employees");
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className={classes.form}>
      <article className={classes.field}>
        <label>First Name</label>
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
        <label>Middle Name</label>
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
        <label>Last Name</label>
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
        <label>Email Address</label>
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
        <label>Mobile</label>
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
        <label>Address</label>
        <input type="text" {...register("address")} className={classes.input} />
        {errors?.address && (
          <small className={classes.error_text}>
            {errors?.address?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>Start Date</label>
        <input
          type="date"
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
        <label>End Date</label>
        <input type="date" {...register("endDate")} className={classes.input} />
        {errors?.endDate && (
          <small className={classes.error_text}>
            {errors?.endDate?.message}
          </small>
        )}
      </article>
      <article className={classes.field}>
        <label>Hours per Week</label>
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
        <label>Employment Status</label>
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
      {/* <article className={classes.field}>
        <label className={classes.custom_checkbox}>On Going</label>
        <input
          type="checkbox"
          {...register("onGoing")}
          className={classes.checkmark}
        />
        {errors?.onGoing && (
          <small className={classes.error_text}>{errors.onGoing.message}</small>
        )}
      </article> */}
      <section className={classes.btn_container}>
        <article>
          <Button variant={"secondary"} onClick={handleCancelClick}>
            Cancel
          </Button>
        </article>
        <article>
          <Button variant={"primary"}>
            {mode === "update" ? "Update Employee" : "Create New Employee"}
          </Button>
        </article>
      </section>
    </form>
  );
};

export default EmployeeForm;
