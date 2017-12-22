package rest.validation;

import dto.ErrorDto;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.ObjectMapperProvider;

/**
 * Maps Java exceptions to HTTP error responses.
 */
@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {

    private static final Logger log = LoggerFactory.getLogger(InvalidInputExceptionMapper.class);

    private ObjectMapperProvider objectMapperProvider;

    public InvalidInputExceptionMapper() {}

    /**
     * Build a JSON error response based on 401 HTTP status code.
     * @param exception the thrown Java exception
     * @return a response with the error in JSON format
     */
    @Override
    public Response toResponse(InvalidInputException exception) {
        ErrorDto error = new ErrorDto(exception.getError().getErrorCode(), exception.getError().getErrorValue());
        return Response.status(Response.Status.CONFLICT)
                .entity(toJson(error))
                .build();
    }

    private String toJson(final ErrorDto errorDto) {
        String errorJson = null;
        try {
            errorJson = objectMapperProvider.newObjectMapper().writeValueAsString(errorDto);
        } catch (final Exception e) {
            log.error("Failed to serialize the error.", e);
        }
        return errorJson;
    }

    /**
     * A rest object mapper serializes the java object ErrorDto to a json string.
     * @param objectMapperProvider
     */
    @Inject
    public void setObjectMapperProvider(ObjectMapperProvider objectMapperProvider) {
        this.objectMapperProvider = objectMapperProvider;
    }
}
