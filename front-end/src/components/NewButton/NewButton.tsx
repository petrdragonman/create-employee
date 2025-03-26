import classes from "./NewButton.module.scss";

type ButtonVariant = "primary" | "secondary" | "danger" | "success" | "text";
type ButtonSize = "small" | "medium" | "large";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: ButtonVariant;
  size?: ButtonSize;
  isLoading?: boolean;
  fullWidth?: boolean;
  startIcon?: React.ReactNode;
  endIcon?: React.ReactNode;
}

const Button: React.FC<ButtonProps> = ({
  children,
  variant = "primary",
  size = "medium",
  isLoading = false,
  fullWidth = false,
  startIcon,
  endIcon,
  className = "",
  disabled,
  ...props
}) => {
  return (
    <button
      className={`btn btn--${variant} btn--${size} ${
        fullWidth ? "btn--full-width" : ""
      } ${isLoading ? "btn--loading" : ""} ${className}`}
      disabled={disabled || isLoading}
      {...props}
    >
      {isLoading && <span className="btn__spinner" />}
      {startIcon && !isLoading && (
        <span className="btn__icon btn__icon--start">{startIcon}</span>
      )}
      <span className="btn__content">{children}</span>
      {endIcon && !isLoading && (
        <span className="btn__icon btn__icon--end">{endIcon}</span>
      )}
    </button>
  );
};

export default Button;
