"use client";

export default function PlayerRanking({
  data,
}: {
  data: { name: string; point: string }[] | null;
}) {
  if (!data) return <h1>Loading...</h1>;
  return (
    <div className="w-full flex flex-col gap-4 px-4">
      {data.map((e, i) => (
        <div
          className="w-full flex border-b-[1px] border-zinc-700 dark:border-zinc-200 text-lg"
          key={`player_rank_data_row_${i}`}
        >
          <p className="basis-[10%] font-jalnangothic text-center">{i + 1}</p>
          <p className="basis-[70%] font-bold overflow-x-hidden">{e.name}</p>
          <p className="basis-[20%]">{e.point}점</p>
        </div>
      ))}
    </div>
  );
}
