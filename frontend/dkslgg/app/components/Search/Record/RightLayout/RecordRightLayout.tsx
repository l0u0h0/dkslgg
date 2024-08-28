"use client";

import RecentCardView from "./RecentCardView";
import RecordCard from "./RecordCard";

export default function RecordRightLayout() {
  return (
    <div className="w-3/4 flex flex-col gap-4">
      <RecentCardView />
      <RecordCard />
    </div>
  );
}
