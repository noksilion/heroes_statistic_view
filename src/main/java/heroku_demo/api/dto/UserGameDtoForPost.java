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
    @NotNull(message = "user_id can not be null")
    private Integer userId;

    @JsonProperty("hero_id")
    @NotNull(message = "hero_id can not be null")
    private Integer heroId;

    @NotNull(message = "result can not be null")
    private Result result;
}
