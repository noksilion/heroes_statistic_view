<form method="post" >
    <tr>Game № ${gameNumber}</tr>
    <tr>Date : ${"date"+gameNumber}</tr>
    <tr>You</tr>
    <tr>
        <td>${youName}</td>
        <td>${youHeroName+gameNumber}</td>
        <td>${youResult+gameNumber}</td>
    </tr>
    <tr>Enemies</tr>
    <#list 1..(enemiesInGame+gameNumber) as enemyNumberInGame>
        <#include "enemy_in_unapproved_game.ftl">
    </#list>
    <button type="submit" formaction="/approve_one_game" >Approve Game</button>
</form>

