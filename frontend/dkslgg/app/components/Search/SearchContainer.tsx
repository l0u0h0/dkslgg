"use client";

import { useState } from "react";
import TabContainer from "../Common/TabContainer";
import RecordContainer from "./Record/RecordContainer";
import AnaylzeContainer from "./Analyze/AnalyzeContainer";

export default function SearchContainer() {
  const [tabs, setTabs] = useState(0);

  const changeView = (tab: number) => {
    switch (tab) {
      case 0:
        return <RecordContainer />;
      case 1:
        return <AnaylzeContainer />;
      case 2:
        return <h1>Group</h1>;
      case 3:
        return <h1>Review</h1>;
      default:
        return <h1>Loading</h1>;
    }
  };

  return (
    <section className="w-full flex justify-center bg-secondary">
      <TabContainer
        items={["전적", "분석", "소속", "리뷰"]}
        tab={tabs}
        setTabs={setTabs}
      >
        {changeView(tabs)}
      </TabContainer>
    </section>
  );
}
