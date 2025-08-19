/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.*;

@Entity
public class CopiaLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoCopia tipo;

    @Enumerated(EnumType.STRING)
    private EstadoCopia estado;

    private boolean esDeReferencia;

    @ManyToOne
    private Libro libro;

    @ManyToOne
    private Rack rack;

    // Getters y setters
}
