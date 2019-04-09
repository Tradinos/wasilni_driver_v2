package com.wasilni.wasilnidriverv2.mvp.model;

public class Car {
    private int colorId, brandId, modelId;
    private String number, insuranceExpireDate, carManufactureYear;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInsuranceExpireDate() {
        return insuranceExpireDate;
    }

    public void setInsuranceExpireDate(String insuranceExpireDate) {
        this.insuranceExpireDate = insuranceExpireDate;
    }

    public String getCarManufactureYear() {
        return carManufactureYear;
    }

    public void setCarManufactureYear(String carManufactureYear) {
        this.carManufactureYear = carManufactureYear;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
