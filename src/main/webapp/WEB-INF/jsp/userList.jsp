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



    <table class="grain_table" border="1" >
        <tr>
            <td colspan="3" style="background-color: darkorange">【${loginName}】${typeName}</td>
        </tr>
        <tr>
            <td>序号</td>
            <td>昵称</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${userBoList}" var="user" varStatus="stat">
        <tr>
            <td>${ stat.index + 1}</td>
            <td>${user.name}</td>
            <c:if test="${user.shoujin_flag==1}">
                <td>
                    <a href="/gotoAddUser.do?user_id=${user.user_id}&groupId=${groupId}&type=addSait">详细</a>

                </td>
            </c:if>
            <c:if test="${user.shoujin_flag!=1}">
                <td>
                    <a href="/gotoAddUser.do?user_id=${user.user_id}&groupId=${groupId}&type=addFriend">详细</a>

                </td>
            </c:if>
        </tr>
        </c:forEach>
    </table>
    <br/>

    <br/>
    <br/>
</div>
</body>
</html>
