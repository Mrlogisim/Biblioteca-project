/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha de Inicio")
    private LocalDate fechaInicio;
    
    @Column(name = "Fecha de devolución")
    private LocalDate fechaVencimiento;

    @ManyToOne
    private CopiaLibro copia;

    @ManyToOne
    private Miembro miembro;

    // Métodos de negocio (opcionalmente implementables luego)
    public boolean estaVencido() {
        if (fechaVencimiento == null) {
            return false; // Si no hay fecha de vencimiento, no está vencido
        }
        
        LocalDate hoy = LocalDate.now();
        return hoy.isAfter(fechaVencimiento);
    }

    public long diasDeRetraso() {
        if (!estaVencido()) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(fechaVencimiento, LocalDate.now());
    }

    public double calcularMulta() {
        return diasDeRetraso() * 10; // ejemplo
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public CopiaLibro getCopia() {
        return copia;
    }

    public void setCopia(CopiaLibro copia) {
        this.copia = copia;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }
    
    
    
    
    
}

