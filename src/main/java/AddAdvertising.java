import com.dao.AdvertisingDao;
import com.dao.UserAdvertisingDao;
import com.dao.UserHibernateDao;
import com.model.Advertising;
import com.model.User;
import com.model.UserAdvertising;

import java.util.List;

public class AddAdvertising {
    public static void main(String[] args){
        Advertising advertising = new Advertising();
        advertising.setLink("link1");
        advertising.setCost(5.0);
        advertising.setTimer(20);
        addAdvertising(advertising);
    }

    public static void addAdvertising(Advertising advertising){
        AdvertisingDao advertisingDao = new AdvertisingDao();
        Integer advertisingId = advertisingDao.insert(advertising);
        List<User> users = new UserHibernateDao().getAll();
        UserAdvertisingDao userAdvertisingDao = new UserAdvertisingDao();
        for(User user : users){
            UserAdvertising userAdvertising = new UserAdvertising();
            userAdvertising.setUserId(user.getId());
            userAdvertising.setAdvertisingId(advertisingId);
            userAdvertisingDao.insert(userAdvertising);
        }
    }
}
