/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "miembro")
@Inheritance(strategy = InheritanceType.JOINED) // Estrategia de herencia
@DiscriminatorColumn(name = "tipo_miembro", discriminatorType = DiscriminatorType.STRING)
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Nombre")
    private String nombre;
    
    @Column(name = "Apellido")
    private String apellido;
    
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

 

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public EstadoMiembro getEstado() {
        return estado;
    }

    public void setEstado(EstadoMiembro estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    


}

