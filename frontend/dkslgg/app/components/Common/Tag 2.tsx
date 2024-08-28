"use client";

interface ITagProps {
  text: string;
  color: string;
}

const TAG_THEME: { [key: string]: string } = {
  red: "bg-red-500",
  cyan: "bg-cyan-500",
  yellow: "bg-yellow-500",
  blue: "bg-blue-500",
};

export default function Tag({ text, color }: ITagProps) {
  return (
    <div
      className={`w-fit text-zinc-200 border-0 py-2 px-4 rounded-lg text-base font-jalnan tracking-wider ${TAG_THEME[color]}`}
    >
      {text}
    </div>
  );
}
