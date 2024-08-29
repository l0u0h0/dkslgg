"use client";

import CardView from "@/app/components/Common/CardView";
import { DDRAGON_VERSION } from "@/config/data";
import Image from "next/image";

export default function LBTICardView() {
  return (
    <CardView title="&#128195; 롤BTI 분석">
      <div>
        <div className="relative w-16 h-16">
          <Image
            fill
            className="object-contain rounded-md"
            referrerPolicy="no-referrer"
            alt="Record Played Champ Image"
            src={`https://ddragon.leagueoflegends.com/cdn/${DDRAGON_VERSION}/img/champion/Aatrox.png`}
          />
        </div>
        <div></div>
        <div></div>
      </div>
    </CardView>
  );
}
