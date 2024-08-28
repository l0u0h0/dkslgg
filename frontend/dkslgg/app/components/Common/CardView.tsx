"use client";

interface ICardViewProps {
  title: string;
  lbti: string | null;
  children: React.ReactNode;
}

export default function CardView({ title, lbti, children }: ICardViewProps) {
  return (
    <div className="card-view w-full">
      <p className="card-title">{lbti ? `${lbti} ${title}` : title}</p>
      {children}
    </div>
  );
}
