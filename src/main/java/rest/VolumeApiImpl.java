package rest;

import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.validation.InvalidInputException;
import rest.validation.RainyHillsError;
import service.VolumeService;

public class VolumeApiImpl implements VolumeApi {

    private static final Logger log = LoggerFactory.getLogger(VolumeApiImpl.class);

    private VolumeService volumeService;

    /**
     * Inject the Service that will serve the output volume of the remaining water.
     * @param volumeService
     */
    @Inject
    public void setVolumeService(@Named("volumeService") VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @Override
    public Response getVolume(@NotNull String numbers) {
        log.debug("Get volume of numbers " + numbers);
        Integer[] integers = convertToArray(numbers);
        validate(integers);
        int volume = volumeService.getVolume(integers);
        return Response.status(Response.Status.OK)
                .entity(volume)
                .build();
    }

    private Integer[] convertToArray(String numbers) {
        try {
            return Arrays.stream(numbers.split("[.*\\s*,]"))
                    .parallel()
                    .filter(character -> !(character == null || character.trim().isEmpty()))
                    .mapToInt(character -> Integer.parseInt(character.trim()))
                    .boxed()
                    .toArray(Integer[]::new);
        } catch(NumberFormatException exc) {
            throw new InvalidInputException("Given invalid array of integers",
                    RainyHillsError.INVALID_INPUT);
        }
    }

    private void validate(Integer[] integers) {
        if(integers == null || integers.length == 0) {
            throw new InvalidInputException("Given empty array of integers",
                    RainyHillsError.INVALID_INPUT);
        }
    }
}
