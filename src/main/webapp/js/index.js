$(function () {
    // 判断是显示复制按钮还是显示马上省按钮(苹果手机目前显示马上省)
    var result2 = $(".copy").data("result");
    if (!$.isEmptyObject(result2)) {
        if (!isIPhone()) {
            $(".copy").show();
            $(".saveNow").hide();
        } else {
            $(".copy").hide();
            $(".saveNow").show();
        }
    } else {
        $(".copy").hide();
        $(".saveNow").show();
    }
    //------
    //删除按钮显示判断
    if ($("#url").val().length == 0) {
        $("#delTag").hide();
    }

    $("#url").focus(function () {
        if ($(this).val().length > 0) {
            $("#delTag").show();
        } else {
            $("#delTag").hide();
        }
    });
    $("#url").focus(function () {
        if ($(this).val().length > 0) {
            $("#delTag").show();
        } else {
            $("#delTag").hide();
        }
    });
    $("#url").on("keyup change", function () {
        if ($(this).val().length > 0) {
            $("#delTag").show();
        } else {
            $("#delTag").hide();
        }
    });

    $("#delTag").click(function () {
        $("#url").val("").focus();
        $("#delTag").hide();
    });
    //弹出框
    $('#myModal').on('hidden.bs.modal', function () {
        $("#myModal div.alert").hide();
        $("#myModal div.form-horizontal").show();
    })
    //点击复制，复制到粘贴板
    $(".copy").on("click", function () {
        var result2 = $(this).data("result");
        var sortUrlText = "";
        if (isPC()) {
            sortUrlText = (result2.couponShortLinkUrl !== null && !(typeof(result2.couponShortLinkUrl) === "undefined")) ? result2.couponShortLinkUrl : result2.shortLinkUrl;
            copyText(sortUrlText);
        } else {
            sortUrlText = (result2.couponLinkTaoToken !== null && !(typeof(result2.couponLinkTaoToken) === "undefined")) ? result2.couponLinkTaoToken : result2.taoToken;
            copyText(sortUrlText);
        }
    });
    ////点击马上省，业务处理
    $(".saveNow").on("click", function () {
        var params = $(this).attr("data-target").split(",");
        var numxx = params[0];
        var gaofanType = params[1];
        var shopUrl = params[2];

        if (Clipboard.isSupported()) {
            $("#copyIt").text("复制");
        } else {
            $("#copyIt").text("全选 & 复制");
        }

        $.ajax({
            type: "post",
            url: "/api/sortUrl",
            data: {
                gaofanType: gaofanType,
                shopUrl: shopUrl
            },
            async: false,
            dataType: "json",
            success: function (data) {
                var sortUrlText = "";

                if (!data.success) {
                    bootbox.alert("未找到优惠信息，您可扫描下方二维码，找淘小秘解决。");
                    return;
                }

                if (isPC()) {
                    sortUrlText = (data.result.data.couponShortLinkUrl !== null && !(typeof(data.result.data.couponShortLinkUrl) === "undefined")) ? data.result.data.couponShortLinkUrl : data.result.data.shortLinkUrl;
                    $("#sortUrlCopy").val(sortUrlText);
                } else {
                    sortUrlText = (data.result.data.couponLinkTaoToken !== null && !(typeof(data.result.data.couponLinkTaoToken) === "undefined")) ? data.result.data.couponLinkTaoToken : data.result.data.taoToken;
                    $("#sortUrlCopy").val(sortUrlText);
                    if (navigator.userAgent.indexOf("iPhone") > 0) {
                        $("#fuzhi").hide();
                    }
                }
                $("#myModal").modal("show").css({
                    'margin-top': 200,
                    'margin-left': 100
                });
            }
        });
    });
    //点击马上省处理
    var clipboard = new Clipboard('#copyIt');
    clipboard.on('success', function (e) {
        selectAll(document.getElementById("sortUrlCopy"));
    });
    clipboard.on('error', function (e) {
        selectAll(document.getElementById("sortUrlCopy"));
    });

});
//判断是否为电脑
function isPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
//判断是否为苹果手机
function isIPhone() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["iPhone", "Windows Phone"];
    var flag = false;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = true;
            break;
        }
    }
    return flag;
}


//全选（苹果手机专用）
function selectAll(el) {
    $("#copyIt").focus();
    if (document.body.createTextRange) {
        var textRange = document.body.createTextRange();
        textRange.moveToElementText(el);
        textRange.select();
        textRange.execCommand("Copy");
        tooltip(el, "Copied!");
    } else if (window.getSelection && document.createRange) {
        var range = document.createRange();
        range.selectNodeContents(el);
        var sel = window.getSelection();
        sel.removeAllRanges();
        sel.addRange(range);
        if (el.nodeName == "TEXTAREA" || el.nodeName == "INPUT")
            el.select();
        if (el.setSelectionRange && navigator.userAgent.match(/ipad|ipod|iphone/i))
            el.setSelectionRange(0, 999999);
    }
}

//复制到粘贴板
function copyText(text) {
    var clipboard = new Clipboard('.copy', {
        text: function () {
            return text;
        }
    });
    if (isPC()) {
        alert("复制成功，请粘贴到浏览器购买 ");
    } else {
        alert("复制成功，请打开手机淘宝购买 ");
    }
}



