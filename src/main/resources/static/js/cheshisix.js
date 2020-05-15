$(document).ready(function () {
    var nums = location.search;
    var reg = /(?<==).*/g;
    var s = nums.match(reg).toString();
    $.get("/manage/productimgtwo/"+s,function (data,staus) {
        if (staus == "success"){
            showDataTwo(data);
        }
    })
})