import { http, HttpResponse } from "msw";

export const imageUploadHandler = http.post(
  "/api/upload/test",
  async ({ request }) => {
    const data = await request.formData();

    const file = data.get("file");

    if (file && file instanceof File) {
      console.log(file);
      return HttpResponse.json({ url: "/images/testImg.png" });
    }

    return new HttpResponse(null, {
      status: 400,
      statusText: "Data Not Found",
    });
  }
);
