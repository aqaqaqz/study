import axios from 'axios';

const commonHeader = {
    'Accept':'application/json',
    'Content-Type':'application/json;charset=UTP-8',
}

const ApiUtils = {
    get : (url, data={}) => {
        return (
            axios.get(url, {
                params : data,
                header : commonHeader
            })
        )
    },
    post : (url, data={}) => {
        return (
            axios.post(url, data, {
                headers : commonHeader
            })
        )
    }
}

export default ApiUtils;