module com.example.searchengineproj {
    requires javafx.controls;
    requires javafx.fxml;
    //requires org.apache.commons.codec;
    requires java.sql;
    requires commons.codec;


    opens com.example.searchengineproj to javafx.fxml;
    exports com.example.searchengineproj;
}