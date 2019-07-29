<tr class="${"enemy"+enemyNumber+"Tr"} enemy">
    <td>Enemy</td>
</tr>
<tr id="${"enemy"+enemyNumber+"Tr"}" class="${"enemy"+enemyNumber+"Tr"}  enemy">
    <td>
        <label>
            <select id = "${"enemy"+enemyNumber+"Result"}" >
                <option value="LOOSE">${loose}</option>
                <option value="HALF_VICTORY">${halfVictory}</option>
                <option value="VICTORY">${victory}</option>
            </select>
        </label>
    </td>
    <td>
        <label>
            <select id = "${"enemy"+enemyNumber+"HeroName"}">
                <#list heroes as hero>
                    <option value="${hero.id}">${hero.name}</option>
                </#list>
            </select>
        </label>
    </td>
    <td>
        <label>
            <select id = "${"enemy"+enemyNumber+"EmailParam"}">
                <#list users as user>
                    <option value=${user.id}>${user.email}</option>
                </#list>
            </select>
        </label>
    </td>
</tr>