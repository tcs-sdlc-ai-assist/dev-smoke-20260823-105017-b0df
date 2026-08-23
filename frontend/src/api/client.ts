import axios from 'axios';
/** Call the Spring API and attach the current demo access token. */ export const api=axios.create({baseURL:'/api'}); api.interceptors.request.use(config=>{const token=localStorage.getItem('token');if(token)config.headers.Authorization=`Bearer ${token}`;return config;});
