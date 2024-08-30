import { NextRequest, NextResponse } from "next/server";

export async function GET(request: NextRequest) {
  console.log(request.nextUrl.searchParams);
  const response = await fetch(`${request.nextUrl.origin}/api/dummy/record`);

  return NextResponse.json({
    status: "SUCCESS",
    data: await response.json(),
  });
}
