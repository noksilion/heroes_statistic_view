<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistic</title>
</head>
<body>
<div id="table" >
    <table >
        <tr>
            <th>V/S</th>
            <th>User Name</th>
            <th>on</th>
            <th>You Hero</th>
        </tr>
        <tr>
            <td></td>
            <td>
                <label>
                    <input id="UserName2Params" type="text">
                </label>
            </td>
            <td></td>
            <td>
                <label>
                    <select id = "EnemyHero">
                        <#list heroes as hero>
                           <option>${hero.name}</option>
                        </#list>
                    </select>
                </label>
            </td>
        </tr>

    </table>
</div>


<button type="button" onclick="loadDoc('http://localhost:8080/stats','${token}')">Request data</button>

<p id="demo"></p>

<script type="text/javascript">
    function loadDoc(url,token) {
        var http = new XMLHttpRequest();
        var str = 'Bearer ';
        var tokenString = str.concat(token);
        var userName = document.getElementById("UserName2Params").value;
        var heroName = document.getElementById("EnemyHero").value;
        var params = 'userId=1&heroId=1';
        http.open('POST', url, true);

//Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        http.setRequestHeader("Authorization",tokenString);

        http.onreadystatechange = function() {//Call a function when the state changes.
            alert(http.responseText);
        }
        http.send(params);
    }
</script>

</body>
</html>