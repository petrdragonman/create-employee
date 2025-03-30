import { useEffect } from "react";
import classes from "./Toast.module.scss";
import { FaCheck, FaExclamation, FaTimes } from "react-icons/fa";
import { useAppDispatch, useAppSelector } from "../../state/hooks";
import { hideToast } from "../../state/notification/toastSlice";

const Toast = () => {
  const dispatch = useAppDispatch();
  const { message, type, isVisible } = useAppSelector((state) => state.toast);
  let timer: number | undefined;

  useEffect(() => {
    if (isVisible && type === "success") {
      timer = window.setTimeout(() => {
        dispatch(hideToast());
      }, 3000);
    }

    return () => {
      if (timer) {
        window.clearTimeout(timer);
      }
    };
  }, [isVisible, type, dispatch]);

  if (!isVisible || !message || !type) return null;

  return (
    <div
      className={`${classes.alert} ${classes[type]} ${classes["top-right"]}`}
    >
      <div className={classes.content}>
        <span className={classes.icon}>
          {type === "success" ? <FaCheck /> : <FaExclamation />}
        </span>
        <span className={classes.message}>{message}</span>
      </div>
      <button
        onClick={() => {
          if (timer) window.clearTimeout(timer);
          dispatch(hideToast());
        }}
        className={classes.closeBtn}
        aria-label="Close notification"
      >
        <FaTimes />
      </button>
    </div>
  );
};

export default Toast;
