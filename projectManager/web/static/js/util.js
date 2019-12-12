//js获取项目根路径
function getRootPath() {
    //获取当前网址
    var curWwwPath = window.location.href;
    //获取主机地址之后的目录
    var pathName = window.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    return (localhostPath + projectName);
}


//获取cookie函数
function getCookie(name){
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i].trim();
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
