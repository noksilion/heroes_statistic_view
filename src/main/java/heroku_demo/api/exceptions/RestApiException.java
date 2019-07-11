package heroku_demo.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class RestApiException extends RuntimeException {
    private final String message;
}
