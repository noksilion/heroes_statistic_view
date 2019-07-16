package heroku_demo.api.controllers;

import heroku_demo.api.dto.GameDto;
import heroku_demo.api.dto.UnapprovedGamesDto;
import heroku_demo.api.dto.UserGameDto;
import heroku_demo.api.services.RestRequestServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ApproveGameController {
    private final RestRequestServices restRequestServices;

    @RequestMapping(value = "/approve_one_game" ,method = RequestMethod.POST)
    public ModelAndView approveOneGame(@RequestParam(value = "gameIdForApprove") Integer gameIdForApprove, HttpServletRequest request){

        restRequestServices.approveGame(gameIdForApprove,request);
        return new ModelAndView("redirect:" + "/view_unapproved_games");
    }

    @RequestMapping(value = "/view_unapproved_games", method = RequestMethod.GET)
    public ModelAndView viewUnapprovedGames(HttpServletRequest request, Map<String, Object> model) {
        UnapprovedGamesDto unapprovedGames = restRequestServices.getUnapprovedGames(request);
        if(unapprovedGames.getGameDtos().isEmpty()){
            model.put("noGamesMessage","There no Games For Approve");
            return new ModelAndView("approve_games", model);
        }
        else {
            Integer confirmingUserId = unapprovedGames.getConfirmingUserId();
            List<GameDto> gameDtoList = unapprovedGames.getGameDtos();
            Map<String, String> paramsNamesValues = new HashMap<>();
            model.put("gamesQuantity", gameDtoList.size());
            int gameNumber = 0;
            for (GameDto gameDto : gameDtoList) {
                gameNumber++;
                int enemyNumber = 0;
                int enemiesQuantityInGame = gameDto.getUserGameList().size() - 1;
                paramsNamesValues.put("gameIdInGame" + gameNumber, gameDto.getId().toString());
                paramsNamesValues.put("enemiesInGame" + gameNumber, Integer.toString(enemiesQuantityInGame));
                for (UserGameDto userGameDto : gameDto.getUserGameList()) {
                    if (gameNumber == 1 & userGameDto.getUserId().equals(confirmingUserId)) {
                        paramsNamesValues.put("youName", userGameDto.getUserName());
                        paramsNamesValues.put("youHeroName" + gameNumber, userGameDto.getHeroName());
                        paramsNamesValues.put("youResult" + gameNumber, userGameDto.getResult().toString());
                    } else if (userGameDto.getUserId().equals(confirmingUserId)) {
                        paramsNamesValues.put("youHeroName" + gameNumber, userGameDto.getHeroName());
                        paramsNamesValues.put("youResult" + gameNumber, userGameDto.getResult().toString());
                    } else {
                        enemyNumber++;
                        paramsNamesValues.put("game" + gameNumber + "enemy" + enemyNumber + "Result", userGameDto.getResult().toString());
                        paramsNamesValues.put("game" + gameNumber + "enemy" + enemyNumber + "HeroName", userGameDto.getHeroName());
                        paramsNamesValues.put("game" + gameNumber + "enemy" + enemyNumber + "Name", userGameDto.getUserName());
                    }
                }
                paramsNamesValues.put("date" + gameNumber, gameDto.getDate());
            }
            model.put("paramsNamesValues", paramsNamesValues);
            return new ModelAndView("approve_games", model);
        }
    }


}
