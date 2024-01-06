import axios from "axios";

const instAxios = axios.create({
    baseURL: 'http://localhost:8080',
});

export default instAxios;