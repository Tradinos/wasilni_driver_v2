package com.wasilni.wasilnidriverv2.mvp.view;

public interface FormContract {
    boolean validate();
    void resetValidation();
    boolean submit();
}
