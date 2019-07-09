package heroku_demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGameDtoForPost {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("hero_id")
    private Integer heroId;
    private Result result;
}
