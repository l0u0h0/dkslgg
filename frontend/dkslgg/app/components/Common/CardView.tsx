"use client";

interface ICardViewProps {
  title: string;
  children: React.ReactNode;
}

export default function CardView({ title, children }: ICardViewProps) {
  return (
    <div className="card-view w-full">
      <p className="card-title">{title}</p>
      {children}
    </div>
  );
}
