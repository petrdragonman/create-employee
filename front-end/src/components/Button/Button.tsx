import classes from "./Button.module.scss";

interface ButtonPartialProps {
  variant: "primary" | "success" | "danger";
}

type ButtonProps = ButtonPartialProps &
  React.DetailedHTMLProps<
    React.ButtonHTMLAttributes<HTMLButtonElement>,
    HTMLButtonElement
  >;

const Button = ({ variant, children, ...rest }: ButtonProps) => {
  const btnClasses = [classes.btn];
  if (variant) {
    btnClasses.push(classes[variant]);
  }
  return (
    <button {...rest} className={btnClasses.join(" ")}>
      {children}
    </button>
  );
};

export default Button;
