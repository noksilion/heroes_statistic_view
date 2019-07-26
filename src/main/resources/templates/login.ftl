
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<#if message??>
    <h2>${message}</h2>
</#if>
<form action="/login" method="get">
    Email: <input type="text" name="email" /><br/>
    Password: <input type="text" name="password" /><br/>
    <input type="submit"/>
</form>
<a href="/" title="Go To Main Page">Go To Main Page</a>

<button type="button" onclick="test()">Request data</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="/js/test.js"></script>
</body>
</html>