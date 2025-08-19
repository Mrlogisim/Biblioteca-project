/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Miembro")
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "clave")
    private String clave;

    @Enumerated(EnumType.STRING)
    private EstadoMiembro estado;

    @Column(name = "activo")
    private boolean activo;

    @OneToMany(mappedBy = "miembro")
    private List<Prestamo> prestamos;

    public boolean puedeSacarLibro() {
        return activo && estado == EstadoMiembro.ACTIVO && prestamos.size() < 5;
    }

    public List<Prestamo> getPrestamosActivos() {
        return prestamos.stream()
                          .filter(p -> p.getFechaDevolucion() == null)
                          .toList();
    }

    // Getters y setters
}

