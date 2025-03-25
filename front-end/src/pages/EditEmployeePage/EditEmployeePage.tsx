import { useNavigate, useParams } from "react-router";
import { useAppDispatch } from "../../state/hooks";
import { useSelector } from "react-redux";
import { RootState } from "../../state/store";
import { EmployeeFormData } from "../../components/EmployeeForm/schema";
import classes from "./EditEmployeePage.module.scss";
import { fetchEmployeeById, updateEmployee } from "../../state/employeeSlice";
import EmployeeForm from "../../components/EmployeeForm/EmployeeForm";
import { useEffect } from "react";

const EditEmployeePage = () => {
  const { id } = useParams<{ id: string }>();
  const employeeId = parseInt(id || "0");
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const { status, error } = useSelector((state: RootState) => state.employees);
  const currentEmployee = useSelector((state: RootState) =>
    state.employees.employees.find((e) => e.id === employeeId)
  );

  useEffect(() => {
    if (employeeId && !currentEmployee) {
      dispatch(fetchEmployeeById(employeeId));
    }
  }, [employeeId, dispatch, currentEmployee]);

  const onSubmit = (data: EmployeeFormData) => {
    dispatch(updateEmployee({ id: employeeId, data }))
      .unwrap()
      .then(() => {
        navigate("/employees");
      })
      .catch((error) => {
        console.error("Failed to create employee:", error);
      });
  };

  if (status === "loading" && !currentEmployee) {
    return <p>Loading employee data...</p>;
  }

  if (!currentEmployee) {
    return <p className={classes.error}>Employee not found</p>;
  }

  // Transform employee data to match form schema
  const defaultValues: EmployeeFormData = {
    firstName: currentEmployee.firstName,
    middleName: currentEmployee.middleName || "", // optional field
    lastName: currentEmployee.lastName,
    emailAddress: currentEmployee.emailAddress,
    mobileNumber: currentEmployee.mobileNumber,
    startDate: currentEmployee.startDate.split("T")[0], // Format date
    hoursPerWeek: currentEmployee.hoursPerWeek,
    employeeStatus: currentEmployee.employeeStatus,
    onGoing: currentEmployee.onGoing,
  };

  return (
    <>
      <section className={classes.container}>
        <article className={classes.title}>
          <p>Update an employee: {currentEmployee.id}</p>
        </article>
        <section className={classes.messages}>
          {status === "loading" && <p>Updating employee...</p>}
          {status === "failed" && <p className={classes.error}>{error}</p>}
        </section>
        <EmployeeForm
          onSubmit={onSubmit}
          mode="update"
          defaultValues={defaultValues}
        />
      </section>
    </>
  );
};

export default EditEmployeePage;
