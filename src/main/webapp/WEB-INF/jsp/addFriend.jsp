<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<html>
<head>
    <title>增加福音朋友</title>
    <%@include file="/WEB-INF/include/header.jsp"%>
</head>

<body style="position: relative; margin: 0 auto;top: 50px;text-align: center;" id="center">
<div class="col-lg-4 col-lg-offset-4" >
    <div class="wrapper-page">
        <div class="m-t-30 card-box">
            <form class="form-signin" id="addUserFrom" name="addUserFrom" role="form" method="post" action="/addFriend.do?user_id=${userBo.user_id}">
                <div class="error-box">${msg}</div>
                <input hidden id="groupId" value="${groupId}">
                <input hidden id="type" value="friend">
                <input hidden  id="user_id" value="${userBo.user_id}">
                <input class="form-control" placeholder="昵称" id="name"  name="name" required autofocus>
                <div style="height: 10px"></div>

                <select name="age"   id="age" class="form-control" style="height:40px">
                    <option value="">年龄阶段</option>
                    <option value="1">小学</option>
                    <option value="2">初高中</option>
                    <option value="3">大学校园</option>
                    <option value="4">在职(或已毕业)</option>
                    <option value="5">已退休</option>
                </select>
                <div style="height: 10px"></div>
                <%--<input name="shoujin_time" class="form-control" id="shoujin_time" placeholder="受浸日期,默认为今天，可不填" type="text"  id="datetimepicker" data-date-format="yyyy-mm-dd">--%>
                <select name="group_id" id="group_id"  class="form-control" style="height:40px">
                    <option value="">小排</option>
                    <c:forEach items="${groupBos}" var="group" varStatus="stat">
                        <option value="${group.group_id}">${group.name}</option>
                    </c:forEach>
                </select>
                <div style="height: 10px"></div>
                <select name="sex"  id="sex"  class="form-control" style="height:40px">
                    <option value="">性别</option>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
                <div style="height: 10px"></div>
                <textarea name="remark"   id="remark"  class="form-control" style="height:150px"  placeholder="备注,可以写传福音的人，情形描述，接触方式"></textarea>
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
    $("#user_id").val("${userBo.user_id}");
    $("#sex").val("${userBo.sex}");
    $("#age").val("${userBo.age}");
    $("#group_id").val("${userBo.group_id}");
    $("#remark").val("${userBo.remark}");
    $("#name").val("${userBo.name}");
function submitForm(){
    var name=$("#name").val();
    var group_id=$("#group_id").val();
    var remark=$("#remark").val();
    if(name==""){
        alert("请填写昵称")
        $("#name").focus();
        return;
    }
    if($("#age").val()=="-1"||$("#age").val()==""||$("#age").val()==null){
        alert("请选择年龄阶段")
        $("#age").focus();
        return;
    }
    if(group_id=="-1"||group_id==""||group_id==null){
        alert("请选择小排")
        $("#group_id").focus();
        return;
    }

    if($("#sex").val()=="-1"||$("#sex").val()==""||$("#sex").val()==null){
        alert("请选择弟兄还是姊妹")
        $("#sex").focus();
        return;
    }

    document.getElementById("addUserFrom").submit();
}
</script>

</body>
</html>