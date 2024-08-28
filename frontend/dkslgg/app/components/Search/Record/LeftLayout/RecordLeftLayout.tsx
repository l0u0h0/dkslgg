"use client";

import DuoPlayerCardView from "./DuoPlayerCardView";
import TierCardView from "./TierCardView";

export default function RecordLeftLayout() {
  return (
    <div className="w-1/4 drop-shadow flex flex-col gap-4">
      <TierCardView />
      <DuoPlayerCardView />
    </div>
  );
}
