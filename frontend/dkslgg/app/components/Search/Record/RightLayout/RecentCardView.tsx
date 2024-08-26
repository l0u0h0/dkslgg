"use client";

import CardView from "../../../Common/CardView";
import { ResponsivePie } from "@nivo/pie";

const DummyPie = [
  {
    id: "승리",
    label: "승리",
    value: 11,
  },
  {
    id: "패배",
    label: "패배",
    value: 9,
  },
];

export default function RecentCardView() {
  return (
    <CardView title="&#128202; 최근 게임 분석" lbti={null}>
      <div className="w-full flex p-2">
        <div className="basis-[30%]">
          <p className="ml-4 text-start text-zinc-500 dark:text-zinc-400">
            20전 11승 9패
          </p>
          <div className="w-full h-32 relative flex gap-2 items-center">
            <ResponsivePie
              data={DummyPie}
              margin={{ top: 5, right: 5, bottom: 5, left: 10 }}
              innerRadius={0.75}
              padAngle={0.7}
              cornerRadius={1}
              colors={["#5393CA", "#ff5858"]}
              activeOuterRadiusOffset={3}
              borderWidth={1}
              borderColor={{
                from: "color",
                modifiers: [["darker", 0.2]],
              }}
              enableArcLinkLabels={false}
              enableArcLabels={false}
            />
            <div className="basis-1/2 flex flex-col gap-2 text-center">
              <div className="inline-flex text-base tracking-wide">
                <p className="text-zinc-600 dark:text-zinc-300">5.7</p>/
                <p className="text-red-500 dark:text-red-400">5.4</p>/
                <p className="text-zinc-600 dark:text-zinc-300">6.4</p>
              </div>
              <p className="text-xl font-semibold">2.24:1</p>
              <p>킬관여 51%</p>
            </div>
          </div>
        </div>
      </div>
    </CardView>
  );
}
