<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Battle</title>
</head>
<body>
<form modelAttribute="enemyBattles" method="post"  >
        <table>
            <tr>
                <th>Result</th>
                <th>Hero Name</th>
                <th>Enemy Name</th>
                <th>Enemies Quantity - ${enemiesQuantity} </th>
            </tr>
            <tr>
                <td>Enemy</td>
            </tr>

            <input type="hidden" value= "${enemiesQuantity}" name="enemiesQuantity" >

            <#list 1..enemiesQuantity as tag>
                <#include "enemy.ftl">
                <#if tag == 3>
                    <#break>
                </#if>
            </#list>


        </table>

    <button type="submit" formaction="/add_battle/add_enemy" >Add new Enemy</button>
    <button type="submit" formaction="/add_battle">Add battle</button>
</form>
</body>
</html>