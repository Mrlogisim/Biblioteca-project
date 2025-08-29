/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.service;

import com.mycompany.bibliotecapoo1.model.CopiaLibro;
import com.mycompany.bibliotecapoo1.model.Rack;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import java.util.List;

/**
 *
 * @author mdcin
 */
public class CopiaLibroServicio {
    private Repositorio repositorio;

    public CopiaLibroServicio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<CopiaLibro> obtenerPorRack(Rack rack) {
        return repositorio.getEntityManager()
                .createQuery("SELECT c FROM CopiaLibro c WHERE c.rack = :rack", CopiaLibro.class)
                .setParameter("rack", rack)
                .getResultList();
    }
    
    public List<CopiaLibro> obtenerTodos() {
        return repositorio.obtenerTodos(CopiaLibro.class);
    }
    
    public void agregarCopia(CopiaLibro copia) {
        repositorio.iniciarTransaccion();
        repositorio.insertar(copia);
        repositorio.confirmarTransaccion();
    }
    
    public void actualizarCopia(CopiaLibro copia) {
        repositorio.iniciarTransaccion();
        repositorio.modificar(copia);
        repositorio.confirmarTransaccion();
    }
    
    public void eliminarCopia(CopiaLibro copia) {
        repositorio.iniciarTransaccion();
        repositorio.eliminar(copia);
        repositorio.confirmarTransaccion();
    }
    
    
    
}

