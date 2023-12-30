module com.example.searchengineproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.searchengineproj to javafx.fxml;
    exports com.example.searchengineproj;
}