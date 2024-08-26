"use client";

import RecordLeftLayout from "./LeftLayout/RecordLeftLayout";
import RecordRightLayout from "./RightLayout/RecordRightLayout";

export default function RecordContainer() {
  return (
    <div className="w-full px-[20%] py-16 bg-zinc-200 dark:bg-zinc-800">
      <div className="w-full min-w-[1024px] flex justify-end pb-2">
        <div className="w-[70%] flex gap-[.25rem] pl-4 items-center custom-radio font-semibold text-zinc-700 dark:text-zinc-200">
          <input type="radio" name="rank-type" defaultChecked />
          <label className="mr-2">랭크 전체</label>
          <input type="radio" name="rank-type" />
          <label>솔로 랭크</label>
        </div>
      </div>
      <div className="w-full min-w-[1024px] flex gap-4">
        <RecordLeftLayout />
        <RecordRightLayout />
      </div>
    </div>
  );
}
