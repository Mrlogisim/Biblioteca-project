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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoCopia getTipo() {
        return tipo;
    }

    public void setTipo(TipoCopia tipo) {
        this.tipo = tipo;
    }

    public EstadoCopia getEstado() {
        return estado;
    }

    public void setEstado(EstadoCopia estado) {
        this.estado = estado;
    }

    public boolean isEsDeReferencia() {
        return esDeReferencia;
    }

    public void setEsDeReferencia(boolean esDeReferencia) {
        this.esDeReferencia = esDeReferencia;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
    
    
}
