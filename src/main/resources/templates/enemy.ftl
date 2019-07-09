<tr>
    <td>Enemy</td>
</tr>
<tr>
    <td>
        <label>
            <select name = "${listEnemyRequestParamsNames[tag-1].result}" >
                <option>${loose}</option>
                <option>${victory}</option>
                <option>${halfVictory}</option>
            </select>
        </label>
    </td>
    <td>
        <label>
            <select name = "${listEnemyRequestParamsNames[tag-1].heroName}">
                <#list heroes as hero>
                    <option >${hero.name}</option>
                </#list>
            </select>
        </label>
    </td>
    <td>
        <input type="text" name="${listEnemyRequestParamsNames[tag-1].name}" /><br/>
    </td>
</tr>