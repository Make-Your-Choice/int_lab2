package com.example.int_lab01;

import javafx.beans.property.SimpleStringProperty;

public class Product {
    SimpleStringProperty nameProduct;
    SimpleStringProperty codeProduct;
    SimpleStringProperty measureUnit;
    SimpleStringProperty codeOKEI;
    SimpleStringProperty amount;
    SimpleStringProperty price;
    SimpleStringProperty sumPrice;
    public Product() {

    }
    public Product(String nameProduct, String codeProduct, String measureUnit, String codeOKEI, String amount, String price, String sumPrice) {
        this.nameProduct = new SimpleStringProperty(nameProduct);
        this.codeProduct = new SimpleStringProperty(codeProduct);
        this.measureUnit = new SimpleStringProperty(measureUnit);
        this.codeOKEI = new SimpleStringProperty(codeOKEI);
        this.amount = new SimpleStringProperty(amount);
        this.price = new SimpleStringProperty(price);
        this.sumPrice = new SimpleStringProperty(sumPrice);
    }
    public String getNameProduct() {
        return nameProduct.get();
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = new SimpleStringProperty(nameProduct);
    }
    public String getCodeProduct() {
        return codeProduct.get();
    }
    public void setCodeProduct(String codeProduct) {
        this.codeProduct = new SimpleStringProperty(codeProduct);
    }
    public String getMeasureUnit() {
        return measureUnit.get();
    }
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = new SimpleStringProperty(measureUnit);
    }
    public String getCodeOKEI() {
        return codeOKEI.get();
    }
    public void setCodeOKEI(String codeOKEI) {
        this.codeOKEI = new SimpleStringProperty(codeOKEI);
    }
    public String getAmount() {
        return amount.get();
    }
    public void setAmount(String amount) {
        this.amount = new SimpleStringProperty(amount);
    }
    public String getPrice() {
        return price.get();
    }
    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
    }
    public String getSumPrice() {
        return sumPrice.get();
    }
    public void setSumPrice(String sumPrice) {
        this.sumPrice = new SimpleStringProperty(sumPrice);
    }
}
