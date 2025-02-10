package com.rapidticket.platform.infrastructure.adapters.out.dbentities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("spectator")
public class SpectatorEntity {

    @Id
    private long id;

    private String dni;
    private String name;

    public SpectatorEntity() {
    }

    public SpectatorEntity(long id, String dni, String name) {
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