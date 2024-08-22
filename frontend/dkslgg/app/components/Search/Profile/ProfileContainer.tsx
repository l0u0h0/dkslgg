"use client";

import { useMemo } from "react";
import Button from "../../Common/Button";

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
      <div className="relative z-10">
        <div></div>
        <div>
          <div>
            <h1>NickName</h1>
            <div></div>
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
