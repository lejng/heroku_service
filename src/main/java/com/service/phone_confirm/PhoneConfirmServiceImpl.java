package com.service.phone_confirm;

import com.dao.PhoneConfirmDao;
import com.model.PhoneConfirm;
import com.utils.CodeGenerator;
import com.utils.SmsUtils;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneConfirmServiceImpl implements PhoneConfirmService{
    @Autowired
    private PhoneConfirmDao phoneConfirmDao;
    @Autowired
    private SmsUtils smsUtils;
    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean sendConfirmCode(PhoneConfirm phoneConfirm) {
        if(userValidator.getPhoneErrors(phoneConfirm.getPhone()) != null) {
            return false;
        }
        PhoneConfirm phoneConfirmOld = phoneConfirmDao.getByPhone(phoneConfirm.getPhone());
        if(phoneConfirmOld != null) {
            phoneConfirm.setId(phoneConfirmOld.getId());
        }
        phoneConfirm.setConfirmCode(CodeGenerator.generateCode());
        smsUtils.sendSMS(phoneConfirm.getConfirmCode(),phoneConfirm.getPhone());
        phoneConfirmDao.saveOrUpdate(phoneConfirm);
        return true;
    }

    @Override
    public boolean checkConfirmCode(PhoneConfirm phoneConfirm) {
        if(userValidator.getPhoneErrors(phoneConfirm.getPhone()) != null) {
            return false;
        }
        PhoneConfirm rightPhoneConfirm = phoneConfirmDao.getByPhone(phoneConfirm.getPhone());
        if(rightPhoneConfirm != null && rightPhoneConfirm.getConfirmCode().equals(phoneConfirm.getConfirmCode())){
            return true;
        }
        return false;
    }
}
