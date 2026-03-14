module com.example.last_version {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;
//    requires ATP.Project.PartB;
    requires javafx.media;

    opens com.example.last_version to javafx.fxml;
    exports com.example.last_version;
    exports View;
    opens View to javafx.fxml;
}