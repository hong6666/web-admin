$(document).ready(function () {
    var nums = location.search;
    var reg = /[1-9][0-9]*/g;
    var s = nums.match(reg).toString();
    $.get("/manage/productimg/"+s,function (data,staus) {
        if (staus == "success"){
            showDataTwo(data);
        }
    })
})