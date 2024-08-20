"use client";

import Image from "next/image";
import SearchSvg from "@/public/images/search.svg";
import { useRef } from "react";

export default function SearchBar({
  onSearch,
  placeholder,
}: {
  onSearch: (word: string | null) => void;
  placeholder: string;
}) {
  const ref = useRef<HTMLInputElement>(null);

  return (
    <div className="w-full relative flex self-center">
      <input
        className="w-[90%] min-w-[260px] h-9 p-[4%] pl-5 rounded-md shadow-lg focus:drop-shadow-2xl border-none transition-all duration-200"
        type="text"
        ref={ref}
        placeholder={placeholder}
      />
      <button
        className="relative w-7 right-9 hover:scale-110 transition-all duration-200"
        onClick={() => onSearch(ref && ref.current ? ref.current.value : null)}
      >
        <Image src={SearchSvg} alt="SearchBtn" />
      </button>
    </div>
  );
}
