module espol.grupo_09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens espol.grupo_09 to javafx.fxml;
    exports espol.grupo_09;
}
