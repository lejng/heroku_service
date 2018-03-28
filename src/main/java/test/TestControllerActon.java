package test;

import org.junit.Assert;
import org.junit.Test;

public class TestControllerActon {
    private static final String LOGIN_URL = "https://service228.herokuapp.com/login";
    private static final String ACCOUNT_URL = "https://service228.herokuapp.com/account";
    private String phone = "+375293111716";
    private String password = "1234";

    @Test
    public void testLogin(){
        String token = Request.getToken(LOGIN_URL, phone, password);
        Assert.assertNotNull("Empty token", token);
        Assert.assertTrue("Wrong token", token.contains("Bearer"));
    }

    @Test
    public void testGetAccountInfo(){
        String token = Request.getToken(LOGIN_URL, phone, password);
        String userJson = Request.callGetRequest(ACCOUNT_URL, token);
        Assert.assertTrue("Wrong user info for phone: " + phone, userJson.contains(phone));
    }
}
