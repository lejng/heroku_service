package com.validator;

import com.dao.PhoneConfirmDao;
import com.model.PhoneConfirm;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserValidator {
    @Autowired
    private PhoneConfirmDao phoneConfirmDao;

    public Map<String, Object> getUserErrors(User user){
        Map<String, Object> body = new HashMap<>();
        body = putInMapIfNotNull("ConfirmCode",getConfirmCodeErrors(user), body);
        body = putInMapIfNotNull("Phone", getPhoneErrors(user.getPhone()), body);
        body = putInMapIfNotNull("Name", getNameErrors(user.getName()), body);
        body = putInMapIfNotNull("Surname", getSurnameErrors(user.getSurname()), body);
        return  putInMapIfNotNull("Password", getPasswordErrors(user.getPassword()), body);
    }

    public String getConfirmCodeErrors(User user){
        PhoneConfirm rightPhoneConfirm = phoneConfirmDao.getByPhone(user.getPhone());
        if(rightPhoneConfirm == null || !rightPhoneConfirm.getConfirmCode().equals(user.getConfirmCode())){
            return "check confirm code field";
        }
        return null;
    }

    public String getPhoneErrors(String phone){
        if(phone == null || phone.length() < 9){
            return "check phone field";
        }
        return null;
    }

    public String getNameErrors(String name){
        if(name == null || name.length() < 3){
            return "check name field";
        }
        return null;
    }

    public String getSurnameErrors(String surname){
        if(surname == null || surname.length() < 3){
            return "check surname field";
        }
        return null;
    }

    public String getPasswordErrors(String password){
        if(password == null || password.length() < 4){
            return "check password field";
        }
        return null;
    }

    public Map<String, Object> putInMapIfNotNull(String key, Object value, Map<String, Object> map){
        if(value != null){
            map.put(key, value);
        }
        return map;
    }
}
