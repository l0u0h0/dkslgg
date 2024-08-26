"use client";

import Image from "next/image";
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

const DDRAGON_VERSION = "14.16.1";

const DummyChampions = [
  {
    name: "Aatrox",
    win_rate: 0.56,
    kda: 2.88,
    cnt: 20,
  },
  {
    name: "Aatrox",
    win_rate: 0.56,
    kda: 2.88,
    cnt: 20,
  },
  {
    name: "Aatrox",
    win_rate: 0.56,
    kda: 2.88,
    cnt: 20,
  },
];

export default function RecentCardView() {
  const setRound = (num: number) => {
    return +(Math.round(Number(num + "e+2")) + "e-2");
  };

  return (
    <CardView title="&#128202; 최근 게임 분석" lbti={null}>
      <div className="w-full flex p-2 gap-4">
        <div aria-label="recent-graph" className="basis-[30%]">
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
        <div aria-label="recent-played" className="basis-[30%]">
          <p className="text-zinc-500 dark:text-zinc-400">플레이한 챔피언</p>
          <div className="w-full flex flex-col gap-4 pt-2">
            {DummyChampions.map((e, i) => (
              <div
                className="w-full flex gap-[.25rem] items-center text-xs"
                key={`Recent-Played-Champ-Row_${i}`}
              >
                <div className="relative w-6 h-6">
                  <Image
                    fill
                    className="object-cover rounded-full"
                    referrerPolicy="no-referrer"
                    alt="Recent Played Champ Image"
                    src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/champion/${e.name}.png`}
                  />
                </div>
                <p>승률 {Math.floor(e.win_rate * 100)}%</p>
                <p>KDA {setRound(e.kda)}</p>
                <p>{e.cnt}게임</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </CardView>
  );
}
