/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descripción")
    private String ubicacion;

    @OneToMany(mappedBy = "rack")
    private List<CopiaLibro> copias;

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public List<CopiaLibro> getCopias() {
        return copias;
    }

    public void setCopias(List<CopiaLibro> copias) {
        this.copias = copias;
    }
    
    
    
}

