


<tr>
    <td>Enemy</td>
</tr>
<#--listEnemyRequestParamsNames[tag-1].result-->
<tr>
    <td>
        <label>
            <select name = "${"enemy"+enemyNumber+"Result"}" >
                <#assign resultKey = "enemy"+enemyNumber+"Result"/>
                <#if enemyParamsNamesValues??>
                    <#if enemyNumber != enemiesQuantity || reducingEnemy ??>
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
            <select name = "${"enemy"+enemyNumber+"HeroName"}">
                <#assign heroNameKey = "enemy"+enemyNumber+"HeroName"/>
                <#list heroes as hero>
                    <#if enemyParamsNamesValues??>
                        <#if enemyNumber != enemiesQuantity || reducingEnemy ??>
                            <#if enemyParamsNamesValues[heroNameKey]=hero.name>
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
            <#if enemyNumber != enemiesQuantity || reducingEnemy ??>
                <#assign nameKey = "enemy"+enemyNumber+"Name"/>
                <input type="text" name="${"enemy"+enemyNumber+"Name"}" value="${enemyParamsNamesValues[nameKey]}" /><br/>
            <#else>
                    <input type="text" name="${"enemy"+enemyNumber+"Name"}"/><br/>
            </#if>
        <#else>
            <input type="text" name="${"enemy"+enemyNumber+"Name"}"/><br/>
        </#if>
    </td>
</tr>