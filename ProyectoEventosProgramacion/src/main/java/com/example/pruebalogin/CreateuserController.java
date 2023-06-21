package com.example.pruebalogin;

import com.example.pruebalogin.DAO.UserDao;
import java.sql.Connection;
import java.sql.SQLException;
import com.example.pruebalogin.Domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateuserController implements Initializable {
    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    private Connection c = null;
    @FXML
    private Label Errorlbl;
    @FXML
    private TextField Usernamefield;
    @FXML
    private PasswordField Passwordfield;
    @FXML
    private TextField Agefield;
    @FXML
    private Button Donebtt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void BackLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        // Carga el archivo "Login.fxml" utilizando el FXMLLoader

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Crea una nueva escena con la vista cargada desde el archivo "Login.fxml"

        LoginController controlador = fxmlLoader.getController();
        // Obtiene el controlador de la vista cargada

        Stage stage = new Stage();
        // Crea una nueva ventana

        stage.setScene(scene);
        // Establece la escena en la ventana

        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre
    }

    public void DoneA(MouseEvent mouseEvent) throws SQLException, IOException {
        User a = null;
        // Crea una variable User inicializada como null

        if (Usernamefield.getText().length() > 0 && Passwordfield.getText().length() > 0 && Agefield.getText().length() > 0) {
            // Verifica si los campos Usernamefield, Passwordfield y Agefield tienen contenido

            a = new User(Usernamefield.getText(), Passwordfield.getText(), Agefield.getText());
            // Crea un nuevo objeto User con los valores de los campos de texto

            UserDao.insertar(a);
            // Utiliza UserDao para insertar el objeto User en la base de datos

            Errorlbl.setText("");
            // Borra el texto del componente Errorlbl

            BackLogin();
            // Llama al método BackLogin() para volver a la pantalla de inicio de sesión
        } else {
            Errorlbl.setText("Complete todos los campos");
            // Establece el texto del componente Errorlbl como "Complete todos los campos"

            Errorlbl.setText("");
            // Borra el texto del componente Errorlbl (línea redundante, posiblemente innecesaria)
        }
    }

}