package com.example.pruebalogin;

import com.example.pruebalogin.DAO.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    private Connection c = null;

    @FXML
    private Button Loginbtt;
    @FXML
    private Button SignUpbtt;
    @FXML
    private TextField UserNametxt;
    @FXML
    private PasswordField Passwordtxt;
    @FXML
    private ImageView Logo;

    @FXML
    private Label Errorlbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método de inicialización para el controlador. Se ejecuta automáticamente al cargar la interfaz gráfica.
        // Puedes agregar código de inicialización aquí si es necesario.
    }

    public LoginController() {
        conectar();
        // Constructor de la clase LoginController. Al crear una instancia de esta clase, se llama automáticamente al método conectar().
    }

    public void conectar() {
        try {
            c = DriverManager.getConnection(url + bd, login, password);
            // Establece una conexión a la base de datos utilizando los valores de las variables url, bd, login y password.
        } catch (SQLException exc) {
            exc.printStackTrace();
            // Si se produce una excepción durante la conexión, se imprime la traza de la excepción.
        }
    }

    public void close() {
        try {
            if (c != null) {
                c.close();
                // Cierra la conexión a la base de datos si la conexión no es nula.
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            // Si se produce una excepción al cerrar la conexión, se imprime la traza de la excepción.
        }
    }

    @FXML
    private void UserValidates(MouseEvent mouseEvent) throws SQLException, IOException {
        // Método asociado al evento de validación del usuario, activado por un evento de clic del mouse.
        CheckAdmin();
        // Llama al método CheckAdmin(), que probablemente tenga alguna lógica relacionada con la verificación del usuario como administrador.
        ArrayList<String> nombreusuarios = UserDao.RecogeUser();
        // Obtiene una lista de nombres de usuarios desde algún lugar utilizando la clase UserDao.
        String nombreintroducido = (String) UserNametxt.getText();
        // Obtiene el nombre introducido por el usuario desde algún componente gráfico llamado UserNametxt.
        for (String nombrearraylist : nombreusuarios) {
            // Itera sobre la lista de nombres de usuarios obtenida anteriormente.
            if (nombrearraylist.equals(nombreintroducido)) {
                // Comprueba si el nombre de usuario actual en la lista coincide con el nombre introducido por el usuario.
                if (Passwordtxt.getText().equals(UserDao.recogecontraseña(nombreintroducido))) {
                    // Comprueba si la contraseña introducida por el usuario coincide con la contraseña correspondiente al nombre de usuario.
                    UserMenu();
                    // Llama al método UserMenu(), que probablemente muestre el menú del usuario si las credenciales son válidas.
                }
            }
        }
    }


    private void CheckAdmin() throws SQLException, IOException {
        // Método para verificar si el usuario introducido es un administrador.
        ArrayList<String> nombreusuarios = UserDao.recogeAdmin();
        // Obtiene una lista de nombres de usuarios administradores desde algún lugar utilizando la clase UserDao.
        String nombreintroducido = (String) UserNametxt.getText();
        // Obtiene el nombre introducido por el usuario desde algún componente gráfico llamado UserNametxt.
        for (String nombrearraylist : nombreusuarios) {
            // Itera sobre la lista de nombres de usuarios administradores obtenida anteriormente.
            if (nombrearraylist.equals(nombreintroducido)) {
                // Comprueba si el nombre de usuario actual en la lista coincide con el nombre introducido por el usuario.
                if (Passwordtxt.getText().equals(UserDao.recogecontraseñaA(nombreintroducido))) {
                    // Comprueba si la contraseña introducida por el usuario coincide con la contraseña correspondiente al nombre de usuario administrador.
                    if (UserDao.CheckPermisi0n(nombreintroducido) == 1) {
                        // Comprueba si el usuario tiene permisos de administrador.
                        AdminMenu();
                        // Llama al método AdminMenu() para mostrar el menú de administrador.
                    } else {
                        AdminMenuB();
                        // Llama al método AdminMenuB() para mostrar una alternativa de menú de administrador.
                    }
                }
            }
        }
    }

    private void AdminMenuB() throws IOException {
        // Método para mostrar el menú de administrador alternativo.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdminB.fxml"));
        // Carga el archivo FXML correspondiente al menú de administrador alternativo.
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Crea una escena con el contenido cargado desde el archivo FXML.
        AdminBController controlador = fxmlLoader.getController();
        // Obtiene una instancia del controlador correspondiente al menú de administrador alternativo.
        Stage stage = new Stage();
        // Crea una nueva ventana (stage).
        stage.setScene(scene);
        // Establece la escena en la ventana.
        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre.
    }

    private void AdminMenu() throws IOException {
        // Método para mostrar el menú de administrador.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin.fxml"));
        // Carga el archivo FXML correspondiente al menú de administrador.
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Crea una escena con el contenido cargado desde el archivo FXML.
        AdminController controlador = fxmlLoader.getController();
        // Obtiene una instancia del controlador correspondiente al menú de administrador.
        Stage stage = new Stage();
        // Crea una nueva ventana (stage).
        stage.setScene(scene);
        // Establece la escena en la ventana.
        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre.
    }

    @FXML
    public void UserMenu() throws IOException {
        // Método para mostrar el menú de usuario.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UserView.fxml"));
        // Carga el archivo FXML correspondiente al menú de usuario.
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Crea una escena con el contenido cargado desde el archivo FXML.
        UserController controlador = fxmlLoader.getController();
        // Obtiene una instancia del controlador correspondiente al menú de usuario.
        Stage stage = new Stage();
        // Crea una nueva ventana (stage).
        stage.setScene(scene);
        // Establece la escena en la ventana.
        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre.
    }

    @FXML
    public void Singup(MouseEvent mouseEvent) throws IOException {
        // Método para manejar el evento de registro.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Createuser.fxml"));
        // Carga el archivo FXML correspondiente a la vista de registro.
        Scene scene = new Scene(fxmlLoader.load(), 306, 400);
        // Crea una escena con el contenido cargado desde el archivo FXML.
        CreateuserController controlador = fxmlLoader.getController();
        // Obtiene una instancia del controlador correspondiente a la vista de registro.
        Stage stage = new Stage();
        // Crea una nueva ventana (stage).
        stage.setScene(scene);
        // Establece la escena en la ventana.
        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre.
    }

    @FXML
    public void PasswordF(MouseEvent mouseEvent) throws IOException {
        // Método para manejar el evento de recuperación de contraseña.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ForgotP.fxml"));
        // Carga el archivo FXML correspondiente a la vista de recuperación de contraseña.
        Scene scene = new Scene(fxmlLoader.load(), 363, 322);
        // Crea una escena con el contenido cargado desde el archivo FXML.
        PasswordForgotController controlador = fxmlLoader.getController();
        // Obtiene una instancia del controlador correspondiente a la vista de recuperación de contraseña.
        Stage stage = new Stage();
        // Crea una nueva ventana (stage).
        stage.setScene(scene);
        // Establece la escena en la ventana.
        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre.
    }
}
