<%@ page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
	<head>		
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
	</head>
	<body>
		<h1>欢迎登陆EasyMall</h1>
		<form action="${pageContext.request.contextPath}/user/login" method="POST">
			<table>
				<tr>
				<%
					Cookie[] cookies=request.getCookies();
					Cookie remeCk=null;
					for(Cookie ck:cookies){
						if(ck.getName().equals("remname")){
							remeCk=ck;
							/* break; */
						}
					}
					String username="";
					if(remeCk!=null){
						username=remeCk.getValue();
						username=URLDecoder.decode(username,"utf-8");
					}
				 %>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="${cookie.remeCk.value }"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="false"/>记住用户名
						<input type="checkbox" name="autologin" value="false"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="1">
					</td>
					<td colspan="2">
						<a href="${pageContext.request.contextPath}/merchant_register.jsp" style="text-decoration: none;color:#3c8dbc">商家入驻，前往注册</a>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登 陆"/>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds" style="color: red;padding:0px 0px 0px 70px">
					  	${LoginMsg }
					</td>  
				</tr>
			</table>
		</form>		
	</body>
</html>
