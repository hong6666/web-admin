$(document).ready(function () {
    $.get("/publicInfo/1",function (data,staus) {
        if (staus == "success"){
            $("#publicaddress").html(data.publicaddress)
            $("#publicfactoryaddress").html(data.publicfactoryaddress)
            $("#publictelephone").html(data.publictelephone)
        }
    })
})