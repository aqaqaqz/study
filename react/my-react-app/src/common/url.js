var BASE_DOMAIN = 'https://lm.shoppingntmall.com/app';

const UrlUtils = {
    HOME : "/",
    TEST_URL : BASE_DOMAIN + "/users",
    JOIN_URL : BASE_DOMAIN + "/join",
    LOGIN_URL : BASE_DOMAIN + "/login",
    makeGetDataUrl : (param={}) => {
        let data = "";
        let keys = Object.keys(param);

        for(let i=0;i<keys.length;i++){
            let key = keys[i];

            if(i == 0)  data += "?";
            else        data += "&";

            data += key + "=" + param[key];
        }

        return data;
    }
}

export default UrlUtils;