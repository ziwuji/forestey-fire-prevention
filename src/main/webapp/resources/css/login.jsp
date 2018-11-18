<%--
  Created by IntelliJ IDEA.
  User: fuguanhui
  Date: 2018/10/24
  Time: 下午12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>森林防火视频监控管理系统登录</title>
    <%@include file="common/header.jsp"%>
    <link rel="stylesheet" href="/resource/css/user.css" type="text/css" />
</head>
<body>

<div class="container-fluid">
    <div class="userform">
        <!-- Default form login -->
        <form class="text-center border border-light p-5">

            <p class="h4 mb-4">登录</p>

            <!-- Email -->
            <input type="email" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="E-mail">

            <!-- Password -->
            <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password">

            <div class="d-flex justify-content-around">
                <div>
                    <!-- Remember me -->
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                        <label class="custom-control-label" for="defaultLoginFormRemember">记住我</label>
                    </div>
                </div>
                <div>
                    <!-- Forgot password -->
                    <a href="">Forgot password?</a>
                </div>
            </div>

            <!-- Sign in button -->
            <button class="btn btn-block my-4 btn-primary" type="submit">登录</button>

            <!-- Register -->
            <p>忘记密码?
                <a href="/user/register">注册</a>
            </p>



        </form>
        <!-- Default form login -->
    </div>
</div>
</body>
</html>
