package heroku_demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroDto {
    private  Integer id;
    @Email(message = "it must be email")
    private  String name;
    @NotNull(message = "casle Id can not be null")
    private  Integer castleId;
}
