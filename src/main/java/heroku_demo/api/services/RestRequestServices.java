package heroku_demo.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heroku_demo.api.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestRequestServices {

    @Value("${restApplicationHost}")
    private String restApplicationHost;

    private final RestTemplate restTemplate;

    public RestRequestServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GameStatistic getStatistic(HttpServletRequest request,Integer userId,Integer heroId){

        HttpHeaders headers = createHttpHeadersWithToken(request);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restApplicationHost+"/stats")
                .queryParam("userId", userId)
                .queryParam("heroId", heroId);
        ResponseEntity<GameStatistic> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, entity, GameStatistic.class);
        return response.getBody();
    }

    public void approveGame(Integer gameId,HttpServletRequest request){
        HttpHeaders headers = createHttpHeadersWithToken(request);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restApplicationHost+"/games/approve_game")
                .queryParam("user_game_id", gameId);
        ResponseEntity<UnapprovedGamesDto> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, entity, UnapprovedGamesDto.class);
    }

    public UnapprovedGamesDto getUnapprovedGames(HttpServletRequest request){
        HttpHeaders headers = createHttpHeadersWithToken(request);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UnapprovedGamesDto> response = restTemplate.exchange(
                restApplicationHost+"/games/unapproved_games", HttpMethod.GET, entity, UnapprovedGamesDto.class);

        return response.getBody();
    }

    public void addTokenToResponse(String email,String password, HttpServletResponse response){
        AccountCredentials accountCredentials = new AccountCredentials();
        accountCredentials.setEmail(email);
        accountCredentials.setPassword(password);
        HttpEntity<AccountCredentials> request = new HttpEntity<>(accountCredentials);

        ResponseEntity<TokenDto> exchange = restTemplate.exchange(restApplicationHost+"/login", HttpMethod.POST, request, TokenDto.class);

        response.addCookie(new Cookie("token",exchange.getBody().getToken()));
    }

    public void addTokenToResponse(String email,String password, String userName, HttpServletResponse response){
        UserDtoForPost userDtoForPost  = new UserDtoForPost(userName,email,password);

        HttpEntity<UserDtoForPost> request = new HttpEntity<>(userDtoForPost);

        ResponseEntity<TokenDto> exchange = restTemplate.exchange(restApplicationHost+"/singup", HttpMethod.POST, request, new ParameterizedTypeReference<TokenDto>() {});

        response.addCookie(new Cookie("token",exchange.getBody().getToken()));
    }


    public Cookie getCookieByName(Cookie[] cookies,String cookieName){
        Cookie tokenCookie = null;
        if (cookies != null) {
            for (Cookie cookieInList : cookies) {
                if (cookieInList.getName().equals(cookieName)) {
                    tokenCookie = cookieInList;
                }
            }
        }
        //todo check if cookie is not null
        return tokenCookie;
    }

    public void saveBattleResults(Map<String,String> allRequestParams,HttpServletRequest request){
        List<UserGameDtoForPost> enemyBattleDtos = new ArrayList<>();
        Map<String,Integer> heroMap = convertHeroListToMap(getAllHeroes(request));
        enemyBattleDtos.add(UserGameDtoForPost.builder()
                .heroId(heroMap.get(allRequestParams.get("youHero")))
                .result(convertStringToResult(allRequestParams.get("youResult")))
                .userId(getLoggedUserId(request))
                .build()
        );
        for( int i=0; i<(allRequestParams.size()-3)/3;i++){
            enemyBattleDtos.add(UserGameDtoForPost.builder()
                    .heroId(heroMap.get(allRequestParams.get("enemy" + (i+1) + "HeroName")))
                    .userId(getUserIdByEmail(request,allRequestParams.get("enemy" + (i+1) + "Name")))
                    .result(convertStringToResult(allRequestParams.get("enemy" + (i+1) + "Result")))
                    .build()
            );
        }
        GameDtoForPost gameDtoForPost = new GameDtoForPost(enemyBattleDtos);
        String json="";
        try {
            json = new ObjectMapper().writeValueAsString(gameDtoForPost);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = createHttpHeadersWithToken(request);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json,headers);

        restTemplate.postForEntity(restApplicationHost+"/games", entity, String.class);
    }

    public List<HeroDto> getAllHeroes(HttpServletRequest request){
        HttpEntity entity = new HttpEntity(createHttpHeadersWithToken(request));
        ResponseEntity<List<HeroDto>> exchange = restTemplate.exchange(restApplicationHost+"/heroes", HttpMethod.GET, entity, new ParameterizedTypeReference<List<HeroDto>>() {});
        return exchange.getBody();
    }

    public List<UserDtoViewAllUsers> getAllUsers(HttpServletRequest request){
        HttpEntity entity = new HttpEntity(createHttpHeadersWithToken(request));
        ResponseEntity<List<UserDtoViewAllUsers>> exchange = restTemplate.exchange(restApplicationHost+"/users/all_users", HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserDtoViewAllUsers>>() {});
        return exchange.getBody();
    }

    public Integer getLoggedUserId(HttpServletRequest request){
        HttpEntity entity = new HttpEntity(createHttpHeadersWithToken(request));
        ResponseEntity<Integer> exchange = restTemplate.exchange(restApplicationHost+"/users/logged_user_id", HttpMethod.GET, entity, new ParameterizedTypeReference<Integer>() {});
        return exchange.getBody();
    }

    public Integer getUserIdByEmail(HttpServletRequest request,String email){
        HttpEntity entity = new HttpEntity(createHttpHeadersWithToken(request));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restApplicationHost+"/users/user_id_by_email")
                .queryParam("email", email);
        ResponseEntity<Integer> exchange = restTemplate.exchange(builder.buildAndExpand(new HashMap<>()).toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<Integer>() {});
        return exchange.getBody();
    }

    private HttpHeaders createHttpHeadersWithToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = getCookieByName(cookies, "token");

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenCookie.getValue());
        return headers;
    }

    private Map<String,Integer> convertHeroListToMap(List<HeroDto> heroList){
        Map<String,Integer> heroMap = new HashMap<>();
        heroList.forEach((hero)->heroMap.put(hero.getName(),hero.getId()));
        return heroMap;
    }

    private Result convertStringToResult(String result){
        //todo check if String result is correct
        if(result.equals("Half Victory")){
            return Result.HALF_VICTORY;
        }
        return Result.valueOf(result.toUpperCase());
    }

}
