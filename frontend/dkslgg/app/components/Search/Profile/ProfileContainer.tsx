"use client";

import { useMemo } from "react";
import Button from "../../Common/Button";
import Image from "next/image";
import DummyProfile from "@/public/images/dkslhead.svg";
import DummyRank from "@/public/images/rank-icons/challenger.png";

export default function ProfileContainer() {
  const BG_URL = useMemo(() => {
    return `/images/bg/search_bg_${Math.floor(Math.random() * 6) + 1}.jpg`;
  }, []);

  return (
    <section
      className="w-full min-h-96 bg-cover bg-center flex flex-col justify-center items-center relative"
      style={{
        backgroundImage: `url(${BG_URL})`,
      }}
    >
      <div className="absolute top-0 left-0 right-0 bottom-0 bg-primary bg-opacity-50 dark:bg-opacity-50 pointer-events-none"></div>
      <div className="relative z-10 flex gap-8 items-center">
        <div className="w-40 h-40 relative rounded-full border-yellow-300 border-2 overflow-hidden">
          <Image
            referrerPolicy="no-referrer"
            className="object-cover"
            src={DummyProfile}
            alt="Profile Icon"
            fill
          />
        </div>
        <div className="flex flex-col gap-4">
          <div className="flex items-center">
            <h1 className="font-jalnangothic text-4xl mr-4">NickName</h1>
            <div className="w-32 h-fit absolute -right-32">
              <Image
                referrerPolicy="no-referrer"
                className="object-cover"
                src={DummyRank}
                alt="Player Rank Icon"
              />
            </div>
          </div>
          <div>LBTI</div>
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
