/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
    private static EntityManagerFactory emf = null;

    // Constructor privado para evitar instanciación externa
    private Conexion() {
    }

    // Método estático para obtener la única instancia de EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            crearConexion();
        }
        return emf;
    }

    // Método privado para crear la conexión si no existe
    private static void crearConexion() {
        emf = Persistence.createEntityManagerFactory("IntegradorPoo");
    }
}
