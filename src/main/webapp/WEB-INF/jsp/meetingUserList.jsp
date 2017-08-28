<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>

<!DOCTYPE>
<html>
<head>
    <title>人员列表</title>
    <%@include file="/WEB-INF/include/header.jsp"%>
</head>

<body>
<%--查询框--%>

<div class="container-fluid main-content">



    <table class="lord_table" border="1" >
        <tr>
            <td colspan="3" style="background-color: darkorange">【${loginName}】${typeName}</td>
        </tr>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>${typeName}</td>
        </tr>
        <c:forEach items="${userBoList}" var="user" varStatus="stat">

        <tr>
            <td>${ stat.index + 1}</td>
            <td>${user.name}</td>
            <td>
            <c:if test="${user.lord_flag==1}">
                    <input name="lord_flag"  value="${user.user_id}" onclick="lord_flag_checkbox(this)" type="checkbox" checked />
            </c:if>

            <c:if test="${user.lord_flag!=1}">
                    <input name="lord_flag"  value="${user.user_id}"  onclick="lord_flag_checkbox(this)"  type="checkbox"  />
            </c:if>
            </td>

        </tr>
        </c:forEach>
    </table>
    <br/>


</div>
<script>
    function lord_flag_checkbox(lord_flag_checkbox){
        var user_id=lord_flag_checkbox.value;
        var isAttended=lord_flag_checkbox.checked;
        var url="/operateMeeting.do?user_id="+user_id+"&isAttended="+isAttended+"&meeting_id="+${meeting_id};
        $.ajax({
            url:url,
            type:'POST',
            async: true,
            success: function( status) {
                alert(status);
            }
        });
    }
</script>
</body>
</html>
