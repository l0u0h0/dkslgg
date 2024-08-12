"use client";
import DarkModeToggleButton from "./dark-mode-toggle-button";

export default function Header() {
  return (
    <header className="w-full min-w-fit sm:min-w-[640px] text-gray-600">
      <div className="mx-auto flex flex-wrap p-5 lg:px-32 md:px-20 flex-col md:flex-row justify-center items-center">
        <nav className="md:ml-auto flex flex-wrap items-center text-lg title-font justify-evenly">
          {/* 다크모드 토글버튼 */}
          <DarkModeToggleButton />
        </nav>
      </div>
    </header>
  );
}
