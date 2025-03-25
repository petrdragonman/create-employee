import { useEffect } from "react";
import classes from "./Toast.module.scss";
import { FaCheck, FaExclamation, FaTimes } from "react-icons/fa";

export type ToastType = "error" | "success";

interface ToastProps {
  type: ToastType;
  message: string | null;
  onClose?: () => void;
  autoClose?: boolean;
  autoCloseDuration?: number;
}

const Toast = ({
  type,
  message,
  onClose,
  autoClose = true,
  autoCloseDuration = 1000,
}: ToastProps) => {
  useEffect(() => {
    if (autoClose && message) {
      const timer = setTimeout(() => {
        onClose?.();
      }, autoCloseDuration);
      return () => clearTimeout(timer);
    }
  }, [message, autoClose, autoCloseDuration, onClose]);
  if (!message) {
    return null;
  }

  const iconMap = {
    error: <FaExclamation />,
    success: <FaCheck />,
  };

  return (
    <div className={`${classes.alert} ${classes[type]}`}>
      <div className={classes.content}>
        <span className={classes.icon}>{iconMap[type]}</span>
        <span>{message}</span>
      </div>
      {onClose && (
        <button onClick={onClose} className={classes.closeBtn}>
          <FaTimes />
        </button>
      )}
    </div>
  );
};

export default Toast;
