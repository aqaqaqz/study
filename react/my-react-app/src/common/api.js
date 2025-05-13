var exports = module.exports = {};

var BASE_DOMAIN = 'https://lm.shoppingntmall.com';

exports.TEST_URL = BASE_DOMAIN + "/app/version";

exports.makeGetUrl = (url, param) => {
    let data = "";
    let keys = Object.keys(param);

    for(let i=0;i<keys.length;i++){
        let key = keys[i];

        if(i == 0)  data += "?";
        else        data += "&";

        data += key + "=" + param[key];
    }

    return url + data;
}