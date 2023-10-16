import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import path from 'node:path';

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    port: 3000,
    // proxy: {
    //   '/api': {
    //     target: 'http://70.12.247.95:8080',
    //     rewrite: (path) => path.replace(/^\/api/, ''),
    //   }
    // }
  },
  resolve: {
    alias: [{ find: '@', replacement: path.resolve(__dirname, 'src') }],
  },
  esbuild: {},
  plugins: [react()],
})
