package com.tradinos.wasilnidriver.mvp.view;

import com.tradinos.wasilnidriver.mvp.model.User;

public interface FormContract {
    boolean validate();
    void resetValidation();
    boolean submit();
    void responseCode200(User user);
}
