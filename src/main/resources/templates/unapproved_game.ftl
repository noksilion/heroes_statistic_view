<form method="post" >
    <tr><td>Game № ${gameNumber}</td></tr>
    <tr><td>Date : ${paramsNamesValues["date"+gameNumber]}</td></tr>
    <tr><td>You</td></tr>
    <tr>
        <td>${paramsNamesValues["youName"]}</td>
        <#assign youHeroNameGameNumber = "youHeroName"+gameNumber/>
        <td>${paramsNamesValues[youHeroNameGameNumber]}</td>
        <#assign youResultGameNumber = "youResult"+gameNumber/>
        <td>${paramsNamesValues[youResultGameNumber]}</td>
    </tr>
    <tr><td>Enemies</td></tr>
    <#assign enemiesInGameGameNumber = "enemiesInGame"+gameNumber/>
    <#list 1..(paramsNamesValues[enemiesInGameGameNumber]?number) as enemyNumberInGame>
        <#include "enemy_in_unapproved_game.ftl">
    </#list>
    <#assign gameIdGameNumber = "gameIdInGame"+gameNumber/>
    <input type="hidden" value= "${paramsNamesValues[gameIdGameNumber]}" name="gameIdForApprove" >
    <tr><td><button type="submit" formaction="/approve_one_game" >Approve Game</button></td></tr>
</form>

