package com.example.int_lab01;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class HelloController implements Initializable {
    @FXML
    public TableView<Product> tableView;
    @FXML
    public TableColumn<Product, String> nameProductColumn;
    @FXML
    public TableColumn<Product, String> codeProductColumn;
    @FXML
    public TableColumn<Product, String> measureUnitColumn;
    @FXML
    public TableColumn<Product, String> codeOKEIColumn;
    @FXML
    public TableColumn<Product, String> amountColumn;
    @FXML
    public TableColumn<Product, String> priceColumn;
    @FXML
    public TableColumn<Product, String> sumPriceColumn;
    @FXML
    public TextField numberActTextField;
    @FXML
    public DatePicker dateActDatePicker;
    @FXML
    public ComboBox nameOrgComboBox;
    @FXML
    public ComboBox nameUnitComboBox;
    @FXML
    public TextField addressTextField;
    public List<List<String>> organizations;
    @FXML
    public TextField okpoTextField;
    @FXML
    public TextField innOrgTextField;
    @FXML
    public TextField okdpTypeTextField;
    @FXML
    public TextField operationTypeTextField;
    @FXML
    public TextField placePurchTextField;
    @FXML
    public ComboBox ceoPositionComboBox;
    @FXML
    public TextField ceoFioTextField;
    public List<List<String>> ceos;
    public static List<List<String>> products;
    public static List<List<String>> measureUnits;
    public static final ObservableList<Product> tableProducts = FXCollections.observableArrayList();
    @FXML
    public TextField completeSumTextField;
    @FXML
    public TextField buyerPositionTextField;
    @FXML
    public TextField buyerFioTextField;
    @FXML
    public TextField sellerFioTextField;
    @FXML
    public TextField completeSumRubTextField;
    @FXML
    public TextField completeSumKopTextField;
    @FXML
    public TextField seriaPassportTextField;
    @FXML
    public TextField numberPassportTextField;
    @FXML
    public TextField issuedByPassportTextField;
    @FXML
    public DatePicker dateIssuePassportDatePicker;
    @FXML
    public TextField homeAddressPassportTextField;
    @FXML
    public TextField numberEntCertificateTextField;
    @FXML
    public TextField issuedByEntCertificateTextField;
    @FXML
    public DatePicker dateIssueEntCertificateDatePicker;
    @FXML
    public TextField nameEntCertificateTextField;
    @FXML
    public TextField innEntCertificateTextField;
    @FXML
    public TextField codeOrgEntCertificateTextField;
    @FXML
    public TextField issuedByFarmCertificateTextField;
    @FXML
    public DatePicker dateIssueFarmCertificateDatePicker;
    @FXML
    public TextField nameFarmCertificateTextField;
    @FXML
    public TextField taxTextField;
    @FXML
    public TextField completeSumDelRubTextField;
    @FXML
    public TextField completeSumDelKopTextField;
    @FXML
    public TextField recieverSellerFioTextField;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //заполнение справочника организаций
        organizations = new ArrayList<List<String>>();
        List<String> org = new ArrayList<>();

        org.add("ООО 'Суплекс'");
        org.add("Кладовая");
        org.add("ул. Пушкина, д. 128");
        org.add("111111");
        org.add("222222");
        org.add("100");
        organizations.add(org);

        org = new ArrayList<>();

        org.add("ЗАО 'Отбойный молоток'");
        org.add("Продовольственный склад");
        org.add("ул. Гоголя, д. 34");
        org.add("333333");
        org.add("444444");
        org.add("101");
        organizations.add(org);

        org = new ArrayList<>();

        org.add("ООО 'Капелька цианистого'");
        org.add("Кладовая");
        org.add("ул. Толстого, д. 69");
        org.add("555555");
        org.add("666666");
        org.add("102");
        organizations.add(org);

        //выпадающий список с названием организации
        ObservableList<String> optionsNameOrg = FXCollections.observableArrayList();
        for (int i = 0; i < organizations.size(); i++) {
            optionsNameOrg.add(organizations.get(i).get(0));
        }
        nameOrgComboBox.getItems().clear();
        nameOrgComboBox.setItems(optionsNameOrg);
        new AutoCompleteBox(nameOrgComboBox);

        //выпадающий список с названием структурного подразделения
        ObservableList<String> optionsNameUnit = FXCollections.observableArrayList();
        for (int i = 0; i < organizations.size(); i++) {
            if(!optionsNameUnit.contains(organizations.get(i).get(1))) {
                optionsNameUnit.add(organizations.get(i).get(1));
            }
        }
        nameUnitComboBox.getItems().clear();
        nameUnitComboBox.setItems(optionsNameUnit);
        new AutoCompleteBox(nameUnitComboBox);

        //поля только для чтения
        okpoTextField.setEditable(false);
        innOrgTextField.setEditable(false);
        okdpTypeTextField.setEditable(false);
        //operationTypeTextField.setEditable(false);

        //заполнение справочника руководителей
        ceos = new ArrayList<List<String>>();
        List<String> ceo = new ArrayList<>();

        ceo.add("Иванов С.В.");
        ceo.add("Директор");
        ceos.add(ceo);

        ceo = new ArrayList<>();

        ceo.add("Петрова И.Д.");
        ceo.add("Зам. директора");
        ceos.add(ceo);

        ceo = new ArrayList<>();

        ceo.add("Кузнецов Г.П.");
        ceo.add("Исп. обязанности директора");
        ceos.add(ceo);

        //выпадающий список с должностями руководителей
        ObservableList<String> optionsPosition = FXCollections.observableArrayList();
        for (int i = 0; i < ceos.size(); i++) {
            optionsPosition.add(ceos.get(i).get(1));
        }
        ceoPositionComboBox.getItems().clear();
        ceoPositionComboBox.setItems(optionsPosition);
        new AutoCompleteBox(ceoPositionComboBox);

        //заполнение справочника продуктов
        products = new ArrayList<List<String>>();
        List<String> product = new ArrayList<>();

        product.add("Картофель столовый ранний");
        product.add("01.13.51.110");
        products.add(product);

        product = new ArrayList<>();

        product.add("Капуста белокачанная");
        product.add("01.13.12.120");
        products.add(product);

        product = new ArrayList<>();

        product.add("Лук репчатый");
        product.add("01.13.43.110");
        products.add(product);

        product = new ArrayList<>();

        product.add("Чеснок");
        product.add("01.13.42.000");
        products.add(product);

        //заполнение справочника единиц измерения
        measureUnits = new ArrayList<List<String>>();
        List<String> measureUnit = new ArrayList<>();

        measureUnit.add("Г");
        measureUnit.add("163");
        measureUnits.add(measureUnit);

        measureUnit = new ArrayList<>();

        measureUnit.add("МКГ");
        measureUnit.add("164");
        measureUnits.add(measureUnit);

        measureUnit = new ArrayList<>();

        measureUnit.add("КГ");
        measureUnit.add("166");
        measureUnits.add(measureUnit);

        //столбцы таблицы
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("nameProduct"));
        codeProductColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("codeProduct"));
        measureUnitColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("measureUnit"));
        codeOKEIColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("codeOKEI"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        sumPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("sumPrice"));
        tableView.getItems().clear();

        //заполнение таблицы данными
        tableView.setItems(tableProducts);
    }

    @FXML
    public void onNameOrgChanged(ActionEvent actionEvent) {
        if(nameOrgComboBox.getValue() == organizations.get(0).get(0)) {
            //addressTextField.setText(organizations.get(0).get(2));
            nameUnitComboBox.getSelectionModel().select(0);
            okpoTextField.setText(organizations.get(0).get(3));
            innOrgTextField.setText(organizations.get(0).get(4));
            okdpTypeTextField.setText(organizations.get(0).get(5));
            //operationTypeTextField.setText("-");
        }
        else if(nameOrgComboBox.getValue() == organizations.get(1).get(0)) {
            //addressTextField.setText(organizations.get(1).get(2));
            nameUnitComboBox.getSelectionModel().select(1);
            okpoTextField.setText(organizations.get(1).get(3));
            innOrgTextField.setText(organizations.get(1).get(4));
            okdpTypeTextField.setText(organizations.get(1).get(5));
            //operationTypeTextField.setText("-");
        }
        else if(nameOrgComboBox.getValue() == organizations.get(2).get(0)) {
            //addressTextField.setText(organizations.get(2).get(2));
            nameUnitComboBox.getSelectionModel().select(0);
            okpoTextField.setText(organizations.get(2).get(3));
            innOrgTextField.setText(organizations.get(2).get(4));
            okdpTypeTextField.setText(organizations.get(2).get(5));
            //operationTypeTextField.setText("-");
        }
        else {
            //addressTextField.setText("");
        }

    }

    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new-entry.fxml"));
        Parent parent = fxmlLoader.load();
        NewEntryController dialogController = fxmlLoader.<NewEntryController>getController();
        dialogController.setObservableLists(tableProducts, products, measureUnits);
        dialogController.fillComboBoxes();
        dialogController.setWindowType(1);

        Scene scene = new Scene(parent, 500, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        updateCompleteSum();
    }

    public void updateCompleteSum() {
        double completeSum = 0;
        for(int i = 0; i < tableView.getItems().size(); i++) {
            completeSum += Double.parseDouble(tableView.getItems().get(i).getSumPrice());
        }
        completeSumTextField.setText(String.valueOf(completeSum));

    }

    @FXML
    public void onEditButtonClick(ActionEvent actionEvent) throws IOException {
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new-entry.fxml"));
        Parent parent = fxmlLoader.load();
        NewEntryController dialogController = fxmlLoader.<NewEntryController>getController();
        dialogController.setObservableLists(tableProducts, products, measureUnits);
        dialogController.fillComboBoxes();
        dialogController.setWindowType(2);
        dialogController.fillAllFields(selectedItem);

        Scene scene = new Scene(parent, 500, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        updateCompleteSum();
    }

    @FXML
    public void onDeleteButtonClick(ActionEvent actionEvent) {
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedItem);
        //tableProducts.remove(tableProducts.indexOf(selectedItem));
        updateCompleteSum();
    }

    @FXML
    public void onExportButtonClicked(ActionEvent actionEvent) throws IOException {

        FileInputStream file = new FileInputStream(new File("C:\\Users\\User\\Downloads\\output.xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell = null;

        cell = sheet.getRow(17).getCell(28);
        cell.setCellValue(numberActTextField.getText());

        cell = sheet.getRow(17).getCell(35);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        cell.setCellValue(dateActDatePicker.getValue().format(formatter));

        cell = sheet.getRow(17).getCell(44);
        formatter = DateTimeFormatter.ofPattern("dd");
        cell.setCellValue(dateActDatePicker.getValue().format(formatter));

        cell = sheet.getRow(17).getCell(47);
        formatter = DateTimeFormatter.ofPattern("MM");
        cell.setCellValue(dateActDatePicker.getValue().format(formatter));

        cell = sheet.getRow(17).getCell(53);
        formatter = DateTimeFormatter.ofPattern("yyyy");
        cell.setCellValue(dateActDatePicker.getValue().format(formatter));

        cell = sheet.getRow(5).getCell(0);
        String tempString = nameOrgComboBox.getValue().toString() + ", " + addressTextField.getText();
        cell.setCellValue(tempString);

        cell = sheet.getRow(8).getCell(0);
        cell.setCellValue(nameUnitComboBox.getValue().toString());

        cell = sheet.getRow(5).getCell(47);
        cell.setCellValue(okpoTextField.getText());

        cell = sheet.getRow(6).getCell(47);
        cell.setCellValue(innOrgTextField.getText());

        cell = sheet.getRow(9).getCell(47);
        cell.setCellValue(okdpTypeTextField.getText());

        cell = sheet.getRow(10).getCell(47);
        cell.setCellValue(operationTypeTextField.getText());

        cell = sheet.getRow(13).getCell(43);
        cell.setCellValue(ceoPositionComboBox.getValue().toString());

        cell = sheet.getRow(15).getCell(48);
        cell.setCellValue(ceoFioTextField.getText());

        cell = sheet.getRow(19).getCell(0);
        cell.setCellValue(placePurchTextField.getText());

        cell = sheet.getRow(21).getCell(4);
        cell.setCellValue(buyerPositionTextField.getText());

        cell = sheet.getRow(21).getCell(15);
        cell.setCellValue(buyerFioTextField.getText());

        cell = sheet.getRow(23).getCell(5);
        cell.setCellValue(sellerFioTextField.getText());

        int index = 0;

        for(int i = 31; index < tableView.getItems().size(); i++) {
            cell = sheet.getRow(i).getCell(0);
            cell.setCellValue(tableView.getItems().get(index).getNameProduct());

            cell = sheet.getRow(i).getCell(21);
            cell.setCellValue(tableView.getItems().get(index).getCodeProduct());

            cell = sheet.getRow(i).getCell(25);
            cell.setCellValue(tableView.getItems().get(index).getMeasureUnit());

            cell = sheet.getRow(i).getCell(29);
            cell.setCellValue(tableView.getItems().get(index).getCodeOKEI());

            cell = sheet.getRow(i).getCell(34);
            cell.setCellValue(tableView.getItems().get(index).getAmount());

            cell = sheet.getRow(i).getCell(39);
            cell.setCellValue(tableView.getItems().get(index).getPrice());

            cell = sheet.getRow(i).getCell(47);
            cell.setCellValue(tableView.getItems().get(index).getSumPrice());

            index++;
        }

        cell = sheet.getRow(58).getCell(47);
        cell.setCellValue(completeSumTextField.getText());

        cell = sheet.getRow(63).getCell(8);
        cell.setCellValue(completeSumRubTextField.getText());

        cell = sheet.getRow(65).getCell(48);
        cell.setCellValue(completeSumKopTextField.getText());

        cell = sheet.getRow(67).getCell(8);
        cell.setCellValue(seriaPassportTextField.getText());

        cell = sheet.getRow(67).getCell(17);
        cell.setCellValue(numberPassportTextField.getText());

        cell = sheet.getRow(69).getCell(0);
        cell.setCellValue(issuedByPassportTextField.getText());

        cell = sheet.getRow(69).getCell(40);
        formatter = DateTimeFormatter.ofPattern("dd");
        cell.setCellValue(dateIssuePassportDatePicker.getValue().format(formatter));

        cell = sheet.getRow(69).getCell(43);
        formatter = DateTimeFormatter.ofPattern("MM");
        cell.setCellValue(dateIssuePassportDatePicker.getValue().format(formatter));

        cell = sheet.getRow(69).getCell(52);
        formatter = DateTimeFormatter.ofPattern("yyyy");
        cell.setCellValue(dateIssuePassportDatePicker.getValue().format(formatter));

        cell = sheet.getRow(71).getCell(15);
        cell.setCellValue(homeAddressPassportTextField.getText());

        cell = sheet.getRow(76).getCell(25);
        cell.setCellValue(numberEntCertificateTextField.getText());

        cell = sheet.getRow(78).getCell(0);
        cell.setCellValue(issuedByEntCertificateTextField.getText());

        cell = sheet.getRow(78).getCell(40);
        formatter = DateTimeFormatter.ofPattern("dd");
        cell.setCellValue(dateIssueEntCertificateDatePicker.getValue().format(formatter));

        cell = sheet.getRow(78).getCell(43);
        formatter = DateTimeFormatter.ofPattern("MM");
        cell.setCellValue(dateIssueEntCertificateDatePicker.getValue().format(formatter));

        cell = sheet.getRow(78).getCell(52);
        formatter = DateTimeFormatter.ofPattern("yyyy");
        cell.setCellValue(dateIssueEntCertificateDatePicker.getValue().format(formatter));

        cell = sheet.getRow(80).getCell(4);
        cell.setCellValue(nameEntCertificateTextField.getText());

        cell = sheet.getRow(80).getCell(43);
        cell.setCellValue(innEntCertificateTextField.getText());

        cell = sheet.getRow(83).getCell(24);
        cell.setCellValue(codeOrgEntCertificateTextField.getText());

        cell = sheet.getRow(87).getCell(0);
        cell.setCellValue(issuedByFarmCertificateTextField.getText());

        cell = sheet.getRow(87).getCell(40);
        formatter = DateTimeFormatter.ofPattern("dd");
        cell.setCellValue(dateIssueFarmCertificateDatePicker.getValue().format(formatter));

        cell = sheet.getRow(87).getCell(43);
        formatter = DateTimeFormatter.ofPattern("MM");
        cell.setCellValue(dateIssueFarmCertificateDatePicker.getValue().format(formatter));

        cell = sheet.getRow(87).getCell(52);
        formatter = DateTimeFormatter.ofPattern("yyyy");
        cell.setCellValue(dateIssueFarmCertificateDatePicker.getValue().format(formatter));

        cell = sheet.getRow(89).getCell(4);
        cell.setCellValue(nameFarmCertificateTextField.getText());

        cell = sheet.getRow(93).getCell(18);
        cell.setCellValue(taxTextField.getText());

        cell = sheet.getRow(97).getCell(8);
        cell.setCellValue(completeSumDelRubTextField.getText());

        cell = sheet.getRow(99).getCell(48);
        cell.setCellValue(completeSumDelKopTextField.getText());

        cell = sheet.getRow(101).getCell(18);
        cell.setCellValue(recieverSellerFioTextField.getText());

        file.close();
        FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\User\\Downloads\\output.xls"));
        workbook.write(outFile);
        outFile.close();
    }
}