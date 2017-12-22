package rest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The REST API methods, responsible to expose data to the clients.
 */
@Path("/volume")
public interface VolumeApi {

    /**
     * Validates the input parameter and Gets a JSON Response which may contain one of the following.
     * <ul>
     *     <li>an integer that represents the volume of water which remained after the rain, in units or</li>
     *     <li>an errorDto object in case the client has sent an invalid input</li>
     * </ul>
     * @param numbers
     *          an array of integers in String format
     * @return a JSON Response with either the volume result or an error
     */
    @GET
    @Path("{numbers}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getVolume(@NotNull @PathParam("numbers") String numbers);
}
