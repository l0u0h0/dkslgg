import { getProfileData } from "@/services/getRecordService";
import { NextRequest, NextResponse } from "next/server";

export async function GET(request: NextRequest) {
  const response = await (
    await fetch(`${request.nextUrl.origin}/api/dummy/record`)
  ).json();

  console.log(response.data.profile);

  return NextResponse.json({
    status: "SUCCESS",
    profile: getProfileData(response.data.profile),
    records: "record",
  });
}
