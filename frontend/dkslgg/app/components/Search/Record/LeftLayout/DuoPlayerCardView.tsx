"use client";

import CardView from "../../../Common/CardView";
import Link from "next/link";

const DummyDuoPlayer = [
  {
    name: "duoPlayer",
    games: 10,
    win: 5,
    lose: 5,
    odds: 50,
  },
  {
    name: "duoPlayer",
    games: 10,
    win: 5,
    lose: 5,
    odds: 50,
  },
  {
    name: "duoPlayer",
    games: 10,
    win: 5,
    lose: 5,
    odds: 50,
  },
  {
    name: "duoPlayer",
    games: 10,
    win: 5,
    lose: 5,
    odds: 50,
  },
];

export default function DuoPlayerCardView() {
  return (
    <CardView title="&#128080; 함께 한 소환사들">
      <div className="w-full px-6 py-6">
        <table className="w-full text-sm text-center border-collapse table-fixed">
          <thead className="w-full font-jalnangothic text-xs">
            <tr className="w-full border-b-2 border-zinc-700 dark:border-zinc-200">
              <td className="w-1/2 pb-2">소환사</td>
              <td className="pb-2">게임</td>
              <td className="pb-2">승-패</td>
              <td className="pb-2">승률</td>
            </tr>
          </thead>
          <tbody>
            {DummyDuoPlayer.map((e, i) => (
              <tr
                key={`duo-player_${i}`}
                className="leading-loose border-b-[1px] border-zinc-700 dark:border-zinc-200"
              >
                <td className="text-start pl-2 overflow-x-hidden">
                  <Link href={`/search/${e.name}`} target="_blank">
                    {e.name}
                  </Link>
                </td>
                <td>{e.games}</td>
                <td>
                  {e.win}-{e.lose}
                </td>
                <td>{e.odds}%</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </CardView>
  );
}
