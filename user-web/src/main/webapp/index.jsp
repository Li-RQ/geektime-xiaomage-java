<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
<title>My Home Page</title>
</head>
<body>
	<div class="container-lg">
		<!-- Content here -->
		Hello,World 2021
	</div>
<p><a href="user/register">用户注册</a></p>
<p><a href="user/login">用户登录</a></p>
<p><a href="jolokia/list" target="_blank" >查询所有MBean</a></p>
<p><a href="jolokia/read/org.geektimes.projects.user.management:type=User" target="_blank" >查询UserMBean</a></p>
<p><a href="jolokia/write/org.geektimes.projects.user.management:type=User/Id/1234" target="_blank" >UserMBean Id 设置为1234</a></p>
<p><a href="jolokia/write/org.geektimes.projects.user.management:type=User/Email/1234@test.com" target="_blank" >UserMBean Email 设置为1234@test.com</a></p>
</body>