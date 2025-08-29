/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bibliotecario")
@DiscriminatorValue("BIBLIOTECARIO")
public class Bibliotecario extends Miembro {
    // Métodos podrían ser implementados como funciones dentro de servicios
}

