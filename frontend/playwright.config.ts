import {defineConfig} from '@playwright/test';

/** Configure browser journeys against the live Vite development server. */
export default defineConfig({
  testDir: './e2e',
  timeout: 30_000,
  workers: 1,
  use: {baseURL: 'http://localhost:5173'},
  reporter: [['json', {outputFile: '../e2e-results.json'}], ['list']],
});
