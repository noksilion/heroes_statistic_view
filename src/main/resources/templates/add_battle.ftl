<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Battle</title>
</head>
<body>
<form method="post"  >

        <#if message??>
            <h3>${message}</h3>
        </#if>

        <table>
            <tr>
                <th>Result</th>
                <th>Hero Name</th>
                <th>Enemy Name</th>
                <th>Enemies Quantity - ${enemiesQuantity} </th>
            </tr>
            <tr>
                <td>You</td>
            </tr>
            <tr>
                <td>
                    <label>
                        <select name = "youResult" >
                            <#if youResult="loose"><option selected="selected">${loose}</option>
                                <#else ><option>${loose}</option></#if>
                            <#if youResult="halfVictory"><option selected="selected">${halfVictory}</option>
                                <#else ><option>${halfVictory}</option></#if>
                            <#if youResult="victory"><option selected="selected">${victory}</option>
                                <#else ><option>${victory}</option></#if>
                        </select>
                    </label>
                </td>
                <td>
                    <label>
                        <select name = "youHero">
                            <#list heroes as hero>
                                <#if youHero=hero.name><option selected="selected">${hero.name}</option>
                                    <#else><option>${hero.name}</option></#if>
                            </#list>
                        </select>
                    </label>
                </td>
            </tr>
            <input type="hidden" value= "${enemiesQuantity}" name="enemiesQuantity" >

            <#list 1..enemiesQuantity as enemyNumber>
                <#include "templates/enemy.ftl">
            </#list>

        </table>


    <button type="submit" formaction="/add_battle/add_enemy" >Add new Enemy</button>
    <#if enemiesQuantity gt 1><button type="submit" formaction="/add_battle/reduce_enemy" >Reduce Enemy</button></#if>
    <button type="submit" formaction="/add_battle">Add battle</button>
</form>
</body>
</html>