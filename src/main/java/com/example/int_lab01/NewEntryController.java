package com.example.int_lab01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewEntryController implements Initializable {
    @FXML
    public ComboBox nameProductComboBox;
    @FXML
    public TextField priceTextField;
    @FXML
    public ComboBox codeProductComboBox;
    @FXML
    public ComboBox codeOKEIComboBox;
    @FXML
    public TextField amountTextField;
    @FXML
    public ComboBox measureUnitComboBox;
    public List<List<String>> products;
    public List<List<String>> measureUnits;
    @FXML
    public Button okButton;
    @FXML
    public Label titleLabel;
    @FXML
    public TextField completeSumTextField;
    @FXML
    public Button cancelButton;
    private ObservableList<Product> tableProducts;
    private int windowType;
    private Product tempProduct;

    public void setObservableLists(ObservableList<Product> tableProducts, List<List<String>> products, List<List<String>> measureUnits) {
        this.tableProducts = tableProducts;
        this.products = products.stream().toList();
        this.measureUnits = measureUnits.stream().toList();
    }

    public void setWindowType(int windowType) {
        this.windowType = windowType;
        if(windowType == 1) {
            titleLabel.setText("Добавление новой записи");
        }
        else if (windowType == 2) {
            titleLabel.setText("Редактирование записи");
        }
    }

    public void fillComboBoxes() {
        //выпадающий список с названием продуктов
        ObservableList<String> optionsNameProduct = FXCollections.observableArrayList();
        for (int i = 0; i < products.size(); i++) {
            optionsNameProduct.add(products.get(i).get(0));
        }
        nameProductComboBox.getItems().clear();
        nameProductComboBox.setItems(optionsNameProduct);
        new AutoCompleteBox(nameProductComboBox);

        //выпадающий список с кодами продуктов
        ObservableList<String> optionsCodeProduct = FXCollections.observableArrayList();
        for (int i = 0; i < products.size(); i++) {
            optionsCodeProduct.add(products.get(i).get(1));
        }
        codeProductComboBox.getItems().clear();
        codeProductComboBox.setItems(optionsCodeProduct);
        new AutoCompleteBox(codeProductComboBox);

        //выпадающий список с названием единиц измерения
        ObservableList<String> optionsMeasureUnits = FXCollections.observableArrayList();
        for (int i = 0; i < measureUnits.size(); i++) {
            optionsMeasureUnits.add(measureUnits.get(i).get(0));
        }
        measureUnitComboBox.getItems().clear();
        measureUnitComboBox.setItems(optionsMeasureUnits);
        new AutoCompleteBox(measureUnitComboBox);

        //выпадающий список с кодами единиц измерения
        ObservableList<String> optionsCodeOKEI = FXCollections.observableArrayList();
        for (int i = 0; i < measureUnits.size(); i++) {
            optionsCodeOKEI.add(measureUnits.get(i).get(1));
        }
        codeOKEIComboBox.getItems().clear();
        codeOKEIComboBox.setItems(optionsCodeOKEI);
        new AutoCompleteBox(codeOKEIComboBox);
    }

    public void fillAllFields(Product product) {
        for(int i = 0; i < products.size(); i ++) {
            if(nameProductComboBox.getItems().get(i).equals(product.getNameProduct())) {
                nameProductComboBox.getSelectionModel().select(i);
            }
            if(codeProductComboBox.getItems().get(i).equals(product.getCodeProduct())) {
                codeProductComboBox.getSelectionModel().select(i);
            }
        }
        for(int i = 0; i < measureUnits.size(); i ++) {
            if(codeOKEIComboBox.getItems().get(i).equals(product.getCodeOKEI())) {
                codeOKEIComboBox.getSelectionModel().select(i);
            }
            if(measureUnitComboBox.getItems().get(i).equals(product.getMeasureUnit())) {
                measureUnitComboBox.getSelectionModel().select(i);
            }
        }
        priceTextField.setText(product.getPrice());
        amountTextField.setText(product.getAmount());
        completeSumTextField.setText(product.getSumPrice());
        tempProduct = product;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Double.parseDouble(newValue);
                Double.parseDouble(priceTextField.getText());
                completeSumTextField.setText(String.valueOf(Double.parseDouble(newValue) * Double.parseDouble(priceTextField.getText())));
            } catch(NumberFormatException e){
                completeSumTextField.setText("");
            }
        });
        priceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Double.parseDouble(newValue);
                Double.parseDouble(amountTextField.getText());
                completeSumTextField.setText(String.valueOf(Double.parseDouble(newValue) * Double.parseDouble(amountTextField.getText())));
            } catch(NumberFormatException e){
                completeSumTextField.setText("");
            }
        });
        completeSumTextField.setEditable(false);

    }

    public void onNameProductChanged(ActionEvent actionEvent) {
        for(int i = 0; i < products.size(); i ++) {
            if(nameProductComboBox.getValue() == products.get(i).get(0)) {
                codeProductComboBox.getSelectionModel().select(i);
            }
        }
    }

    public void onCodeProductChanged(ActionEvent actionEvent) {
        for(int i = 0; i < products.size(); i ++) {
            if(codeProductComboBox.getValue() == products.get(i).get(1)) {
                nameProductComboBox.getSelectionModel().select(i);
            }
        }
    }

    public void onCodeOKEIChanged(ActionEvent actionEvent) {
        for(int i = 0; i < measureUnits.size(); i ++) {
            if(codeOKEIComboBox.getValue() == measureUnits.get(i).get(1)) {
                measureUnitComboBox.getSelectionModel().select(i);
            }
        }
    }

    public void onMeasureUnitChanged(ActionEvent actionEvent) {
        for(int i = 0; i < measureUnits.size(); i ++) {
            if(measureUnitComboBox.getValue() == measureUnits.get(i).get(0)) {
                codeOKEIComboBox.getSelectionModel().select(i);
            }
        }
    }

    public void onOkButtonClicked(ActionEvent actionEvent) {
        if(windowType == 1) {
            double sum = Double.parseDouble(amountTextField.getText()) * Double.parseDouble(priceTextField.getText());
            tableProducts.add(new Product(nameProductComboBox.getValue().toString(), codeProductComboBox.getValue().toString(),
                    measureUnitComboBox.getValue().toString(), codeOKEIComboBox.getValue().toString(),
                    amountTextField.getText(), priceTextField.getText(), String.valueOf(sum)));
        }
        else if (windowType == 2) {
            double sum = Double.parseDouble(amountTextField.getText()) * Double.parseDouble(priceTextField.getText());
            tableProducts.set(tableProducts.indexOf(tempProduct), new Product(nameProductComboBox.getValue().toString(), codeProductComboBox.getValue().toString(),
                    measureUnitComboBox.getValue().toString(), codeOKEIComboBox.getValue().toString(),
                    amountTextField.getText(), priceTextField.getText(), String.valueOf(sum)));
        }
        windowType = 0;
        amountTextField.clear();
        priceTextField.clear();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        amountTextField.clear();
        priceTextField.clear();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
