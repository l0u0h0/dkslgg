import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import HeaderContainer from "@/app/components/Header/HeaderContainer";
import Footer from "@/app/components/Footer/Footer";
import Providers from "../provider/ThemeProvider";
import IntegrateMSW from "@/provider/MSWProvider";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: {
    template: "%s | dkslgg",
    default: "dkslgg",
  },
  description: "League Of Legends 전적 조회 서비스.",
  icons: "/images/dkslhead.svg",
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
            <HeaderContainer />
            {children}
            <Footer />
          </Providers>
        </IntegrateMSW>
      </body>
    </html>
  );
}
