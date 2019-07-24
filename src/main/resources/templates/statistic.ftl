<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistic</title>
</head>
<body>

    <table id="statistic2Params">
        <tbody >
            <tr>
                <th>V/S</th>
                <th>User Name</th>
                <th>on</th>
                <th>You Hero</th>
            </tr>
            <tr class="lastTR">
                <td></td>
                <td>
                    <label>
                        <input id="UserName2Params" type="text">
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

<button type="button" onclick="loadStatsHtml2Params('http://localhost:8085/get_stats_html','${token}')">Request data</button>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="/js/loadStatsHtml.js"></script>

</body>
</html>