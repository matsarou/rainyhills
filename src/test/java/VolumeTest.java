import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import rest.ObjectMapperProvider;
import rest.VolumeApiImpl;
import rest.validation.InvalidInputException;
import service.VolumeServiceImpl;

@RunWith(CdiRunner.class)
public class VolumeTest {

    @Inject
    private VolumeServiceImpl service;

    @Inject
    private VolumeApiImpl api;

    @Inject
    ObjectMapperProvider objectMapperProvider;

    @Test
    public void testSucceedToObtainVolume1() {
        Response response = api.getVolume("3, 2,4, 1,2");
        Assert.assertNotNull(response);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertNotNull(response.getEntity());
        Assert.assertEquals(2, (int) response.getEntity());
    }

    @Test
    public void testSucceedToObtainVolume2() {
        Response response = api.getVolume("4,1,1 0,2 3");
        Assert.assertNotNull(response);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertNotNull(response.getEntity());
        Assert.assertEquals(8, (int) response.getEntity());
    }

    @Test(expected = NullPointerException.class)
    public void testFailToObtainVolumeWithNullPointerException() {
        api.getVolume(null);
    }

    @Test(expected = InvalidInputException.class)
    public void testFailToObtainVolumeBecauseOfEmptyInput() {
        Response response = api.getVolume("");
        Assert.assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
    }

    @Test(expected = InvalidInputException.class)
    public void testFailToObtainVolumeBecauseOfInvalidCharactersInInput() {
        Response response = api.getVolume("5,yy,7");
        Assert.assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
    }

    @Test(expected = InvalidInputException.class)
    public void testFailToObtainVolumeBecauseOfNonIntegerInput() {
        Response response = api.getVolume(",");
        Assert.assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
    }
}
