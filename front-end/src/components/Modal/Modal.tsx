import classes from "./Modal.module.scss";

interface ModalProps {
  isOpen: boolean;
  title: string;
  message: string;
  onConfirm: () => void;
  onCancel: () => void;
  confirmText?: string;
  cancelText?: string;
  confirmVariant?: "primary" | "danger" | "secondary";
}

const Modal = ({
  isOpen,
  title,
  message,
  onConfirm,
  onCancel,
  confirmText = "Confirm",
  cancelText = "Cancel",
  confirmVariant = "danger",
}: ModalProps) => {
  if (!isOpen) return null;

  return (
    <div className={classes.modalOverlay}>
      <div className={classes.modal}>
        <div className={classes.modalHeader}>
          <h3>{title}</h3>
        </div>
        <div className={classes.modalBody}>
          <p>{message}</p>
        </div>
        <div className={classes.modalFooter}>
          <button
            type="button"
            className={`${classes.button} ${classes.secondary}`}
            onClick={onCancel}
          >
            {cancelText}
          </button>
          <button
            type="button"
            className={`${classes.button} ${classes[confirmVariant]}`}
            onClick={onConfirm}
          >
            {confirmText}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Modal;
