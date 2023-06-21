package com.example.pruebalogin;

import java.sql.*;
import com.example.pruebalogin.DAO.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class BanUserController implements Initializable {
    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    private Connection c = null;

    @FXML
    private Button backbtt3;
    @FXML
    private Button Eventsbtt;
    @FXML
    private Button Banbtt;
    @FXML
    private TextField Nametxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public BanUserController() {
        conectar();
    }
    public void conectar() {
        try {
            c = DriverManager.getConnection(url + bd, login, password);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public void close() {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    public void BackLogin1(MouseEvent mouseEvent) throws IOException {
        // Cargar el archivo FXML "Login.fxml" utilizando la clase FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));

        // Crear una nueva escena utilizando el archivo FXML cargado y establecer su tamaño en 600x400
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Obtener una referencia al controlador asociado al archivo FXML cargado
        LoginController controlador = fxmlLoader.getController();

        // Crear una nueva ventana (Stage)
        Stage stage = new Stage();

        // Establecer la escena recién creada como escena principal de la ventana
        stage.setScene(scene);

        // Mostrar la ventana y esperar hasta que el usuario la cierre
        stage.showAndWait();
    }
    public void ShowEvent(MouseEvent mouseEvent) throws IOException, SQLException {
        // Se crea un objeto FXMLLoader y se carga el archivo FXML "Admin.fxml"
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin.fxml"));

        // Se crea una nueva escena con el contenido cargado y se establece su tamaño
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Se obtiene el controlador asociado al archivo FXML
        AdminController controlador = fxmlLoader.getController();

        // Se crea una nueva ventana de la aplicación
        Stage stage = new Stage();

        // Se establece la escena en la ventana
        stage.setScene(scene);

        // Se muestra la ventana y se espera hasta que el usuario la cierre
        stage.showAndWait();
    }
    public void BanUser(MouseEvent mouseEvent) throws IOException, SQLException{
        String Username = Nametxt.getText();
        UserDao.Eliminar(Username);
    }

}