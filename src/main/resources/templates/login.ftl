
<!DOCTYPE html>
<html lang="en" xmlns:form="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Create new student</title>
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
</body>
</html>