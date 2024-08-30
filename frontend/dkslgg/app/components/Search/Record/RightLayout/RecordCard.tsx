"use client";

import Image from "next/image";
import Link from "next/link";
import { useState } from "react";
import RecordDetailCard from "./RecordDetailCard";

const DDRAGON_VERSION = "14.16.1";

const Dummy_TeamPlayer = [
  [
    { name: "team1_01", champ: "Aatrox" },
    { name: "team1_02", champ: "Aatrox" },
    { name: "team1_03", champ: "Aatrox" },
    { name: "team1_04", champ: "Aatrox" },
    { name: "team1_05", champ: "Aatrox" },
  ],
  [
    { name: "team2_01", champ: "Aatrox" },
    { name: "team2_02", champ: "Aatrox" },
    { name: "team2_03", champ: "Aatrox" },
    { name: "team2_04", champ: "Aatrox" },
    { name: "team2_05", champ: "Aatrox" },
  ],
];

const Dummy_Spell = {
  spell_0_id: "SummonerBoost",
  spell_1_id: "SummonerFlash",
};

const Dummy_Rune = {
  rune_0_id: "Domination/Electrocute/Electrocute",
  rune_1_id: "7201_Precision",
};

const Dummy_Items = [
  {
    id: "2031",
  },

  {
    id: "3152",
  },

  {
    id: "1082",
  },

  {
    id: "3340",
  },
  {
    id: "0",
  },

  {
    id: "1055",
  },

  {
    id: "3020",
  },
];

export default function RecordCard() {
  const [isDetailOpen, setIsDetailOpen] = useState(false);

  return (
    <div className="w-full h-fit flex flex-col gap-1">
      <div className="w-full h-28 pl-2 pr-6 bg-blue-500 dark:bg-blue-600 rounded-md">
        <div className="w-full h-full flex px-2 bg-primary !bg-opacity-80">
          {/* game detail */}
          <div className="basis-1/12 flex flex-col items-center justify-center text-xs">
            <p className="text-sm font-jalnangothic mb-1">승리</p>
            <p className="dark:text-zinc-300">솔로 랭크</p>
            <p className="dark:text-zinc-300">26:09</p>
            <p className="dark:text-zinc-300">1시간 전</p>
          </div>
          {/* user detail */}
          <div className="basis-7/12 flex px-2 gap-2 items-center">
            <div className="basis-1/5 flex flex-col justify-center items-center gap-1">
              <div className="relative w-2/3 h-12">
                <Image
                  sizes="3rem"
                  fill
                  className="object-contain rounded-md"
                  referrerPolicy="no-referrer"
                  alt="Record Played Champ Image"
                  src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/champion/Aatrox.png`}
                />
                <div className="bg-secondary w-fit h-fit p-[1px] relative z-10 left-2/3 top-2/3 rounded-sm">
                  <p className="text-[.65rem] leading-3">17</p>
                </div>
              </div>
              <div className="w-full flex">
                <div className="basis-1/4 relative h-4">
                  <Image
                    sizes="1rem"
                    fill
                    className="object-contain"
                    referrerPolicy="no-referrer"
                    alt="Record Played Spell Image"
                    src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/spell/${Dummy_Spell.spell_0_id}.png`}
                  />
                </div>
                <div className="basis-1/4 relative h-4">
                  <Image
                    sizes="1rem"
                    fill
                    className="object-contain"
                    referrerPolicy="no-referrer"
                    alt="Record Played Spell Image"
                    src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/spell/${Dummy_Spell.spell_1_id}.png`}
                  />
                </div>
                <div className="basis-1/4 relative h-4">
                  <Image
                    sizes="1rem"
                    fill
                    className="object-contain object-center"
                    referrerPolicy="no-referrer"
                    alt="Record Played Rune Image"
                    src={`https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/${Dummy_Rune.rune_0_id}.png`}
                  />
                </div>
                <div className="basis-1/4 relative h-4">
                  <Image
                    sizes="1rem"
                    fill
                    className="object-contain object-center"
                    referrerPolicy="no-referrer"
                    alt="Record Played Rune Image"
                    src={`https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/${Dummy_Rune.rune_1_id}.png`}
                  />
                </div>
              </div>
            </div>
            <div className="basis-1/5 flex flex-col justify-center items-center gap-1 text-xs">
              <div className="flex text-base gap-1 font-semibold">
                <p>6</p>/<p className="text-red-500 dark:text-red-400">1</p>/
                <p>4</p>
              </div>
              <p>10:00 평점</p>
              <div className="px-2 border-[1px] border-zinc-700 dark:border-zinc-200 rounded-md">
                <p className="font-jalnangothic tracking-wide leading-loose">
                  TOP
                </p>
              </div>
            </div>
            <div className="basis-1/5 flex flex-col justify-center items-center gap-1 text-xs">
              <p>
                킬 관여 <b>62</b>%
              </p>
              <p>
                CS <b>279</b>
              </p>
              <p>
                시야 <b>31</b>
              </p>
            </div>
            <div className="basis-1/4 h-fit flex flex-wrap justify-start items-center">
              {Dummy_Items.map((e, i) => (
                <div
                  key={`Record Items_${i}`}
                  className="basis-1/4 relative h-6"
                >
                  <Image
                    sizes="1.5rem"
                    fill
                    className="object-contain rounded-md"
                    referrerPolicy="no-referrer"
                    alt="Record Played Items Image"
                    src={
                      e.id != "0"
                        ? `https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/item/${e.id}.png`
                        : "/images/no_item.png"
                    }
                  />
                </div>
              ))}
            </div>
          </div>
          {/* team detail */}
          <div className="basis-1/3 flex justify-center items-center text-xs">
            <div className="basis-1/2 flex flex-col gap-[1px]">
              {Dummy_TeamPlayer[0].map((e, i) => (
                <div className="flex gap-1" key={`Record Team Player_01_${i}`}>
                  <div className="relative w-4 h-4">
                    <Image
                      sizes="1rem"
                      fill
                      className="object-contain rounded-sm"
                      referrerPolicy="no-referrer"
                      alt="Record Played Champ Image"
                      src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/champion/${e.champ}.png`}
                    />
                  </div>
                  <Link href={`/search/${e.name}`} target="_blank">
                    {e.name}
                  </Link>
                </div>
              ))}
            </div>
            <div className="basis-1/2 flex flex-col gap-[1px]">
              {Dummy_TeamPlayer[1].map((e, i) => (
                <div className="flex gap-1" key={`Record Team Player_02_${i}`}>
                  <div className="relative w-4 h-4">
                    <Image
                      sizes="1rem"
                      fill
                      className="object-contain rounded-sm"
                      referrerPolicy="no-referrer"
                      alt="Record Played Champ Image"
                      src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/champion/${e.champ}.png`}
                    />
                  </div>
                  <Link href={`/search/${e.name}`} target="_blank">
                    {e.name}
                  </Link>
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="relative w-6 h-4 left-full bottom-6">
          <button
            className="w-full h-full"
            onClick={() => setIsDetailOpen(!isDetailOpen)}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24"
              viewBox="0 -960 960 960"
              width="24"
              className={`dark:fill-zinc-200 hover:scale-125 transition-all duration-300 ${
                isDetailOpen ? "rotate-0" : "rotate-180"
              }`}
            >
              <path d="m296-345-56-56 240-240 240 240-56 56-184-184-184 184Z" />
            </svg>
          </button>
        </div>
      </div>
      <RecordDetailCard isDetailOpen={isDetailOpen} />
    </div>
  );
}
