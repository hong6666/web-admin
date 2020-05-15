$(document).ready(function () {
    $.get("/manage/productSkusAll",function (data,staus) {
        if (staus == "success"){
            showData(data);
        }
    })
})

