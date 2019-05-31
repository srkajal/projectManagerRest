package org.kajal.mallick.model.response;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class BaseResponseTest {
    private BaseResponse baseResponse;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        baseResponse = new BaseResponse("success", 1, "message1");
    }

    @Test
    public void getStatus() {
        String status = "success1";
        baseResponse.setStatus(status);
        assertEquals(status, baseResponse.getStatus());
    }

    @Test
    public void getCode() {
        Integer code = new Integer(2);
        baseResponse.setCode(code);
        assertEquals(code, baseResponse.getCode());
    }

    @Test
    public void getMessage() {
        String message = "message2";
        baseResponse.setMessage(message);
        assertEquals(message, baseResponse.getMessage());
    }
}