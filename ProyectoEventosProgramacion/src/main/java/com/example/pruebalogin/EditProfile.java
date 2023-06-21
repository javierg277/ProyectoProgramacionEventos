package com.example.pruebalogin;

import com.example.pruebalogin.DAO.UserDao;
import java.sql.*;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditProfile implements Initializable {
    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    private Connection c = null;
    @FXML
    private Button backbtt4;
    @FXML
    private Button EventsUbtt;
    @FXML
    private Button Editbtt;
    @FXML
    private TextField Usernametxt;
    @FXML
    private PasswordField Passwordtxt;
    @FXML
    private TextField Agetxt;
    @FXML
    private Label Errorlbl;
    @FXML
    private Label Editlbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public EditProfile() {
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
    public void BackLogin4(MouseEvent mouseEvent) throws IOException {
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

    @FXML
    public void ShowEvents(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("UserView.fxml"));
        // Carga el archivo "UserView.fxml" utilizando el FXMLLoader

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Crea una nueva escena con la vista cargada desde el archivo "UserView.fxml"

        UserController controlador = fxmlLoader.getController();
        // Obtiene el controlador de la vista cargada

        Stage stage = new Stage();
        // Crea una nueva ventana

        stage.setScene(scene);
        // Establece la escena en la ventana

        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre
    }

    public void Editprofiles() throws IOException, SQLException {
        User a = null;
        // Crea una variable User inicializada como null

        if (Usernametxt.getText().length() > 0 && Passwordtxt.getText().length() > 0 && Agetxt.getText().length() > 0) {
            // Verifica si los campos Usernametxt, Passwordtxt y Agetxt tienen contenido

            a = new User(Usernametxt.getText(), Passwordtxt.getText(), Agetxt.getText());
            // Crea un nuevo objeto User con los valores de los campos de texto

            Editlbl.setText("usuario Modificado correctamente");
            // Establece el texto del componente Editlbl como "usuario Modificado correctamente"

            Errorlbl.setText("");
            // Borra el texto del componente Errorlbl

            String username = Usernametxt.getText();
            // Obtiene el nombre de usuario del campo Usernametxt

            UserDao.Eliminar(username);
            // Utiliza UserDao para eliminar el usuario con el nombre de usuario especificado

            UserDao.insertar(a);
            // Utiliza UserDao para insertar el objeto User modificado en la base de datos

            close();
            // Cierra la conexión con la base de datos mediante el método close()
        } else {
            Errorlbl.setText("Complete todos los campos");
            // Establece el texto del componente Errorlbl como "Complete todos los campos"

            Editlbl.setText("");
            // Borra el texto del componente Editlbl
        }
    }
    public ArrayList RecogeUser() throws SQLException {
        ArrayList<String> usuarios = new ArrayList<>();
        // Crea un ArrayList para almacenar los nombres de usuario

        Statement stat = c.createStatement();
        // Crea un objeto Statement para ejecutar consultas SQL

        ResultSet rs = stat.executeQuery("SELECT UserName from users");
        // Ejecuta una consulta SQL para seleccionar todos los nombres de usuario de la tabla "users"

        while (rs.next()) {
            // Itera sobre el resultado del conjunto de resultados
            String nombre = rs.getString("UserName");
            // Obtiene el nombre de usuario del resultado

            usuarios.add(nombre);
            // Agrega el nombre de usuario al ArrayList
        }

        return usuarios;
        // Devuelve el ArrayList con los nombres de usuario obtenidos de la base de datos
    }

    @FXML
    private void UserValidates(MouseEvent mouseEvent) throws SQLException, IOException {
        ArrayList<String> nombreusuarios = RecogeUser();
        // Obtiene la lista de nombres de usuario llamando al método RecogeUser()

        String nombreintroducido = (String) Usernametxt.getText();
        // Obtiene el nombre de usuario introducido en el campo de texto Usernametxt

        for (String nombrearraylist : nombreusuarios) {
            // Itera sobre los nombres de usuario obtenidos de la base de datos
            if (nombrearraylist.equals(nombreintroducido)) {
                // Comprueba si el nombre de usuario introducido coincide con uno de los nombres de usuario obtenidos
                Editprofiles();
                // Llama al método Editprofiles() para editar el perfil del usuario
            }
        }
    }
}
