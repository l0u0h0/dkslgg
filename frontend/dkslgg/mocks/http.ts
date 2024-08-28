import express from "express";
import { handlers } from "./handlers";
import { createMiddleware } from "@mswjs/http-middleware";
import cors from "cors";

const app = express();
const port = 9090;

app.use(
  cors({
    methods: ["POST", "GET", "HEAD"],
    origin: "http://localhost:3000",
    optionsSuccessStatus: 200,
  })
);
app.use(express.json());
app.use(createMiddleware(...handlers));

app.listen(port, () => console.log(`Mock Server is Running on Port: ${port}`));
