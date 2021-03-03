<head>
    <jsp:directive.include
            file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <title>My Home Page</title>
</head>
<body>
<div class="container-lg">
    <p> 用户注册 </p>
    <form method="post" action="register">
        <p>用户名：<input type="text" name="name" placeholder="请输入用户名"></p>
        <p>密  码：<input type="password" name="password" placeholder="请输入密码"></p>
        <p>手  机：<input type="text" name="phoneNumber" placeholder="请输入手机号码"></p>
        <p>邮  箱：<input type="email" name="email" placeholder="请输入邮箱"></p>
        <%=request.getAttribute("error") == null? "": "<p style='color: red'>" + request.getAttribute("error") + "</p>"%>
        <p><input type="submit" value="提交"></p>
    </form>
</div>
</body>