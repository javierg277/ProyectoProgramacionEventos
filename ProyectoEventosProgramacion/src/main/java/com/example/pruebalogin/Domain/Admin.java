package com.example.pruebalogin.Domain;



public class Admin extends User {
    private boolean permisos;

    public Admin(boolean permisos) {
        this.permisos = permisos;
    }

    public boolean isFlag() {
        return permisos;
    }

    public void setFlag(boolean permisos) {
        this.permisos = permisos;
    }
}