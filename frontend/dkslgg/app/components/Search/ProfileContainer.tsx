"use client";

import { useMemo } from "react";
import Button from "../Common/Button";
import Image from "next/image";
import DummyProfile from "@/public/images/dkslhead.svg";
import DummyRank from "@/public/images/rank-icons/challenger.png";
import Tag from "../Common/Tag";
import { ProfileDataType } from "@/types/record.type";

interface IProfileContainerProps {
  profile: ProfileDataType;
}

export default function ProfileContainer({ profile }: IProfileContainerProps) {
  const BG_URL = useMemo(() => {
    return `/images/bg/search_bg_${Math.floor(Math.random() * 6) + 1}.jpg`;
  }, []);

  const PROFILE_ICON_URL = useMemo(() => {
    return `https://ddragon.leagueoflegends.com/cdn/14.16.1/img/profileicon/${profile.profile_icon_id}.png`;
  }, [profile.profile_icon_id]);

  const RANK_ICON_URL = useMemo(() => {
    return `/images/rank-icons/${profile.tier.toLowerCase()}.png`;
  }, [profile.tier]);

  return (
    <section
      className="w-full min-h-96 px-[20%] bg-cover bg-center flex justify-center relative"
      style={{
        backgroundImage: `url(${BG_URL})`,
      }}
    >
      <div className="absolute top-0 left-0 right-0 bottom-0 bg-primary bg-opacity-50 dark:bg-opacity-50 pointer-events-none"></div>
      <div className="w-full relative z-10 flex gap-8 items-center">
        <div className="w-36 h-36 relative rounded-full border-yellow-300 border-2 overflow-hidden">
          <Image
            referrerPolicy="no-referrer"
            className="object-cover"
            src={PROFILE_ICON_URL}
            alt="Profile Icon"
            fill
            sizes="9rem"
          />
        </div>
        <div className="flex flex-col gap-4">
          <div className="flex items-end">
            <div className="flex flex-col gap-2">
              <h1 className="font-jalnangothic text-4xl mr-4">
                {profile.name}
              </h1>
              <Tag text="LBTI" color="red" />
            </div>
            <div className="relative w-24 h-24">
              <Image
                referrerPolicy="no-referrer"
                className="object-contain"
                src={RANK_ICON_URL}
                alt="Player Rank Icon"
                fill
                sizes="6rem"
              />
            </div>
          </div>
          <div>
            <Button size="base" onClick={() => location.reload()}>
              전적 갱신
            </Button>
          </div>
        </div>
      </div>
    </section>
  );
}
