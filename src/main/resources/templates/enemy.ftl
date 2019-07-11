<tr>
    <td>Enemy</td>
</tr>
<tr>
    <td>
        <label>
            <select name = "${listEnemyRequestParamsNames[tag-1].result}" >
                <#assign resultKey = listEnemyRequestParamsNames[tag-1].result/>
                <#if enemyParamsNamesValues??>
                    <#if tag != enemiesQuantity>
                        <#if enemyParamsNamesValues[resultKey] = "Loose" ><option selected="selected">${loose}</option>
                            <#else ><option>${loose}</option></#if>
                        <#if enemyParamsNamesValues[resultKey] ="HalfVictory"><option selected="selected">${halfVictory}</option>
                            <#else ><option>${halfVictory}</option></#if>
                        <#if enemyParamsNamesValues[resultKey] ="Victory"><option selected="selected">${victory}</option>
                            <#else ><option>${victory}</option></#if>
                    <#else>
                        <option>${loose}</option>
                        <option>${halfVictory}</option>
                        <option>${victory}</option>
                    </#if>
                <#else>
                    <option>${loose}</option>
                    <option>${halfVictory}</option>
                    <option>${victory}</option>
                </#if>
            </select>
        </label>
    </td>
    <td>
        <label>
            <select name = "${listEnemyRequestParamsNames[tag-1].heroName}">
                <#assign nameKey = listEnemyRequestParamsNames[tag-1].heroName/>
                <#list heroes as hero>
                    <#if enemyParamsNamesValues??>
                        <#if tag != enemiesQuantity>
                            <#if enemyParamsNamesValues[nameKey]=hero.name>
                                <option selected="selected">${hero.name}</option>
                            <#else>
                                <option>${hero.name}</option>
                            </#if>
                        <#else>
                            <option>${hero.name}</option>
                        </#if>
                    <#else>
                        <option>${hero.name}</option>
                    </#if>
                </#list>
            </select>
        </label>
    </td>
    <td>
        <#if enemyParamsNamesValues??>
            <#if tag != enemiesQuantity>
                <#assign resultKey = listEnemyRequestParamsNames[tag-1].name/>
                <input type="text" name="${listEnemyRequestParamsNames[tag-1].name}" value="${enemyParamsNamesValues[resultKey]}" /><br/>
            <#else>
                    <input type="text" name="${listEnemyRequestParamsNames[tag-1].name}"/><br/>
            </#if>
        <#else>
            <input type="text" name="${listEnemyRequestParamsNames[tag-1].name}"/><br/>
        </#if>
    </td>
</tr>