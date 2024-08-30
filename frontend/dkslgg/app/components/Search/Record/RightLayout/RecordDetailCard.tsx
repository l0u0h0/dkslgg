"use client";

import { DummyDetailData, DDRAGON_VERSION } from "@/config/data";
import Image from "next/image";
import Link from "next/link";

interface IRecordDetailPlayerProps {
  name: string;
  line: string;
  kill: number;
  death: number;
  assist: number;
  kda: number;
  CS: number;
  gold: string;
  ward: string;
  damage: number;
  champ: string;
  spell_0_id: string;
  spell_1_id: string;
  rune_0_id: string;
  rune_1_id: string;
  items: {
    id: string;
  }[];
}

function RecordDetailPlayer({ data }: { data: IRecordDetailPlayerProps }) {
  return (
    <div className="w-full basis-1/5 px-1 flex border-b-[1px] border-zinc-200 dark:border-zinc-800 last:border-none">
      <div className="basis-1/3 flex justify-center items-center gap-1">
        <div className="relative w-8 h-8">
          <Image
            sizes="2rem"
            fill
            className="object-contain rounded-md"
            referrerPolicy="no-referrer"
            alt="Record Played Champ Image"
            src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/champion/${data.champ}.png`}
          />
          <div className="bg-secondary w-fit h-fit p-[1px] relative z-10 left-2/3 top-[1.25rem] rounded-sm">
            <p className="text-[.5rem] leading-3">17</p>
          </div>
        </div>
        <div className="flex">
          <div className="flex flex-col">
            <div className="relative w-4 h-4">
              <Image
                sizes="1rem"
                fill
                className="object-contain"
                referrerPolicy="no-referrer"
                alt="Record Played Spell Image"
                src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/spell/${data.spell_0_id}.png`}
              />
            </div>
            <div className="relative w-4 h-4">
              <Image
                sizes="1rem"
                fill
                className="object-contain"
                referrerPolicy="no-referrer"
                alt="Record Played Spell Image"
                src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/spell/${data.spell_1_id}.png`}
              />
            </div>
          </div>
          <div className="flex flex-col">
            <div className="relative w-4 h-4">
              <Image
                sizes="1rem"
                fill
                className="object-contain object-center"
                referrerPolicy="no-referrer"
                alt="Record Played Rune Image"
                src={`https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/${data.rune_0_id}.png`}
              />
            </div>
            <div className="relative w-4 h-4">
              <Image
                sizes="1rem"
                fill
                className="object-contain object-center"
                referrerPolicy="no-referrer"
                alt="Record Played Rune Image"
                src={`https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/${data.rune_1_id}.png`}
              />
            </div>
          </div>
        </div>
        <div className="flex flex-col text-[.6rem] justify-center items-center">
          <div className="w-fit px-1 border-[1px] border-zinc-700 dark:border-zinc-200 rounded-md">
            <p className="text-[0.5rem] font-jalnangothic tracking-wide leading-loose">
              {data.line}
            </p>
          </div>
          <Link
            href={`/search/${data.name}`}
            target="_blank"
            className="font-jalnangothic overflow-x-hidden"
          >
            {data.name}
          </Link>
        </div>
      </div>
      <div className="flex-1 flex justify-center items-center">
        <div className="basis-1/4 text-xs text-center">
          <div className="w-full flex justify-center items-center">
            <p>{data.kill}</p>/
            <p className="text-red-500 dark:text-red-400">{data.death}</p>/
            <p>{data.assist}</p>
          </div>
          <p className="text-[.6rem]">{Math.floor(data.kda * 100)}:00</p>
        </div>
        <div className="flex-1 flex flex-col justify-center items-center gap-2">
          <div className="flex text-[.5rem] gap-1">
            <p>
              <b>C</b> {data.CS}
            </p>
            <p>
              <b>G</b> {data.gold}
            </p>
            <p>
              <b>W</b> {data.ward}
            </p>
          </div>
          <div className="w-full flex justify-center">
            <div className="w-2/3 h-4 flex bg-zinc-300 dark:bg-zinc-800 justify-start rounded-sm">
              <span
                className="bg-red-400 dark:bg-red-500"
                style={{
                  flexBasis: `${Math.floor(data.damage * 100)}%`,
                }}
              ></span>
            </div>
            <p className="absolute z-10 text-[.6rem] tracking-wide text-zinc-800 dark:text-zinc-100">
              12700
            </p>
          </div>
        </div>
      </div>
      <div className="basis-1/4 flex justify-center items-center">
        {data.items.map((e, i) => (
          <div
            key={`Record Detail Player Items_${i}`}
            className="relative w-4 h-4"
          >
            <Image
              sizes="1rem"
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
  );
}

export default function RecordDetailCard({
  isDetailOpen,
}: {
  isDetailOpen: boolean;
}) {
  return (
    <div
      className="w-full flex bg-translate"
      style={
        isDetailOpen
          ? {
              height: "20rem",
              opacity: 1,
              animation: "0.6s ease-in-out loadEffect",
            }
          : {
              height: 0,
              opacity: 0,
            }
      }
    >
      <div className="basis-1/2 h-full flex flex-col">
        <div className="w-full basis-[5%] bg-blue-400 dark:bg-blue-600">
          <p className="text-xs leading-loose font-jalnan text-center">승리</p>
        </div>
        <div className="w-full basis-[95%] flex flex-col bg-blue-500 dark:bg-blue-600 !bg-opacity-25">
          {DummyDetailData.win.map((e, i) => (
            <RecordDetailPlayer key={i} data={e} />
          ))}
        </div>
      </div>
      <div className="basis-1/2 h-full flex flex-col">
        <div className="w-full basis-[5%] bg-red-400 dark:bg-red-600">
          <p className="text-xs leading-loose font-jalnan text-center">패배</p>
        </div>
        <div className="w-full basis-[95%] flex flex-col bg-red-500 dark:bg-red-600 !bg-opacity-25">
          {DummyDetailData.loose.map((e, i) => (
            <RecordDetailPlayer key={i} data={e} />
          ))}
        </div>
      </div>
    </div>
  );
}
