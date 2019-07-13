<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
                <#include "unapproved_game.ftl">
            </#list>

        </table>
    <#else >
        <h2>${noGamesMessage}</h2>
    </#if>
</body>
</html>