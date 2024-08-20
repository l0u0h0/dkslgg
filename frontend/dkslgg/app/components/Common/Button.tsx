"use client";

interface IButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  size: string;
  children: React.ReactNode;
}

export default function Button({ size, children, ...rest }: IButtonProps) {
  return (
    <button className={`btn-modern text-${size}`} {...rest}>
      {children}
    </button>
  );
}
