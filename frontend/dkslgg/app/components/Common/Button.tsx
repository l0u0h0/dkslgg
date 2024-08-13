"use client";

export default function Button({ size, text }: { size: string; text: string }) {
  return <button className={`btn-modern text-${size}`}>{text}</button>;
}
