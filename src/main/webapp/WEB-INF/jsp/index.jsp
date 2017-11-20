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
        <tr>
            <td colspan="3" style="background-color: #e38d13">${groupShuoMing}${loginName}
                <c:if test="${seesionGroupBo.group_level==10}">
                    <a href="/smallGroups.do?groupId=${groupId}">小排情况</a>
                </c:if>
            </td>
        </tr>
        <c:if test="${seesionGroupBo.group_level==10}">
            <tr style="background-color: #f8efc0">
                <td><a href="/index.do?time_add_flag=-1"><--上周</a></td>
                <td><a href="/index.do?time_add_flag=1">下周--></a></td>
            </tr>
            <tr style="background-color: #f8efc0">
                <td>${beginDate}</td>
                <td>${endDate}</td>
            </tr>
        </c:if>

        <tr style="background-color: #f8efc0">
            <td>名称</td>
            <td>人数</td>
        </tr>

        <tr style="background-color: #f8efc0">
            <td>新受浸</td>
            <td><a href="/userList.do?groupId=${groupId}&type=new_saits_total_num">${new_saits_total_num}人</a></td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td>福音朋友</td>
            <td><a href="/userList.do?groupId=${groupId}&type=friends_num">${friends_num}人</a></td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td>总圣徒</td>
            <td><a href="/userList.do?groupId=${groupId}&type=saits_total_num">${saits_total_num}人</a></td>
        </tr>
        <tr style="background-color: #f8efc0">
            <td>久不聚会</td>
            <td><a href="/userList.do?groupId=${groupId}&type=jbjh_saits_num">${jbjh_saits_num}人</a></td>
        </tr>

        <c:forEach items="${meetingBoList}" var="meetingBo" varStatus="stat">
            <tr>
                <td>${meetingBo.meeting_name}</td>
                <td>
                    <a href="/userList.do?groupId=${groupId}&type=meeting&meeting_id=${meetingBo.meeting_id}">${meetingBo.meeting_num}人</a>
                    <c:if test="${meetingBo.liyue_flag==1}">
                        /<a href="/userList.do?groupId=${groupId}&type=meeting&meeting_id=${meetingBo.meeting_id}">${meetingBo.liYue_num}人</a>
                    </c:if>

                </td>

            </tr>
            <tr>
                <td style="background-color: #f8efc0">${meetingBo.meeting_name}比例</td>
                <td style="background-color: #f8efc0">
                        ${meetingBo.meeting_percent}%
                </td>
            </tr>
        </c:forEach>


    </table>
    <br/>

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
            <tr style="background-color: #f8efc0">
                <td>久不聚会</td>
                <td>
                    <a href="/userList.do?groupId=${childGroupNum.group_id}&type=jbjh_saits_num">${childGroupNum.jbjh_saits_num}人</a>
                </td>

            </tr>
            <c:forEach items="${childGroupNum.meetingBoList}" var="meetingBo1" varStatus="stat">
                <tr>
                    <td>${meetingBo1.meeting_name}</td>
                    <td>
                        <a href="/userList.do?groupId=${childGroupNum.group_id}&type=meeting&meeting_id=${meetingBo1.meeting_id}">${meetingBo1.meeting_num}人</a>
                    </td>
                        <%--<td><a href="/operate.do?groupId=${childGroupNum.group_id}&type=meeting&meeting_id=${meetingBo1.meeting_id}">签到</a></td>--%>
                </tr>
                <tr>
                    <td style="background-color: #f8efc0">${meetingBo1.meeting_name}比例</td>
                    <td style="background-color: #f8efc0">${meetingBo1.meeting_percent}%</td>
                </tr>
            </c:forEach>


        </table>
        <br/>
    </c:forEach>
</div>

</body>
</html>
