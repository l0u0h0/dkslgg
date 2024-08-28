/** @type {import('next').NextConfig} */
import removeImports from "next-remove-imports";

const nextConfig = {
  reactStrictMode: true,
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "ddragon.leagueoflegends.com",
      },
    ],
  },
  output: "standalone",
  webpack: (config, _options) => {
    config.cache = false;
    return config;
  },
};

export default nextConfig;

export const removeImport = removeImports({});
