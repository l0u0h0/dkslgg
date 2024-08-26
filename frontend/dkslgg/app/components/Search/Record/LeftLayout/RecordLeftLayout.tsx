"use client";

import DuoPlayerCardView from "./DuoPlayerCardView";
import TierCardView from "./TierCardView";

export default function RecordLeftLayout() {
  return (
    <div className="w-[30%] drop-shadow flex flex-col gap-8">
      <TierCardView />
      <DuoPlayerCardView />
    </div>
  );
}
