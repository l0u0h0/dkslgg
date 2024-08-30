import { DummyRecord } from "@/config/data";
import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json({
    status: 200,
    data: DummyRecord,
  });
}
