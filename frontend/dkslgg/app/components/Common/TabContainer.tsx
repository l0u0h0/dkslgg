"use client";

export interface ITabContainerProps {
  items: string[];
  tab: number;
  setTabs: (args: number) => void;
  children: React.ReactNode;
}

export default function TabContainer({
  items,
  tab,
  setTabs,
  children,
}: ITabContainerProps) {
  return (
    <div className="w-full">
      <div className="w-full flex py-4 gap-4 font-bold justify-center items-center drop-shadow-lg">
        {items.map((e, i) =>
          tab !== i ? (
            <button
              className="px-6 py-2 rounded-lg"
              key={`tab_key-${i}`}
              onClick={() => setTabs(i)}
            >
              {e}
            </button>
          ) : (
            <button
              className="bg-zinc-700 dark:bg-zinc-200 text-zinc-100 dark:text-zinc-700 px-6 py-2 rounded-lg"
              key={`tab_key-${i}`}
              disabled
            >
              {e}
            </button>
          )
        )}
      </div>
      {children}
    </div>
  );
}
