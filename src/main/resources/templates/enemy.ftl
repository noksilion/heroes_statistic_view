<tr>
    <td>
        <label>
            <select name = "result" >
                <option>${loose}</option>
                <option>${victory}</option>
                <option>${halfVictory}</option>
            </select>
        </label>
    </td>
    <td>
        <label>
            <select name = "heroName">
                <#list heroes as hero>
                    <option >${hero.name}</option>
                </#list>
            </select>
        </label>
    </td>
    <td>
        <input type="text" name="name" /><br/>
    </td>
</tr>