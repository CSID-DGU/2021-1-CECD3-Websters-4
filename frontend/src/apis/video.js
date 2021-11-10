import axios from './axios'

const videoAPI={
    get:(data)=>{
        const url='/problem'
        return axios.get(url,data)
    },
    upload:(formData,config)=>{
        const url='/api/v1/video'
        axios.post(url,formData, config)
    },
    getList:()=>{
        const url='/api/v1/videos'
        return axios.get(url)
    }

    
}

export default videoAPI