"use client";
import { useEffect } from "react";

async function startMSW() {
  if (typeof window !== "undefined" && process.env.NODE_ENV === "development") {
    const worker = await import("../mocks/broswer").then((res) => res.default);
    worker.start({
      onUnhandledRequest: "bypass",
    });
  }
}

export default function IntegrateMSW({
  children,
}: {
  children: React.ReactNode;
}) {
  useEffect(() => {
    startMSW();
  }, []);

  return <>{children}</>;
}
