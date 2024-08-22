"use client";

import { useCallback, useState } from "react";
import TabContainer, { ITabContainerProps } from "../../Common/TabContainer";
import PlayerRanking from "./PlayerRanking";
import React from "react";
import { playerRankDummyData } from "@/config/data";

type PlayerRankData = {
  name: string;
  point: string;
}[][];

const PLAYER_RANK_TABS = ["일간", "주간", "월간"];
const GROUP_RANK_TABS = ["평균 티어", "멤버 수", "최근 가입"];

const MemoizedRankContainer = React.memo<ITabContainerProps>(
  ({ items, tab, setTabs, children }) => {
    return (
      <TabContainer items={items} tab={tab} setTabs={setTabs}>
        {children}
      </TabContainer>
    );
  },
  (prev, next) => {
    return prev.tab === next.tab;
  }
);

export default function RankingContainer() {
  const [playerRankTabs, setPlayerRankTabs] = useState(0);
  const [groupRankTabs, setGroupRankTabs] = useState(0);

  const playerRankTabsSetter = useCallback(
    (tabIndex: number) => {
      setPlayerRankTabs(tabIndex);
    },
    [playerRankTabs]
  );

  const groupRankTabsSetter = useCallback(
    (tabIndex: number) => {
      setGroupRankTabs(tabIndex);
    },
    [groupRankTabs]
  );

  return (
    <section className="w-full h-fit p-24 flex gap-24 justify-center items-start">
      <div className="min-w-96 basis-1/4 py-4">
        <p className="card-title">&#127942; 명예의 전당</p>
        <MemoizedRankContainer
          items={PLAYER_RANK_TABS}
          tab={playerRankTabs}
          setTabs={playerRankTabsSetter}
        >
          <PlayerRanking data={playerRankDummyData[playerRankTabs]} />
        </MemoizedRankContainer>
      </div>
      <div className="min-w-96 basis-1/4 py-4">
        <p className="card-title">&#127969; 소속 별 순위</p>
        <MemoizedRankContainer
          items={GROUP_RANK_TABS}
          tab={groupRankTabs}
          setTabs={groupRankTabsSetter}
        >
          <PlayerRanking data={playerRankDummyData[playerRankTabs]} />
        </MemoizedRankContainer>
      </div>
    </section>
  );
}
