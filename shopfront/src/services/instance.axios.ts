import { getAccessToken } from "@/utils/sessionTokenAccessor";
import axios from "axios";


const instance = axios.create({
    baseURL: process.env.BACKEND_URL,
    headers: {
        "Content-type": "application/json",
    },
});

instance.interceptors.request.use(
    async (config) => {
        const token = await getAccessToken();
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        } else {
            delete config.headers.Authorization;
        }
        return config;
    },
    (error) => Promise.reject(error)
);


export default instance;
