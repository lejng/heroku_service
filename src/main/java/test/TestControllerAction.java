package test;

import com.config.WebSecurityConfig;
import com.controllers.AdvertisingController;
import com.controllers.UserController;
import com.dao.AdvertisingDao;
import com.dao.UserHibernateDao;
import com.model.Advertising;
import com.model.User;
import com.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestControllerAction {
    private static final String HOST = /*"https://service228.herokuapp.com";*/"http://localhost:8080/";
    private static final String LOGIN_URL = HOST + WebSecurityConfig.LOGIN;
    private String phone = "+375293111716";
    private String password = "1234";
    private UserHibernateDao userDao = new UserHibernateDao();
    private AdvertisingDao advertisingDao = new AdvertisingDao();

    @Test
    public void testLogin(){
        String token = Request.getToken(LOGIN_URL, phone, password);
        Assert.assertNotNull("Empty token", token);
        Assert.assertTrue("Wrong token", token.contains("Bearer"));
    }

    @Test
    public void testGetAccountInfo(){
        String token = Request.getToken(LOGIN_URL, phone, password);
        Map<String, Object> actualUser = JsonUtils.convertJsonToMap(Request.callGetRequest(HOST + UserController.ACCOUNT_INFO, token));
        User expectedUser = userDao.getByPhone(phone);
        Assert.assertEquals("Wrong phone",expectedUser.getPhone(),actualUser.get("phone"));
        Assert.assertEquals("Wrong name",expectedUser.getName(),actualUser.get("name"));
        Assert.assertEquals("Wrong surname",expectedUser.getSurname(),actualUser.get("surname"));
        Assert.assertEquals("Wrong balance",expectedUser.getBalance(),actualUser.get("balance"));
    }

    @Test
    public void testGetAvailableAdvertisingsForView(){
        String token = Request.getToken(LOGIN_URL, phone, password);
        List<Advertising> expectedAdvertisings = advertisingDao.getAvailableAdvertising(userDao.getByPhone(phone).getId());
        List<Object> actualAdvertisings = (List)JsonUtils.convertJsonTo(Request.callGetRequest(HOST + AdvertisingController.AVAILABLE_ADVERTISING_FOR_VIEW, token), List.class);
        Assert.assertEquals("Wrong size", expectedAdvertisings.size(), actualAdvertisings.size());
        for(int i = 0; i < expectedAdvertisings.size(); i++){
            Assert.assertEquals(expectedAdvertisings.get(i).toString(), actualAdvertisings.get(i).toString());
        }
    }
}
