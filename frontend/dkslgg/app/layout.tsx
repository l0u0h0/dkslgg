import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import Providers from "../provider/ThemeProvider";
import IntegrateMSW from "@/provider/MSWProvider";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "dkslgg",
  description: "",
  icons: "images/dkslhead.svg",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="ko">
      <body
        className={`${inter.className} bg-primary w-screen !overflow-x-hidden`}
      >
        <IntegrateMSW>
          <Providers>
            <Header />
            {children}
            <Footer />
          </Providers>
        </IntegrateMSW>
      </body>
    </html>
  );
}
