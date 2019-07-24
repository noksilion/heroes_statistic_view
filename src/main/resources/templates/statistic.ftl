<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistic</title>
</head>
<body>

    <table>
        <tbody >
            <tr>
                <th>V/S</th>
                <th>Enemy Email</th>
                <th>on</th>
                <th>Enemy Hero</th>
            </tr>
            <tr id="statisticBy2ParamsLastTr">
                <td></td>
                <td>
                    <label>
                        <input id="Email2Params" type="text">
                    </label>
                </td>
                <td></td>
                <td>
                    <label>
                        <select id = "EnemyHero2Params">
                            <#list heroes as hero>
                               <option value=${hero.id}>${hero.name}</option>
                            </#list>
                        </select>
                    </label>
                </td>
            </tr >
        </tbody>
    </table>

    <button type="button" onclick="loadStatsHtml2Params()">Request data</button>


    <table>
        <tbody >
        <tr>
            <th>V/S</th>
            <th>Enemy Email</th>
        </tr>
        <tr id="statisticByEmailLastTr">
            <td></td>
            <td>
                <label>
                    <input id="EmailParam" type="text">
                </label>
            </td>
        </tr >
        </tbody>
    </table>

    <button type="button" onclick="loadStatsHtmlByEmail()">Request data</button>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="/js/loadStatsHtml.js"></script>


</body>
</html>