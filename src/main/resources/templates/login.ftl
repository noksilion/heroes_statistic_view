
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<div class="container">

    <input id="restHost" type="hidden" value="${restHost}"  />
    <input id="appHost" type="hidden" value="${appHost}"  />
    <div class="staticInfo">
        <div class ="hommLogo">
            <img src="/img/homm.png" alt="image"/>
        </div>

        <div class="introInfo">
            <img src="/img/introInfo.png" alt="introInfo">
        </div>
    </div>


    <div class ="login-signup" >
        <h3 id="textTitle">Войти</h3>
        <input id="Email" type="text" name="email" placeholder="Электронная почта" />
        <input id="Password" type="text" name="password" placeholder="Пароль" />
        <button id="sendButton" type="button" onclick="login()" >Войти</button>
        <h3 id="textOr">или</h3>
        <button id="registrationButton" type="button" onclick="changeToSignup()" >Регистрация</button>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="/js/changeLogin-Signup.js"></script>
</body>
</html>