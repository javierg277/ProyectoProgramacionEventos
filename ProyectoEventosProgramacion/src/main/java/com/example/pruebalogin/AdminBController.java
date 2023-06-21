package com.example.pruebalogin;

import java.sql.*;

import com.example.pruebalogin.DAO.EventDao;
import com.example.pruebalogin.DAO.UserDao;
import com.example.pruebalogin.Domain.Event;
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

public class AdminBController implements Initializable {
    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    private Connection c = null;

    @FXML
    private Button Createbtt;
    @FXML
    private Button backbtt3;
    @FXML
    private TextField Nametxt;
    @FXML
    private TextField Datetxt;
    @FXML
    private TextField Descriptiontxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public AdminBController() {
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
        // Se carga el archivo FXML "Login.fxml" utilizando la clase FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));

        // Se crea una nueva escena utilizando el archivo FXML cargado y se establece su tamaño en 600x400
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Se obtiene una referencia al controlador asociado al archivo FXML cargado
        LoginController controlador = fxmlLoader.getController();

        // Se crea una nueva ventana (Stage)
        Stage stage = new Stage();

        // Se establece la escena recién creada como escena principal de la ventana
        stage.setScene(scene);

        // Se muestra la ventana y se espera hasta que el usuario la cierre
        stage.showAndWait();
    }
    public void CreateEvent(MouseEvent mouseEvent) throws IOException, SQLException {
        // Verificar si los campos de nombre, fecha y descripción tienen contenido
        if (Nametxt.getText().length() > 0 && Datetxt.getText().length() > 0 && Descriptiontxt.getText().length() > 0) {
            // Crear un nuevo objeto Evento utilizando los valores de los campos de texto
            Event b = new Event(Nametxt.getText(), Datetxt.getText(), Descriptiontxt.getText());

            // Insertar el evento en la base de datos utilizando el objeto EventDao
            EventDao.insertar(b);

            // Limpiar los campos de texto después de insertar el evento
            Nametxt.setText("");
            Datetxt.setText("");
            Descriptiontxt.setText("");
        } else {
            // Si alguno de los campos está vacío, limpiar todos los campos de texto
            Nametxt.setText("");
            Datetxt.setText("");
            Descriptiontxt.setText("");
        }
    }

}