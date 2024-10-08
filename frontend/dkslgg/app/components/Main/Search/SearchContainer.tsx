"use client";

import { useRouter } from "next/navigation";
import SearchBar from "../../Common/SearchBar";
import { useMemo } from "react";

export default function SearchContainer() {
  const router = useRouter();
  const BG_URL = useMemo(() => {
    return `/images/bg/main_bg_${Math.floor(Math.random() * 10) + 1}.jpg`;
  }, []);

  const onSearch = (word: string | null) => {
    if (!word) {
      console.error("No Search Word!!");
    } else if (word.includes("/")) {
      console.error("Wrong Search Word!");
    } else {
      router.push("/");
    }
  };

  return (
    <section
      className="w-full min-h-[40rem] bg-cover bg-center flex flex-col justify-center items-center bg-gradient-conic"
      style={{
        backgroundImage: `url(${BG_URL})`,
      }}
    >
      <div className="w-fit h-fit m-10 font-bold grid place-items-center drop-shadow-xl">
        <h1 className="typing w-full pt-2 whitespace-nowrap overflow-hidden border-r-4 font-jalnangothic text-4xl text-zinc-50 tracking-wider">
          나의 전적을 분석해보세요.
        </h1>
      </div>
      <div className="w-2/5 max-w-[40rem]">
        <SearchBar
          onSearch={onSearch}
          placeholder="소환사 명을 입력해주세요."
        />
      </div>
    </section>
  );
}
