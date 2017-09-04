<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<html>
<head>
    <title>增加弟兄姊妹</title>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>

<body style="position: relative; margin: 0 auto;text-align: center;" id="center">
<div class="col-lg-4 col-lg-offset-4">
    <div class="wrapper-page">
        <div class="m-t-30 card-box">
            <form class="form-signin" id="addUserFrom" name="addUserFrom" role="form" method="post"
                  action="/addSait.do?user_id=${userBo.user_id}">
                <div class="error-box">${msg}</div>
                <input hidden id="groupId" value="${groupId}">
                <input hidden id="type" value="saits">
                <input hidden id="user_id" value="${userBo.user_id}">
                <input class="form-control" name="shoujin_time" maxlength="10" id="shoujin_time"
                       type="text" placeholder="受浸日期" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
                <div style="height: 10px"></div>
                <input class="form-control" placeholder="昵称"   autofocus id="name" name="name">
                <div style="height: 10px"></div>
                <select name="age" id="age" class="form-control" style="height:40px">
                    <option value="">年龄阶段</option>
                    <option value="1">小学</option>
                    <option value="2">初高中</option>
                    <option value="3">大学校园</option>
                    <option value="4">在职(或已毕业)</option>
                    <option value="5">已退休</option>
                </select>
                <div style="height: 10px"></div>
                <%--<input name="shoujin_time" class="form-control" id="shoujin_time" placeholder="受浸日期,默认为今天，可不填" type="text"  id="datetimepicker" data-date-format="yyyy-mm-dd">--%>
                <select name="group_id" id="group_id" class="form-control" style="height:40px">
                    <option value="">小排</option>
                    <c:forEach items="${groupBos}" var="group" varStatus="stat">
                        <option value="${group.group_id}">${group.name}</option>
                    </c:forEach>
                </select>
                <div style="height: 10px"></div>
                <select name="shoujin_local_flag" id="shoujin_local_flag" class="form-control" style="height:40px">
                    <option value="">受浸地点(默认为本地,可不填)</option>
                    <option value="1">本地</option>
                    <option value="0">外地</option>
                </select>
                <div style="height: 10px"></div>

                <select name="sex" id="sex" class="form-control" value="${userBo.sex}" style="height:40px">
                    <option value="">弟兄/姊妹</option>
                    <option value="1">弟兄</option>
                    <option value="0">姊妹</option>
                </select>
                <div style="height: 10px"></div>
                <textarea name="remark" id="remark" class="form-control" style="height:150px"
                          placeholder="备注,可以写传福音的人，情形描述，接触方式"></textarea>
                <div class="row">
                    <div class="col-sm-4 col-xs-8">
                        <button class="btn btn-warning btn-block" onclick="submitForm()" type="button">保存</button>
                    </div>
                    <c:if test="${userBo.user_id!=null}">
                        <br/>
                        <br/>
                        <div class="col-sm-4 col-xs-8">
                            <button class="btn btn-warning btn-block" onclick="delUser()" type="button">删除</button>
                        </div>
                    </c:if>
                    <%--<a href="/delUser.do?user_id=${user.user_id}&groupId=${groupId}&type=addSait">删</a>--%>
                </div>
            </form>

        </div>

    </div>
    <!-- end wrapper page -->
</div>
<script>
    $("#user_id").val("${userBo.user_id}");
    $("#sex").val("${userBo.sex}");
    $("#shoujin_time").val("${userBo.shoujin_time}");
    $("#age").val("${userBo.age}");
    $("#shoujin_local_flag").val("${userBo.shoujin_local_flag}");
    $("#group_id").val("${userBo.group_id}");
    $("#remark").val("${userBo.remark}");
    $("#name").val("${userBo.name}");

    /**
     * 删除用户
     */
    function delUser() {
        var url = "/delUser.do?user_id=${userBo.user_id}&groupId=${groupId}";
        $.ajax({
            url: url,
            success: function (data) {
            }
        });
        alert("已删除");
        location.href= "/userList.do?groupId=${groupId}&type=saits_total_num";
    }

    function submitForm() {
        var name = $("#name").val();
        var shoujin_time = $("#shoujin_time").val();
        var shoujin_local_flag = $("#shoujin_local_flag").val();
        var group_id = $("#group_id").val();
        var remark = $("#remark").val();
//        if (shoujin_time == "") {
//            alert("请填写受浸日期")
//            $("#shoujin_time").focus();
//            return;
//        }
        if (name == "") {
            alert("请填写昵称")
            $("#name").focus();
            return;
        }

        if (shoujin_local_flag == "-1" || shoujin_local_flag == "" || shoujin_local_flag == null) {
            shoujin_local_flag = "1";
            $("#shoujin_local_flag").val("1");
        }

        if ($("#age").val() == "-1" || $("#age").val() == "" || $("#age").val() == null) {
            alert("请选择年龄阶段")
            $("#age").focus();
            return;
        }
        if (group_id == "-1" || group_id == "" || group_id == null) {
            alert("请选择小排")
            $("#group_id").focus();
            return;
        }

        if ($("#sex").val() == "-1" || $("#sex").val() == "" || $("#sex").val() == null) {
            alert("请选择弟兄还是姊妹")
            $("#sex").focus();
            return;
        }
        if ($("#age").val() == "-1" || $("#age").val() == "" || $("#age").val() == null) {
            alert("请选择年龄阶段")
            $("#age").focus();
            return;
        }
        if (group_id == "-1" || group_id == "" || group_id == null) {
            alert("请选择小排")
            $("#group_id").focus();
            return;
        }

        if ($("#sex").val() == "-1" || $("#sex").val() == "" || $("#sex").val() == null) {
            alert("请选择弟兄还是姊妹")
            $("#sex").focus();
            return;
        }

        document.getElementById("addUserFrom").submit();

    }
</script>

</body>
</html>