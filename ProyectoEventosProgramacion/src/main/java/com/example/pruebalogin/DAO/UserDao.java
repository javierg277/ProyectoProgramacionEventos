package com.example.pruebalogin.DAO;
import java.sql.*;

import com.example.pruebalogin.Domain.User;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class UserDao implements Initializable{
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
    public static int CheckPermisi0n(String nombreintroducido) throws SQLException {
        int a = 0;
        conectar();
        PreparedStatement pstat = null;
        pstat = c.prepareStatement("SELECT Permisos from Admin where userName = ?");
        pstat.setString(1, nombreintroducido);
        pstat.executeQuery();
        ResultSet rs = pstat.executeQuery();
        while(rs.next()){
            a = rs.getInt("Permisos");
        }

        return a;
    }

    public static String recogecontraseña(String usuario) throws SQLException {
        String contraseña = "";
        conectar();
        PreparedStatement stat = c.prepareStatement("SELECT Password from users where UserName = ?");
        stat.setString(1 , usuario);
        ResultSet res = stat.executeQuery();
        while(res.next()){
            contraseña = res.getString("password");
        }
        return contraseña;
    }

    public static String recogecontraseñaA(String usuario) throws SQLException {
        String contraseña = "";
        conectar();
        PreparedStatement stat = c.prepareStatement("SELECT Password from admin where UserName = ?");
        stat.setString(1 , usuario);
        ResultSet res = stat.executeQuery();
        while(res.next()){
            contraseña = res.getString("password");
        }
        return contraseña;
    }

    public static ArrayList RecogeUser() throws SQLException {
        conectar();
        ArrayList<String> usuarios = new ArrayList<>();

        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery("SELECT UserName from users");
        while(rs.next()){
            String nombre = rs.getString("UserName");
            usuarios.add(nombre);
        }
        return usuarios;
    }

    public static ArrayList<String> recogeAdmin() throws SQLException {
        conectar();
        ArrayList<String> Admins = new ArrayList<>();

        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery("SELECT UserName from Admin");
        while(rs.next()){
            String nombre = rs.getString("UserName");
            Admins.add(nombre);
        }
        return Admins;
    }

    public static void Eliminar(String username) throws SQLException {
        conectar();
        PreparedStatement pstat = null;
        String castro = String.valueOf(username);
        System.out.print(castro);
        pstat = c.prepareStatement("DELETE FROM users Where UserName = ? ");
        pstat.setString(1, username);
        pstat.executeUpdate();
    }
    public static String recogePassword(String nombre) throws SQLException{
        conectar();
        String contraseña = "";

        PreparedStatement stat = c.prepareStatement("SELECT Password from users where UserName = ?");
        stat.setString(1 , nombre);
        ResultSet res = stat.executeQuery();
        while(res.next()){
            contraseña = res.getString("password");
        }
        return contraseña;
    }

    public static String getAge(String usuario) throws SQLException {
        conectar();
        int AgeI = 0;

        PreparedStatement stat = c.prepareStatement("SELECT Age from users where UserName = ?");
        stat.setString(1 , usuario);
        ResultSet res = stat.executeQuery();
        while(res.next()){
            AgeI = res.getInt("Age");
        }
        String Age = Integer.toString(AgeI);
        return Age;
    }

    public static void insertar(User a) throws SQLException {
        conectar();
        PreparedStatement stat = null;
        stat = c.prepareStatement("insert into users(UserName,password,Age) values(?,?,?)");
        stat.setString(1, a.getUserName());
        stat.setString(2, a.getPassword());
        stat.setString(3, a.getAge());
        stat.executeUpdate();

    }

}
