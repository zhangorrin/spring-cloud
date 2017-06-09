<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <script type="text/javascript" src="/js/jquery.min.js" charset="utf-8"></script>

</head>
<body>
    <form action="/pages/login"  method="post" id="loginForm">
        <ul>
            <li>
                <input type="text"  placeholder="用户名/手机号" name="username"/>
            </li>
            <li>
                <input type="password" placeholder="登录密码" name="password" />
            </li>
            <li>
                <input type="checkbox" name="rememberMe" />记住登录账号
                <div><a href="/registered/passWordUrl">忘记密码</a></div>
            </li>
            <li>
                <div>
                    <input type="text"  placeholder="请输入右侧字符" name="verifyCode" />
                    <img alt="" src="/captcha/getVerifyCodeImage" id="verifyCodeImage" alt="验证码" width="100px" height="40px" />
                    <a href="javascript:void(0)" id="changeVerify">换一张</a>
                </div>
                <#if error??>
                    <div><span id="error_info">${error}</span></div>
                </#if>
            </li>
            <li><input type="submit" id="btn_login" value="提交" /></li>
        </ul>
    </form>
</body>
</html>