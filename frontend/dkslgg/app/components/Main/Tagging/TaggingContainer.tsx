"use client";

import Button from "../../Common/Button";

export default function TaggingContainer() {
  return (
    <section className="w-full h-[10%] min-h-24 flex justify-center items-center bg-zinc-300 dark:bg-zinc-600">
      <p className="m-4 text-xl font-jalnangothic tracking-wide">
        &#128161; 나의 <b className="underline underline-offset-4">롤BTI</b>는
        무엇일까?
      </p>
      <Button size="base" onClick={() => console.log("LBTI 검사하기")}>
        검사하기
      </Button>
    </section>
  );
}
