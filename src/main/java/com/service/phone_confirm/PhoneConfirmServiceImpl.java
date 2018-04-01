package com.service.phone_confirm;

import com.dao.PhoneConfirmDao;
import com.model.PhoneConfirm;
import com.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PhoneConfirmServiceImpl implements PhoneConfirmService{
    @Autowired
    private PhoneConfirmDao phoneConfirmDao;
    @Autowired
    private SmsUtils smsUtils;

    @Override
    public void sendConfirmCode(PhoneConfirm phoneConfirm) {
        PhoneConfirm phoneConfirmOld = phoneConfirmDao.getByPhone(phoneConfirm.getPhone());
        if(phoneConfirmOld != null) {
            phoneConfirm.setId(phoneConfirmOld.getId());
        }
        phoneConfirm.setConfirmCode(generateCode());
        smsUtils.sendSMS(phoneConfirm.getConfirmCode(),phoneConfirm.getPhone());
        phoneConfirmDao.saveOrUpdate(phoneConfirm);
    }

    @Override
    public boolean checkConfirmCode(PhoneConfirm phoneConfirm) {
        PhoneConfirm rightPhoneConfirm = phoneConfirmDao.getByPhone(phoneConfirm.getPhone());
        if(rightPhoneConfirm != null && rightPhoneConfirm.getConfirmCode().equals(phoneConfirm.getConfirmCode())){
            return true;
        }
        return false;
    }

    public String generateCode(){
        Random rand = new Random();
        String code = "";
        for(int i = 0; i < 4; i++){
            code += rand.nextInt(10);
        }
        return code;
    }
}
