package com.personal.banking_api.util;

import com.personal.banking_api.model.User;
import org.springframework.stereotype.Component;

@Component
public class CheckerUtil {

    public boolean informationCheck(User user){
        if(!user.getName().isEmpty() && !user.getAddress().isEmpty() && user.getDob() != null){
            return true;
        }
        return false;
    }

    public boolean pinCheck(String pin){
        if(pin.length() == 6 && pin.matches("[0-9]+")){
            return true;
        }
        return false;
    }
}
