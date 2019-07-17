<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
</head>
<body>
<#if message??>
    <h2>${message}</h2>
</#if>
<form action="/signup" method="get">
    Email: <input type="text" name="email" /><br/>
    Password: <input type="text" name="password" /><br/>
    UserName:<input type="text" name="userName" /><br/>
    <input type="submit"/>
</form>
<a href="/" title="Go To Main Page"></a>
</body>
</html>