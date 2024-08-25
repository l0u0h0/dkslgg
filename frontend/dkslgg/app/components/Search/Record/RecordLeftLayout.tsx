"use client";

import CardView from "../../Common/CardView";
import Image from "next/image";
import DummyRank from "@/public/images/rank-icons/master.png";
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

export default function RecordLeftLayout() {
  return (
    <div className="w-[30%] min-w-80 drop-shadow flex flex-col gap-8">
      <CardView title="&#127941; 티어" lbti={null}>
        <div className="w-full px-12 py-6 flex flex-col gap-4">
          <div>
            <p className="font-semibold">솔로 랭크</p>
            <div className="flex items-center gap-4">
              <div className="relative w-24">
                <Image
                  referrerPolicy="no-referrer"
                  alt="rank-icon"
                  src={DummyRank}
                />
              </div>
              <p className="text-xl font-jalnangothic tracking-wider">Matser</p>
            </div>
          </div>
          <div>
            <p className="font-semibold">자유 랭크</p>
            <div className="flex items-center gap-4">
              <div className="relative w-24">
                <Image
                  referrerPolicy="no-referrer"
                  alt="rank-icon"
                  src={DummyRank}
                />
              </div>
              <p className="text-xl font-jalnangothic tracking-wider">Matser</p>
            </div>
          </div>
          <div>
            <p className="font-semibold">전략적 팀 전투</p>
            <div className="flex items-center gap-4">
              <div className="relative w-24">
                <Image
                  referrerPolicy="no-referrer"
                  alt="rank-icon"
                  src={DummyRank}
                />
              </div>
              <p className="text-xl font-jalnangothic tracking-wider">Matser</p>
            </div>
          </div>
        </div>
      </CardView>
      <CardView title="&#128080; 함께 한 소환사들" lbti={null}>
        <div className="w-full px-6 py-6">
          <table className="w-full text-sm text-center border-collapse table-fixed">
            <thead className="w-full font-jalnangothic">
              <tr className="w-full border-b-2 border-zinc-700 dark:border-zinc-200">
                <td className="w-1/2 pb-2">소환사</td>
                <td className="pb-2">게임</td>
                <td className="pb-2">승-패</td>
                <td className="pb-2">승률</td>
              </tr>
            </thead>
            <tbody>
              {DummyDuoPlayer.map((e, i) => (
                <tr className="leading-loose border-b-[1px] border-zinc-700 dark:border-zinc-200">
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
    </div>
  );
}
