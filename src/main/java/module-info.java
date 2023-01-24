module com.yns.nim {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;

    opens com.yns.nim to javafx.fxml;
    exports com.yns.nim;
    exports com.yns.nim.controllers;
    opens com.yns.nim.controllers to javafx.fxml;
    exports com.yns.nim.Utilities;
    opens com.yns.nim.Utilities to javafx.fxml;
    exports com.yns.nim.Utilities.DataBaseOperations;
    opens com.yns.nim.Utilities.DataBaseOperations to javafx.fxml;
}