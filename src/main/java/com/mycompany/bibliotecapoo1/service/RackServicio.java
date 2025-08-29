/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.service;

import com.mycompany.bibliotecapoo1.model.Rack;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import java.util.List;

/**
 *
 * @author mdcin
 */
public class RackServicio {
    private Repositorio repositorio;

    public RackServicio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarRack(Rack rack) {
        repositorio.iniciarTransaccion();
        repositorio.insertar(rack);
        repositorio.confirmarTransaccion();
    }

    public List<Rack> obtenerTodos() {
        return repositorio.obtenerTodos(Rack.class);
    }

    public void actualizarRack(Rack rack) {
        repositorio.iniciarTransaccion();
        repositorio.modificar(rack);
        repositorio.confirmarTransaccion();
    }

    public void eliminarRack(Rack rack) {
        repositorio.iniciarTransaccion();
        repositorio.eliminar(rack);
        repositorio.confirmarTransaccion();
    }
}

