/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.service;

import com.mycompany.bibliotecapoo1.model.Libro;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import java.util.List;

/**
 *
 * @author mdcin
 */
public class LibroServicio {
    
    private Repositorio repositorio;

    public LibroServicio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarLibro(Libro libro) {
        repositorio.iniciarTransaccion();
        repositorio.insertar(libro);
        repositorio.confirmarTransaccion();
    }

    public List<Libro> obtenerTodos() {
        return repositorio.obtenerTodos(Libro.class);
    }
    
    public void actualizarLibro(Libro libro) {
        repositorio.iniciarTransaccion();
        repositorio.modificar(libro);
        repositorio.confirmarTransaccion();
    }
    
    public void eliminarLibro(Libro libro) {
        repositorio.iniciarTransaccion();
        repositorio.eliminar(libro);
        repositorio.confirmarTransaccion();
    }

}
