package heroku_demo.api.services;

import heroku_demo.api.dto.EnemyRequestParamsNames;
import heroku_demo.api.dto.HeroDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ViewServices {
    private RestRequestServices restRequestServices;

    public void viewBattle(HttpServletRequest request, Map<String, Object> model, Integer enemiesQuantity,String message) {

        model.put("victory", "Victory");
        model.put("loose", "Loose");
        model.put("halfVictory", "Half Victory");
        model.put("enemiesQuantity", enemiesQuantity);
        model.put("youResult","");
        model.put("youHero","");
        if(message!=null){
            model.put("message",message);
        }
        EnemyRequestParamsNames[] arrEnemyRequestParamsNames = new EnemyRequestParamsNames[enemiesQuantity];
        for (int i = 0; i < enemiesQuantity; i++) {
            String heroNameParam = "enemy" + (i+1) + "HeroName";
            String nameParam = "enemy" + (i+1) + "Name";
            String resultParam = "enemy" + (i+1) + "Result";
            arrEnemyRequestParamsNames[i] = (EnemyRequestParamsNames.builder()
                    .heroName(heroNameParam)
                    .name(nameParam)
                    .result(resultParam)
                    .build()
            );
            model.put(heroNameParam,"");
            model.put(nameParam,"");
            model.put(resultParam,"");
        }
        model.put("listEnemyRequestParamsNames", arrEnemyRequestParamsNames);
        List<HeroDto> heroDtos = restRequestServices.getAllHeroes(request);
        model.put("heroes", heroDtos);

    }

    public void viewBattleWithAddedEnemy(HttpServletRequest request, Map<String, Object> model, Integer enemiesQuantity,String message,Map<String, String> allRequestParams) {

        model.put("victory", "Victory");
        model.put("loose", "Loose");
        model.put("halfVictory", "Half Victory");
        model.put("enemiesQuantity", enemiesQuantity);
        model.put("youResult",allRequestParams.get("youResult"));
        model.put("youHero",allRequestParams.get("youHero"));
        if(message!=null){
            model.put("message",message);
        }
        //todo remove EnemyRequestParamsNames[]
        EnemyRequestParamsNames[] arrEnemyRequestParamsNames = new EnemyRequestParamsNames[enemiesQuantity];
        Map<String,String> enemyParamsNamesValues = new HashMap<>();
        for (int i = 0; i < enemiesQuantity; i++) {
            String heroNameParam = "enemy" + (i+1) + "HeroName";
            String nameParam = "enemy" + (i+1) + "Name";
            String resultParam = "enemy" + (i+1) + "Result";
            arrEnemyRequestParamsNames[i] = (EnemyRequestParamsNames.builder()
                    .heroName(heroNameParam)
                    .name(nameParam)
                    .result(resultParam)
                    .build()
            );

            enemyParamsNamesValues.put(heroNameParam, allRequestParams.get(heroNameParam));
            enemyParamsNamesValues.put(nameParam, allRequestParams.get(nameParam));
            enemyParamsNamesValues.put(resultParam, allRequestParams.get(resultParam));

        }
        model.put("enemyParamsNamesValues",enemyParamsNamesValues);
        model.put("listEnemyRequestParamsNames", arrEnemyRequestParamsNames);
        List<HeroDto> heroDtos = restRequestServices.getAllHeroes(request);
        model.put("heroes", heroDtos);

    }
}
