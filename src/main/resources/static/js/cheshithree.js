$(document).ready(function () {
    $.get("/manage/productSeasonAll",function (data,staus) {
        if (staus == "success"){
            showData(data);
        }
    })
})