$(document).ready(function () {
    $.get("/manage/productSeasonAll",function (data,staus) {
        if (staus == "success"){
            showData(data);
        }
    })
})

$(document).ready(function () {
    $.get("/manage/productSkus/"+location.search,function (data,staus) {
        if (staus == "success"){
            showDataTwo(data);
        }
    })
})

