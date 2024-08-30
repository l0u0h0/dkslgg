"use client";

import CardView from "@/app/components/Common/CardView";
import Image from "next/image";
import DummyRank from "@/public/images/rank-icons/master.png";

export default function TierCardView() {
  return (
    <CardView title="&#127941; 티어">
      <div className="w-full px-8 py-6 flex flex-col gap-4">
        <div>
          <p className="font-semibold">솔로 랭크</p>
          <div className="flex items-center gap-4">
            <div className="relative w-24">
              <Image
                referrerPolicy="no-referrer"
                alt="rank-icon"
                src={DummyRank}
                priority
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
                priority
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
                priority
              />
            </div>
            <p className="text-xl font-jalnangothic tracking-wider">Matser</p>
          </div>
        </div>
      </div>
    </CardView>
  );
}
