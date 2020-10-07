function getQueryVar(value) {
    let query = window.location.search.substring(1);//参数部分
    let lets = query.split("&");
    for (let i = 0; i < lets.length; i++) {
        let pair = lets[i].split("=");
        if (pair[0] === value) {
            return pair[1];
        }
    }
    return (false);
}

function getRootPath() {
    let pathName = window.document.location.pathname;
    return pathName.split("/")[1];
}

//获取路径最后一个斜杠后面的
function getLastPath() {
    let pathName = window.document.location.pathname;
    return pathName.substring(pathName.lastIndexOf("/") + 1);
}

function trim(str) {
    if (str === null || str === "") {
        return str;
    }
    return str.replace(/^\s+|\s+$/g, "");
}

function cookieSet(key, val, time) {
    let date = new Date();
    date.setTime(date.getTime() + time * 1000);
    document.cookie = key + "=" + val + ";expires=" + date.toUTCString();  //设置cookie
}

function cookieGet(key) {
    let getCookie = document.cookie.replace(/[ ]/g, "");//获取cookie，并且将获得的cookie格式化，去掉空格字符
    let arrCookie = getCookie.split(";");
    let tips;
    for (let i = 0; i < arrCookie.length; i++) {
        let arr = arrCookie[i].split("=");
        if (key === arr[0]) {
            tips = arr[1];
            break;
        }
    }
    return tips;
}

function getDate() {
    let d = new Date();
    let year = d.getFullYear();
    let month = change(d.getMonth() + 1);
    let day = change(d.getDate());
    let hour = change(d.getHours());
    let minute = change(d.getMinutes());
    let second = change(d.getSeconds());

    function change(t) {
        if (t < 10) {
            return "0" + t;
        } else {
            return t;
        }
    }

    return year + '-' + month + '-' + day;
}

function timestampToDate(timestamp) {
    let time = new Date(timestamp);
    let y = time.getFullYear();
    let m = time.getMonth() + 1;
    let d = time.getDate();
    let h = time.getHours();
    let mm = time.getMinutes();
    let s = time.getSeconds();
    return y + '-' + m + '-' + d;
}

function strOmit(value, length) {
    if (value == null) {
        return null;
    }
    if (value.length <= length) {
        return value;
    } else {
        return value.substring(0, length) + "...";
    }
}