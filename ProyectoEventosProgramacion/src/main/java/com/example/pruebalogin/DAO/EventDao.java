package com.example.pruebalogin.DAO;

import java.sql.*;

import com.example.pruebalogin.Domain.Event;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
public class EventDao implements Initializable {
    private static final String bd = "eventos_programacion";
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String login = "root";
    private static final String password = "";

    private static Connection c = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void conectar() {
        try {
            c = DriverManager.getConnection(url + bd, login, password);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
    public static void insertar(Event b) throws SQLException {
        conectar();
        PreparedStatement stat = null;
        stat = c.prepareStatement("insert into events(Event_name,Event_Date,Event_Description) values(?,?,?)");
        stat.setString(1, b.getEvent_name());
        stat.setString(2, b.getEvent_Date());
        stat.setString(3, b.getEvent_Description());
        stat.executeUpdate();

    }
}
