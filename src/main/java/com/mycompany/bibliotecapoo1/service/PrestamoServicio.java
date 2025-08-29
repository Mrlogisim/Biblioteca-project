/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.service;

import com.mycompany.bibliotecapoo1.model.Prestamo;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import java.util.List;




public class PrestamoServicio {
    
    private Repositorio repositorio;

    public PrestamoServicio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        repositorio.iniciarTransaccion();
        repositorio.insertar(prestamo);
        repositorio.confirmarTransaccion();
    }

    public List<Prestamo> obtenerTodos() {
        return repositorio.obtenerTodos(Prestamo.class);
    }
    
    public void actualizarLibro(Prestamo prestamo) {
        repositorio.iniciarTransaccion();
        repositorio.modificar(prestamo);
        repositorio.confirmarTransaccion();
    }
    
    public void eliminarLibro(Prestamo prestamo) {
        repositorio.iniciarTransaccion();
        repositorio.eliminar(prestamo);
        repositorio.confirmarTransaccion();
    }

    // ✅ NUEVO: Obtener libro por ID
    public Prestamo obtenerLibroPorId(int id) {
        return repositorio.obtenerPorId(Prestamo.class, id);
    }
    
}
