package heroku_demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserDtoForPost {
    @NotNull(message = "name can not be null")
    @Size(min = 1, max = 20,message = "size must be no more than 20 letters")
    private  String userName;
    @Email(message = "please provide valid email")
    private  String email;
    @NotNull(message = "password can not be null")
    @Size(min = 1, max = 10, message = "size must be no more than 10 letters")
    @NotBlank(message = "password can not have 0 symbols")
    private  String password;
}
