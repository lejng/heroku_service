package test;

import org.junit.Assert;
import org.junit.Test;

public class TestControllerAction {
    private static final String HOST = "https://service228.herokuapp.com";//"http://localhost:8080/";
    private static final String LOGIN_URL = HOST + "/login";
    private static final String ACCOUNT_URL = HOST + "/account";
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
