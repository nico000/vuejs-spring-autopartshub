import axios from "axios";

const instAxios = axios.create({
    baseURL: import.meta.env.VITE_APP_API_ROOT_URL
});

export default instAxios;