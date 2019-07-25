<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistic</title>
</head>
<body>
    <div>
        <h2 id="statisticBy2ParamsError"></h2>
    </div>
    <table>
        <tbody >
            <tr>
                <th>V/S</th>
                <th>Enemy Email</th>
                <th>on</th>
                <th>You Hero</th>
            </tr>
            <tr id="statisticBy2ParamsLastTr">
                <td></td>
                <td>
                    <label>
                        <select id = "Email2Params">
                            <#list users as user>
                                <option value=${user.id}>${user.email}</option>
                            </#list>
                        </select>
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

    <button type="button" onclick="loadStatsHtml2Params('${link}')">Request data</button>



    <div>
        <h2 id="statisticByEmailError"></h2>
    </div>
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
                    <select id = "EmailParam">
                        <#list users as user>
                            <option value=${user.id}>${user.email}</option>
                        </#list>
                    </select>
                </label>
            </td>
        </tr >
        </tbody>
    </table>

    <button type="button" onclick="loadStatsHtmlByEmail('${link}')">Request data</button>

    <div>
        <h2 id="statisticByHeroError"></h2>
    </div>

    <table>
        <tbody >
            <tr>
                <th>Win Rate</th>
                <th>Hero Name</th>
            </tr>
            <tr id="statisticByHeroLastTr">
                <td></td>
                <td>
                    <label>
                        <select id = "YouHeroParam">
                            <#list heroes as hero>
                                <option value=${hero.id}>${hero.name}</option>
                            </#list>
                        </select>
                    </label>
                </td>
            </tr >
        </tbody>
    </table>


    <button type="button" onclick="loadStatsHtmlByHero('${link}')">Request data</button>


    <div>
        <h2 id="globalWinRateError"></h2>
    </div>

    <table>
        <tbody >
        <tr id="globalWinRateTr">
            <th></th>
            <th>Global Win Rate</th>
        </tr>
        </tbody>
    </table>

    <button type="button" onclick="loadStatsHtmlGlobalUserWinRate('${link}')">Request data</button>

    <br>
    <a href="/" title="Go To Main Page">Go To Main Page</a>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="/js/loadStatsHtml.js"></script>


</body>
</html>