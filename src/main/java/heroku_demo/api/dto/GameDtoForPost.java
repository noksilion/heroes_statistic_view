package heroku_demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDtoForPost {
    @JsonProperty("players")
    @Valid
    @NotNull(message = "players can not be null")
    private List<UserGameDtoForPost> userGameList;
}
