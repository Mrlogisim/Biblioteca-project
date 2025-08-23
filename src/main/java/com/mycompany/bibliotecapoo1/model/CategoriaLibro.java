/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;


public enum CategoriaLibro {
    FICCION("Ficción"),
    NO_FICCION("No Ficción"),
    CIENCIA("Ciencia"),
    HISTORIA("Historia"),
    TECNOLOGIA("Tecnologia");

    private final String label;

    CategoriaLibro(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

