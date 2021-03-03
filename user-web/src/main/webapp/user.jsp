<%@ page import="org.geektimes.projects.user.domain.User" %>
<head>
    <jsp:directive.include
            file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <title>My Home Page</title>
</head>
<body>
<%
    User user = (User)session.getAttribute("curUser");
%>
<div class="container-lg">
    <!-- Content here -->
   <p>用户信息</p>
    <p>id：<%=user.getId()%></p>
    <p>用户名：<%=user.getName()%></p>
    <p>手机：<%=user.getPhoneNumber()%></p>
    <p>邮箱：<%=user.getEmail()%></p>
</div>
</body>