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
    <table class="grain_table" border="1">
        <tr>
            <td colspan="3" style="background-color: #e38d13">${groupShuoMing}${loginName}</td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td>名称</td>
            <td>人数</td>
            <td>操作</td>
        </tr>

        <tr style="background-color: #f8efc0">
            <td>受浸</td>
            <td><a href="/userList.do?groupId=${groupId}&type=new_saits_total_num">${new_saits_total_num}人</a></td>
            <td><a href="/operate.do?groupId=${groupId}&type=new_saits_total_num">增加</a></td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td>福音朋友</td>
            <td><a href="/userList.do?groupId=${groupId}&type=friends_num">${friends_num}人</a></td>
            <td><a href="/operate.do?groupId=${groupId}&type=friends_num">增加</a></td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td >总圣徒</td>
            <td><a href="/userList.do?groupId=${groupId}&type=saits_total_num">${saits_total_num}人</a></td>
            <td><a href="/operate.do?groupId=${groupId}&type=saits_total_num">增加</a></td>
        </tr>
        <c:forEach items="${meetingBoList}" var="meetingBo" varStatus="stat">
            <tr>
                <td>${meetingBo.meeting_name}</td>
                <td><a href="/userList.do?groupId=${groupId}&type=meeting&meeting_id=${meetingBo.meeting_id}">${meetingBo.meeting_num}人</a>
                    <c:if test="${meetingBo.liyue_flag==1}">
                        /<a href="/userList.do?groupId=${groupId}&type=meeting&meeting_id=${meetingBo.meeting_id}">${meetingBo.liYue_num}人</a>
                    </c:if>

                </td>
                <td>
                    <c:if test="${meetingBo.liyue_flag==1}">
                        <a href="/operate.do?groupId=${groupId}&type=meeting_liyue&meeting_id=${meetingBo.meeting_id}">签到</a>
                    </c:if>
                    <c:if test="${meetingBo.liyue_flag!=1}">
                        <a href="/operate.do?groupId=${groupId}&type=meeting&meeting_id=${meetingBo.meeting_id}">签到</a>
                    </c:if>

                </td>
            </tr>
            <tr>
                <td style="background-color: #f8efc0">${meetingBo.meeting_name}比例</td>
                <td colspan="2" style="background-color: #f8efc0">
                ${meetingBo.meeting_percent}%
                    <c:if test="${meetingBo.liyue_flag==1}">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/gotoLiyue.do?groupId=${groupId}&type=meeting&meeting_id=${meetingBo.meeting_id}">立约</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>


    </table>
    <br/>

    <c:forEach items="${childGroupNumBoList}" var="chindGroupNum" varStatus="stat">
        <table class="grain_table" border="1">
            <tr>
                <td colspan="3" style="background-color: darkorange"><a
                        href="/index.do?groupId=${chindGroupNum.group_id}">${chindGroupNum.group_name}</a></td>
            </tr>
            <tr style="background-color: #f8efc0">
                <td>名称</td>
                <td>人数</td>
                <td>操作</td>
            </tr>
            <tr style="background-color: #f8efc0">
                <td>受浸</td>
                <td>
                    <a href="/userList.do?groupId=${chindGroupNum.group_id}&type=new_saits_total_num">${chindGroupNum.new_saits_total_num}人</a>
                </td>
                <td><a href="/operate.do?groupId=${chindGroupNum.group_id}&type=new_saits_total_num">增加</a></td>
            </tr>
            <tr style="background-color: #f8efc0">
                <td>福音朋友</td>
                <td><a href="/userList.do?groupId=${chindGroupNum.group_id}&type=friends_num">${chindGroupNum.friends_num}人</a></td>
                <td><a href="/operate.do?groupId=${chindGroupNum.group_id}&type=friends_num">增加</a></td>
            </tr>
            <tr style="background-color: #f8efc0">
                <td>总圣徒</td>
                <td><a href="/userList.do?groupId=${chindGroupNum.group_id}&type=saits_total_num">${chindGroupNum.saits_total_num}人</a></td>
                <td><a href="/operate.do?groupId=${chindGroupNum.group_id}&type=saits_total_num">增加</a></td>
            </tr>
            <%--<c:forEach items="${chindGroupNum.meetingBoList}" var="meetingBo1" varStatus="stat">--%>
                <%--<tr>--%>
                    <%--<td>${meetingBo1.meeting_name}</td>--%>
                    <%--<td><a href="/userList.do?groupId=${chindGroupNum.group_id}&type=meeting&meeting_id=${meetingBo1.meeting_id}">${meetingBo1.meeting_num}人</a></td>--%>
                    <%--<td><a href="/operate.do?groupId=${chindGroupNum.group_id}&type=meeting&meeting_id=${meetingBo1.meeting_id}">签到</a></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td style="background-color: #f8efc0">${meetingBo1.meeting_name}比例</td>--%>
                    <%--<td colspan="2" style="background-color: #f8efc0">${meetingBo1.meeting_percent}%</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>

            


        </table>
        <br/>
    </c:forEach>
</div>

</body>
</html>
