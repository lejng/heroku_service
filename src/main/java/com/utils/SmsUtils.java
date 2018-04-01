package com.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsUtils {
    private static final PropertyHelper smsProperty = new PropertyHelper("sms.properties");
    private static String ACCOUNT_SID;
    private static String AUTH_TOKEN;
    private static String NUMBER_FROM;

    public SmsUtils(){
        NUMBER_FROM = smsProperty.getProperty("number_from");
        ACCOUNT_SID = smsProperty.getProperty("account_sid");
        AUTH_TOKEN = smsProperty.getProperty("auth_token");
    }

    public void sendSMS(String sms, String numberTo){
        sendSMS(sms, numberTo, NUMBER_FROM);
    }

    public void sendSMS(String sms, String numberTo, String numberFrom){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(numberTo), new PhoneNumber(numberFrom),sms).create();
    }
}
