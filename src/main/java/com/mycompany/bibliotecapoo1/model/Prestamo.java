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

    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;

    @ManyToOne
    private CopiaLibro copia;

    @ManyToOne
    private Miembro miembro;

    // Métodos de negocio (opcionalmente implementables luego)
    public boolean estaVencido() {
        return fechaDevolucion == null && fechaVencimiento.isBefore(LocalDate.now());
    }

    public long diasDeRetraso() {
        if (!estaVencido()) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(fechaVencimiento, LocalDate.now());
    }

    public double calcularMulta() {
        return diasDeRetraso() * 10; // ejemplo
    }

    // Getters y setters
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    
}

