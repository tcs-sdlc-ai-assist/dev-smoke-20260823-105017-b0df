import { defineConfig } from 'vite'; import react from '@vitejs/plugin-react';
/** Configure the React development server and backend proxy. */ export default defineConfig({plugins:[react()],server:{port:5173,proxy:{'/api':'http://127.0.0.1:8080'}},test:{environment:'jsdom',globals:true,exclude:['e2e/**','../e2e/**','node_modules/**']}});
