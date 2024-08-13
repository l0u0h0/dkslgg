"use client";
import Link from "next/link";
import LogoSvg from "@/public/images/dkslhead.svg";
import DarkModeToggleButton from "./dark-mode-toggle-button";
import Image from "next/image";
import Button from "../Common/Button";

export default function Header() {
  return (
    <header className="w-full min-w-fit sm:min-w-[640px] max-h-28 text-gray-600 flex flex-wrap p-5 lg:px-32 md:px-20 flex-col md:flex-row justify-center items-center">
      <div className="flex h-full basis-1/5 justify-center">
        <Link
          className="relative flex w-12 h-full hover:scale-105 border-0"
          href="/"
          rel="noreferrer"
        >
          <Image src={LogoSvg} alt="dkslgg Logo" sizes="10rem" />
        </Link>
      </div>
      <nav className="basis-1/2 font-jalnangothic text-xl">
        <Link href="/group" rel="noreferrer">
          소속
        </Link>
      </nav>
      <nav className="basis-1/4 md:ml-auto flex flex-wrap gap-4 items-center justify-center">
        <Button size="base" text="로그인" />
        <DarkModeToggleButton />
      </nav>
    </header>
  );
}
