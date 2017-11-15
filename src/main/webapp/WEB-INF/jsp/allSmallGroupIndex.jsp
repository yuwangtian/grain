<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>

<!DOCTYPE>
<html>
<head>
    <title>首页 - 禾捆</title>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>

<body>
<%--查询框--%>

<div class="container-fluid main-content">
    <table class="index_table" border="1">
        <tr style="background-color: #f8efc0">
            <td><a href="/smallGroups.do?time_add_flag=-1"><--上周</a></td>
            <td><a href="/smallGroups.do?time_add_flag=1">下周--></a></td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td>${beginDate}</td>
            <td>${endDate}</td>
        </tr>
    </table>

    <c:forEach items="${childGroupNumBoList}" var="childGroupNum" varStatus="stat">
        <table class="index_table" border="1">
            <tr>
                <td colspan="3" style="background-color: #e38d13"><a
                        href="/index.do?groupId=${childGroupNum.group_id}">${childGroupNum.group_name}</a></td>
            </tr>
            <tr style="background-color: #f8efc0">
                <td>名称</td>
                <td>人数</td>

            </tr>
            <tr style="background-color: #f8efc0">
                <td>新受浸</td>
                <td>
                    <a href="/userList.do?groupId=${childGroupNum.group_id}&type=new_saits_total_num">${childGroupNum.new_saits_total_num}人</a>
                </td>

            </tr>
            <tr style="background-color: #f8efc0">
                <td>福音朋友</td>
                <td>
                    <a href="/userList.do?groupId=${childGroupNum.group_id}&type=friends_num">${childGroupNum.friends_num}人</a>
                </td>

            </tr>
            <tr style="background-color: #f8efc0">
                <td>总圣徒</td>
                <td>
                    <a href="/userList.do?groupId=${childGroupNum.group_id}&type=saits_total_num">${childGroupNum.saits_total_num}人</a>
                </td>

            </tr>
            <c:forEach items="${childGroupNum.meetingBoList}" var="meetingBo1" varStatus="stat">
                <tr>
                    <td>${meetingBo1.meeting_name}</td>
                    <td>
                        <a href="/userList.do?groupId=${childGroupNum.group_id}&type=meeting&meeting_id=${meetingBo1.meeting_id}">${meetingBo1.meeting_num}人</a>
                    </td>
                </tr>
                <tr>
                    <td style="background-color: #f8efc0">${meetingBo1.meeting_name}比例</td>
                    <td colspan="2" style="background-color: #f8efc0">${meetingBo1.meeting_percent}%</td>
                </tr>
            </c:forEach>
        </table>
        <br/>
    </c:forEach>
</div>

</body>
</html>
