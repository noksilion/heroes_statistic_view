package heroku_demo.api.services;

import heroku_demo.api.dto.EnemyRequestParamsNames;
import heroku_demo.api.dto.HeroDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ViewServices {
    private final RestTemplate restTemplate;
    private RestRequestServices restRequestServices;

    public void viewBattle(HttpServletRequest request, Map<String, Object> model, Integer enemiesQuantity) {

        model.put("victory", "Victory");
        model.put("loose", "Loose");
        model.put("halfVictory", "Half Victory");
        model.put("enemiesQuantity", enemiesQuantity);
        EnemyRequestParamsNames[] arrEnemyRequestParamsNames = new EnemyRequestParamsNames[enemiesQuantity];
        for (int i = 0; i < enemiesQuantity; i++) {
            arrEnemyRequestParamsNames[i] = (EnemyRequestParamsNames.builder()
                    .heroName("enemy" + (i+1) + "HeroName")
                    .name("enemy" + (i+1) + "Name")
                    .result("enemy" + (i+1) + "Result")
                    .build()
            );
        }
        model.put("listEnemyRequestParamsNames", arrEnemyRequestParamsNames);
        List<HeroDto> heroDtos = restRequestServices.getAllHeroes(request);
        model.put("heroes", heroDtos);

    }

}
