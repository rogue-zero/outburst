package roguezero.web;

import org.junit.Test;
import spark.Response;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PublicCorsTest {

    @Test
    public void shouldApplyRightAccessControls() {
        Response res = mock(Response.class);

        Cors cors = new Cors();
        cors.applyCors(res);

        verify(res).header(eq("Access-Control-Allow-Methods"), anyString());
        verify(res).header(eq("Access-Control-Allow-Origin"), anyString());
        verify(res).header(eq("Access-Control-Allow-Headers"), anyString());
        verify(res).header(eq("Access-Control-Allow-Credentials"), anyString());
    }

}
