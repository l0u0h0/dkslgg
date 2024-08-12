/** @type {import('next').NextConfig} */
import removeImports from "next-remove-imports";

const nextConfig = {
  reactStrictMode: true,
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "www.notion.so",
      },
      {
        protocol: "https",
        hostname: "images.unsplash.com",
      },
      {
        protocol: "https",
        hostname: "prod-files-secure.s3.us-west-2.amazonaws.com",
      },
      {
        protocol: "http",
        hostname: "res.cloudinary.com",
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
