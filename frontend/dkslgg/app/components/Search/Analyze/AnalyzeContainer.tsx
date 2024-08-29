"use client";

import ChampCardView from "./Layout/ChampCardView";
import FamousCardView from "./Layout/FamousCardView";
import GraphCardView from "./Layout/GraphCardView";
import LBTICardView from "./Layout/LBTICardView";

export default function AnaylzeContainer() {
  return (
    <div className="w-full px-[20%] py-16 bg-zinc-200 dark:bg-zinc-800">
      <div className="w-full min-w-[1024px] flex flex-col gap-4">
        <div className="w-full flex gap-4">
          <div className="basis-1/3">
            <LBTICardView />
          </div>
          <div className="basis-2/3">
            <GraphCardView />
          </div>
        </div>
        <ChampCardView />
        <FamousCardView />
      </div>
    </div>
  );
}
