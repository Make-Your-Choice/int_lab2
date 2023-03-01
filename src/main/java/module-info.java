module com.example.int_lab01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi;


    opens com.example.int_lab01 to javafx.fxml;
    exports com.example.int_lab01;
}