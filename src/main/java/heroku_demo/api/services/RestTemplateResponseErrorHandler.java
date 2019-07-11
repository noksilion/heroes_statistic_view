package heroku_demo.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import heroku_demo.api.dto.BaseErrorDTO;
import heroku_demo.api.exceptions.ForbiddenException;
import heroku_demo.api.exceptions.RestApiException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@AllArgsConstructor
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
        }
        else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                BaseErrorDTO baseError = objectMapper.readValue(StreamUtils.copyToString(httpResponse.getBody(), Charset.defaultCharset()), BaseErrorDTO.class);
                throw new ForbiddenException(baseError.getMessage());
            } else {
                BaseErrorDTO baseError = objectMapper.readValue(StreamUtils.copyToString(httpResponse.getBody(), Charset.defaultCharset()), BaseErrorDTO.class);
                throw new RestApiException (baseError.getMessage());
            }
        }
    }
}

