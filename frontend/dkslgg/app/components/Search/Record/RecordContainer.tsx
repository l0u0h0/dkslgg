"use client";

import RecordLeftLayout from "./RecordLeftLayout";
import RecordRightLayout from "./RecordRightLayout";

export default function RecordContainer() {
  return (
    <div className="w-full flex px-[20%] py-16 bg-zinc-200 dark:bg-zinc-700">
      <RecordLeftLayout />
      <RecordRightLayout />
    </div>
  );
}
