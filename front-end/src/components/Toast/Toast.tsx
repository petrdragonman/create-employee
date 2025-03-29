import { useEffect } from "react";
import classes from "./Toast.module.scss";
import { FaCheck, FaExclamation, FaTimes } from "react-icons/fa";
import { useAppDispatch, useAppSelector } from "../../state/hooks";
import { hideToast } from "../../state/notification/toastSlice";

const Toast = () => {
  const dispatch = useAppDispatch();
  const { message, type, isVisible } = useAppSelector((state) => state.toast);

  console.log("IN THE TOAST>>>>");
  useEffect(() => {
    if (isVisible) {
      const timer = setTimeout(() => {
        dispatch(hideToast());
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [isVisible, dispatch]);

  if (!isVisible || !message || !type) return null;

  const iconMap = {
    error: <FaExclamation />,
    success: <FaCheck />,
  };

  return (
    <div
      className={`${classes.alert} ${classes[type]} ${classes["top-right"]}`}
    >
      <div className={classes.content}>
        <span className={classes.icon}>{iconMap[type]}</span>
        <span>{message}</span>
      </div>
      <button
        onClick={() => dispatch(hideToast())}
        className={classes.closeBtn}
      >
        <FaTimes />
      </button>
    </div>
  );
};

export default Toast;
