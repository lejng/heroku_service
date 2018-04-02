package com.service.phone_confirm;

import com.model.PhoneConfirm;

public interface PhoneConfirmService {
    boolean sendConfirmCode(PhoneConfirm phoneConfirm);
    boolean checkConfirmCode(PhoneConfirm phoneConfirm);
}
