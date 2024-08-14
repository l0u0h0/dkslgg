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
    <div>
      <input type="text" ref={ref} placeholder={placeholder} />
      <button
        onClick={() => onSearch(ref && ref.current ? ref.current.value : null)}
      >
        <Image src={SearchSvg} alt="SearchBtn" />
      </button>
    </div>
  );
}
