$(document).ready(function () {
    $.get("/productPictures/skus/14",function (data,staus) {
        if (staus == "success"){
            showData(data);

        }
    })
})

function setSelectSku() {
    var index1=$('#season option:selected').val();

    var optionJson=[];
    $.ajax({
        url: "/selectSku/"+index1,
        type: "get",
        datatype: "json",
        success:function(data){
            optionJson = data;
            i = data[0].skusname;
            var a=data.length;
            var selectObj=document.getElementById("skus");
            selectObj.options.length=0;
            selectObj.options.add(new Option("请选择风格",0));
            for(var i=0;i<data.length;i++){
                selectObj.add(new Option(optionJson[i].skusname,optionJson[i].skusid));
            }
        }
    })
}

function setSelectPictures() {
    var index1=$('#skus option:selected').val();
    var optionJson=[];
    $.ajax({
        url: "/productPictures/skus/"+index1,
        type: "get",
        datatype: "json",
        success:function(data){
            if (data==null) {
                var str = "";//定义用于拼接的字符串
                for (var i = 0; i < data.length; i++) {
                    //拼接表格的行和列
                    str = "<a class='col-lg-3 col-xs-6'>" +
                        "<img src='"+data[i].picturesurl+"'/>" + "</a>";
                    //追加到table中
                    $("#tab").append(str);

                }

            }else {
                $('#tab').html("");
                var str = "";//定义用于拼接的字符串
                for (var i = 0; i < data.length; i++) {
                    //拼接表格的行和列
                    str = "<a class='col-lg-3 col-xs-6'>" +
                        "<img src='"+data[i].picturesurl+"'/>" + "</a>";
                    //追加到table中
                    $("#tab").append(str);

                }
            }

        }
    })
}