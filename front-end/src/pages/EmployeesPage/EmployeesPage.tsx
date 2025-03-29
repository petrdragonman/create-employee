import classes from "./EmployeesPage.module.scss";
import { useEffect, useState } from "react";
import EmployeesList from "../../components/EmployeesList";
import { useSelector } from "react-redux";
import { RootState } from "../../state/store";
import {
  fetchEmployees,
  removeEmployee,
} from "../../state/employee/employeeSlice";
import { useAppDispatch } from "../../state/hooks";
import { useNavigate } from "react-router";
import Modal from "../../components/Modal/Modal";
import Button from "../../components/Button/Button";

const EmployeesPage = () => {
  const navigate = useNavigate();
  const employees = useSelector(
    (state: RootState) => state.employees.employees
  );
  const dispatch = useAppDispatch();
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [employeeToDelete, setEmployeeToDelete] = useState<number | null>(null);

  useEffect(() => {
    dispatch(fetchEmployees());
  }, [dispatch]);

  const handleDeleteClick = (id: number) => {
    setEmployeeToDelete(id);
    setShowDeleteModal(true);
  };

  const confirmDelete = async () => {
    if (employeeToDelete !== null) {
      await dispatch(removeEmployee(employeeToDelete));
      setShowDeleteModal(false);
      setEmployeeToDelete(null);
    }
  };

  const cancelDelete = () => {
    setShowDeleteModal(false);
    setEmployeeToDelete(null);
  };

  const handleCreateClick = () => {
    navigate("/employees/new");
  };

  const handleUpdateClick = (id: number) => {
    navigate(`/employees/${id}`);
  };

  return (
    <div className={classes.container}>
      <Modal
        isOpen={showDeleteModal}
        title="Confirm Deletion"
        message={`Are you sure you want to delete employee id: ${employeeToDelete}? This action can not be undone!`}
        onConfirm={confirmDelete}
        onCancel={cancelDelete}
        confirmText="Delete"
        cancelText="Cancel"
        confirmVariant="danger"
      />
      <article className={classes.title}>Create Employee App</article>
      {/* <section className={classes.btn_create}>
        <Button variant="primary" onClick={handleCreateClick}>
          Create Employee
        </Button>
      </section> */}
      <section className={classes.list}>
        <section className={classes.btn_create}>
          <Button variant="primary" onClick={handleCreateClick}>
            Create Employee
          </Button>
        </section>
        <section className={classes.list}>
          <EmployeesList
            employees={employees}
            onDelete={handleDeleteClick}
            onUpdate={handleUpdateClick}
          />
        </section>
      </section>
    </div>
  );
};

export default EmployeesPage;
