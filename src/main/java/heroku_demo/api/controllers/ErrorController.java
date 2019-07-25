package heroku_demo.api.controllers;

import heroku_demo.api.dto.BaseErrorDTO;
import heroku_demo.api.exceptions.RestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "heroku_demo")
public class ErrorController {

    @ExceptionHandler(RestApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private BaseErrorDTO accessDeniedException(RestApiException e) {
        BaseErrorDTO errorDTO = mapException(e, HttpStatus.BAD_REQUEST);
        errorDTO.setMessage(e.getMessage());
        return errorDTO ;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    private BaseErrorDTO internalError(Exception e) {
        return mapException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private BaseErrorDTO mapException(Exception e, HttpStatus status) {
        String msg = e.getMessage().isEmpty() ? e.getClass().toString() : e.getMessage();
        return new BaseErrorDTO(status, msg, e.getMessage());
    }
}
