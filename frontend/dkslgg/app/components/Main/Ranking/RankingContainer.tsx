"use client";

import { useCallback, useState } from "react";
import TabContainer, { ITabContainerProps } from "../../Common/TabContainer";
import Ranking from "./Ranking";
import React from "react";

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
    <section className="w-full h-fit p-24 flex gap-24 justify-center items-center">
      <div className="py-4">
        <p className="card-title">&#127942; 명예의 전당</p>
        <MemoizedRankContainer
          items={["일간", "주간", "월간"]}
          tab={playerRankTabs}
          setTabs={playerRankTabsSetter}
        >
          <Ranking />
        </MemoizedRankContainer>
      </div>
      <div className="py-4">
        <p className="card-title">&#127969; 소속 별 순위</p>
        <MemoizedRankContainer
          items={["평균 티어", "멤버 수", "최근 가입"]}
          tab={groupRankTabs}
          setTabs={groupRankTabsSetter}
        >
          <Ranking />
        </MemoizedRankContainer>
      </div>
    </section>
  );
}
