package com.wasilni.wasilnidriverv2.mvp.view;

import com.wasilni.wasilnidriverv2.mvp.model.User;

public interface FormContract {
    boolean validate();
    void resetValidation();
    boolean submit();
    void responseCode200(User user);
}
