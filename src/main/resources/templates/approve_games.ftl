<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Approve Game</title>
</head>
<body>

    <#if !noGamesMessage??>
        <h2>Unapproved Games</h2>

        <table>

            <tr>
                <th>User Name</th>
                <th>Hero Name</th>
                <th>Result</th>
                <th>Games Quantity - ${gamesQuantity} </th>
            </tr>

            <#list 1..gamesQuantity as gameNumber>
                <#include "templates/unapproved_game.ftl">
            </#list>

        </table>
    <#else >
        <h2>${noGamesMessage}</h2>
    </#if>
    <a href="/" title="Go To Main Page">Go To Main Page</a>
</body>
</html>