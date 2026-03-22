module com.eda.gerenciadortarefas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens com.eda.gerenciadortarefas to javafx.fxml;
    opens com.eda.gerenciadortarefas.controller to javafx.fxml;

    exports com.eda.gerenciadortarefas;
}