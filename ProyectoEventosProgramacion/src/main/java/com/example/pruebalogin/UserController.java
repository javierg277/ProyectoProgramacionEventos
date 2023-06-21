package com.example.pruebalogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.pruebalogin.Domain.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class UserController implements Initializable{
    private final String bd = "eventos_programacion";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String login = "root";
    private final String password = "";

    @FXML
    private TableView<Map> tablaeventos;
    private Connection c = null;
    @FXML
    private Button backbtt3;
    @FXML
    private Button Profilebtt;
    @FXML
    private TableView<Event> tblEvents;
    @FXML
    private TableColumn<Event, String> NameCol;
    @FXML
    private TableColumn<Event, String> dateCol;
    @FXML
    private TableColumn<Event, String> DescriptionCol;
    private ObservableList<Event> Eventlist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void Table(MouseEvent mousevent) throws SQLException {
        this.NameCol.setCellValueFactory(new PropertyValueFactory<>("Event_name"));
        this.dateCol.setCellValueFactory(new PropertyValueFactory<>("Event_Date"));
        this.DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Event_Description"));

            ObservableList<Event> items = getEvent();
            this.tblEvents.setItems(items);

    }
    public UserController() {
        conectar();
    }
    public ObservableList<Event> getEvent() throws SQLException{
        ObservableList<Event> obs = FXCollections.observableArrayList();
        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery("SELECT Event_name, Event_Date, Event_Description FROM events");
        while(rs.next()){
            Event e = new Event();
            String Event_Name = rs.getString("Event_name");
            String Event_Date = rs.getString("Event_Date");
            String Event_Description = rs.getString("Event_Description");
            e = new Event(Event_Name, Event_Date, Event_Description);
            obs.add(e);
        }
        return obs;
    }
    private void llenartabla() throws SQLException {
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
    public void EditProfile(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditProfile.fxml"));
        // Carga el archivo "EditProfile.fxml" utilizando el FXMLLoader

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Crea una nueva escena con la vista cargada desde el archivo "EditProfile.fxml"

        EditProfile controlador = fxmlLoader.getController();
        // Obtiene el controlador de la vista cargada

        Stage stage = new Stage();
        // Crea una nueva ventana

        stage.setScene(scene);
        // Establece la escena en la ventana

        stage.showAndWait();
        // Muestra la ventana y espera hasta que se cierre
    }

    @FXML
    public void BackLogin1(MouseEvent mouseEvent) throws IOException {
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

}
