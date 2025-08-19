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

    private String descripcion;

    @OneToMany(mappedBy = "rack")
    private List<CopiaLibro> copias;

    // Getters y setters
}

