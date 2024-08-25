"use client";

import { useState } from "react";
import TabContainer from "../Common/TabContainer";
import RecordContainer from "./Record/RecordContainer";

export default function SearchContainer() {
  const [tabs, setTabs] = useState(0);

  return (
    <section className="w-full flex justify-center">
      <TabContainer
        items={["전적", "분석", "소속", "리뷰"]}
        tab={tabs}
        setTabs={setTabs}
      >
        <RecordContainer />
      </TabContainer>
    </section>
  );
}
