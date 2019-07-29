<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Battle</title>
</head>
<body>
    <input type="hidden" id="enemiesQuantity" value="0">
    <input type="hidden" id="youId" value="${youId}">

        <div>
            <h2 id="Message"></h2>
        </div>

        <table>
            <tr>
                <th>Result</th>
                <th>Hero Name</th>
                <th>Enemy Name</th>
                <th id="enemiesQuantityVisual">Enemies Quantity - 0 </th>
            </tr>
            <tr>
                <td>You</td>
            </tr>
            <tr id = "youTr">
                <td>
                    <label>
                        <select id = "youResult" >
                            <option value="LOOSE">${loose}</option>
                            <option value="HALF_VICTORY">${halfVictory}</option>
                            <option value="VICTORY">${victory}</option>
                        </select>
                    </label>
                </td>
                <td>
                    <label>
                        <select id = "youHero">
                            <#list heroes as hero>
                                <option value="${hero.id}">${hero.name}</option>
                            </#list>
                        </select>
                    </label>
                </td>
            </tr>

        </table>


    <button id = "addEnemy" type="button" onclick="getEnemyHtml('${applicationHost}')">Add new Enemy</button>

    <button type="button" onclick="addBattle('${restApplicationHost}')">Add battle</button>

<a href="/" title="Go To Main Page">Go To Main Page</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="/js/loadEnemyHtml.js"></script>

<script>
    window.onload = function() {
        getEnemyHtml('${applicationHost}');
    };
</script>
</body>
</html>