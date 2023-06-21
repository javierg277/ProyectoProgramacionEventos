package com.example.pruebalogin;

import javafx.fxml.Initializable;

import java.sql.*;
import com.example.pruebalogin.DAO.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class PasswordForgotController implements Initializable {

    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    private Connection c = null;

    @FXML
    private Button Checkboxbtt;
    @FXML
    private TextField UserNametxt;
    @FXML
    private TextField Agetxt;
    @FXML
    private Label Errorlbl;
    @FXML
    private TextField Passwordtxt;
    @FXML
    private Label Correctlbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método de inicialización de la clase PasswordForgotController
        // Se ejecuta cuando se carga una URL específica
    }

    public PasswordForgotController() {
        conectar(); // Llama al método conectar() al crear una instancia de la clase
    }

    public void conectar() {
        try {
            c = DriverManager.getConnection(url + bd, login, password);
            // Establece una conexión con la base de datos utilizando los datos de conexión
        } catch (SQLException exc) {
            exc.printStackTrace(); // Imprime la traza de la excepción si se produce un error
        }
    }

    public void close() {
        try {
            if (c != null) {
                c.close(); // Cierra la conexión con la base de datos si no es nula
            }
        } catch (SQLException exc) {
            exc.printStackTrace(); // Imprime la traza de la excepción si se produce un error
        }
    }

    public void CheckData(MouseEvent mouseEvent) throws IOException, SQLException {
        ArrayList<String> nombreusuarios = UserDao.RecogeUser();
        // Obtiene una lista de nombres de usuario utilizando la clase UserDao

        String nombreintroducido = (String) UserNametxt.getText();
        // Obtiene el texto ingresado en un campo de texto llamado UserNametxt

        for (String nombrearraylist : nombreusuarios) {
            // Recorre la lista de nombres de usuario
            if (nombrearraylist.equals(nombreintroducido)) {
                // Comprueba si hay una coincidencia con el nombre introducido
                if (Agetxt.getText().equals(UserDao.getAge(nombreintroducido))) {
                    // Comprueba si la edad ingresada coincide con la edad obtenida de UserDao
                    AbrirPassword(nombreintroducido);
                    // Llama al método AbrirPassword() con el nombre de usuario como argumento
                } else {
                    Errorlbl.setText("Error: Datos Inválidos");
                    // Establece el texto del componente Errorlbl como "Error: Datos Inválidos"
                }
            }
        }
    }

    private void AbrirPassword(String nombreintroducido) throws SQLException {
        String Password = UserDao.recogePassword(nombreintroducido);
        // Obtiene la contraseña del usuario utilizando la clase UserDao

        Passwordtxt.setText(Password);
        // Establece el texto del componente Passwordtxt con la contraseña obtenida

        Correctlbl.setText("Su contraseña es:");
        // Establece el texto del componente Correctlbl como "Su contraseña es:"
    }
}