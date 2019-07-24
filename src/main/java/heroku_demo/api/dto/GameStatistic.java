package heroku_demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameStatistic {
    private Integer gamesCount;
    private Integer winsCount;
    private Integer looseCount;
    private Float winPercent;
}
