package roguezero.web;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorMessageTest {

    @Test
    public void shouldCreateErrorMessageObjectWithMessageAndCode() {

        ErrorMessage error = new ErrorMessage(500, "Internal Server Error");

        assertEquals((int) error.code, 500);
        assertEquals(error.message, "Internal Server Error");

    }

}
