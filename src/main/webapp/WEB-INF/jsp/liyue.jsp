<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<html>
<head>
    <title>立约</title>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>

<body style="position: relative; margin: 0 auto;top: 50px;text-align: center;" id="center">
<div class="col-lg-4 col-lg-offset-4">
    <div class="wrapper-page">
        <div class="m-t-30 card-box">
            <form class="form-signin" id="liyue" name="addUserFrom" role="form" method="post" action="/liyue.do?meeting_id=${meeting_id}">
                <div class="error-box">${msg}</div>
                <input hidden id="groupId" value="${groupId}"/>
                <input hidden id="type" value="liyue"/>
                <input hidden id="meeting_id" value="${meeting_id}"/>
                <div id="group_copy">
                </div>
                <div class="row">
                    <div class="col-sm-4 col-xs-8">
                        <button id="add" class="btn btn-warning btn-block" onclick="addLine(this)" type="button">继续增加</button>
                    </div>
                </div>

                <div style="height: 10px"></div>
                <div class="row">
                    <div class="col-sm-4 col-xs-8">
                        <button class="btn btn-warning btn-block" onclick="submitForm()" type="button">保存</button>
                    </div>
                </div>

            </form>
        </div>

    </div>
    <!-- end wrapper page -->
</div>
<script>
    $("#group_id").val("${group_id}");
    $("#remark").val("${userBo.remark}");

    var pub_index = 0;
    /**
     * 初化小组
     */
    function initGroup(index) {
        var url = "/getAllSmallGroups.do?groupId=${groupId}";
        $.ajax({
            url: url,
            success: function (data) {
                var group_ids = eval(data);
                $("#group_id" + index).empty();
                $('#group_id' + index).append("<option value='' >所属小排</option>");
                $.each(group_ids, function (i, item) {
                    // 这样每个item是一个json对象，
                    $('#group_id' + index).append("<option value='" + item.group_id + "' >" + item.name + "</option>");
                })
            }
        });
    }
    /**
     * 获取小排下的人
     */
    function queryUserList(group) {

        var index = group.id.split("group_id")[1];
        var group_id = group.value;
        var url = "/getUsersByGroupId.do?groupId=${groupId}&group_id=" + group_id;
        $.ajax({
            url: url,
            success: function (data) {
                var users = eval(data);
                $("#users" + index).empty();
                $.each(users, function (i, item) {
                    // 这样每个item是一个json对象，
                    $('#users' + index).append("<option value='" + item.user_id + "' >" + item.name + "</option>");
                })
            }
        });
    }

    addLine(null);
    initGroup(1);
    /**
     * 增加行
     */
    function addLine(obj) {
        var isFirst=true;
        if(obj){
            isFirst=false;
        }
        var index = pub_index;
        if (!isFirst && ($("#group_id" + index).val() == "" || $("#group_id" + index).val() == undefined)) {
            $("#group_id" + index).focus();
            alert("请选择小排");
            return;
        }
        if (!isFirst && ($("#users" + index).val() == "" || $("#users" + index).val() == undefined)) {
            $("#users" + index).focus();
            alert("请选择立约人");
            return;
        }
        if (!isFirst && ($("#user_li_yue_type" + index).val() == "" || $("#user_li_yue_type" + index).val() == undefined)) {
            $("#user_li_yue_type" + index).focus();
            alert("请选择立约人类型");
            return;
        }
        pub_index++;
        index = pub_index;
        var html = "          <div style=\"height: 10px\"></div>\n" +
                "                    <select name=\"group_id" + index + "\" id=\"group_id" + index + "\"  onchange=\"queryUserList(this)\" class=\"form-control\"\n" +
                "                            style=\"height:40px\">\n" +
                "                        <option value=\"\">所属小排</option>\n" +
                "                    </select>\n" +
                "                    <div style=\"height: 10px\"></div>\n" +
                "                    <select name=\"users" + index + "\" id=\"users" + index + "\" class=\"form-control\" style=\"height:40px\">\n" +
                "                        <option value=\"\">立约人</option>\n" +
                "                    </select>\n" +
                "                    <div style=\"height: 10px\"></div>\n" +
                "                    <select name=\"user_li_yue_type" + index + "\" id=\"user_li_yue_type" + index + "\" class=\"form-control\" style=\"height:40px\">\n" +
                "                        <option value=\"\">立约人的类型</option>\n" +
                "                        <option value=\"1\">帮手</option>\n" +
                "                        <option value=\"2\">新人</option>\n" +
                "                        <option value=\"3\">福音朋友</option>\n" +
                "                        <option value=\"4\">新得救</option>\n" +
                "                        <option value=\"5\">久不聚会</option>\n" +
                "                        <option value=\"6\">新迁入</option>\n" +
                "                        <option value=\"7\">亲人</option>\n" +
                "                        <option value=\"8\">其他</option>\n" +
                "                    </select>\n" +
                "                    <div style=\"height: 10px\"></div>\n" +
                "                <textarea name=\"remark" + index + "\" id=\"remark" + index + "\" class=\"form-control\" style=\"height:150px\"\n" +
                "                          placeholder=\"备注,情形描述等\"></textarea>\n<hr />"
        $("#group_copy").append(html);
        initGroup(index);
    }
    /**
     * 提交
     */
    function submitForm() {
        document.getElementById("liyue").submit();
    }
</script>

</body>
</html>