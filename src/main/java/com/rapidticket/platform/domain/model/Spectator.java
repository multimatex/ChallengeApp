package com.rapidticket.platform.domain.model;

public class Spectator {
    private long id;
    private String dni;
    private String name;

    public Spectator() {
    }

    public Spectator(long id, String dni, String name) {
        this.id = id;
        this.dni = dni;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
