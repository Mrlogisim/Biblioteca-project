/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.service;

import com.mycompany.bibliotecapoo1.model.Miembro;
import com.mycompany.bibliotecapoo1.model.Usuario;
import com.mycompany.bibliotecapoo1.model.Bibliotecario;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MiembroServicio {

    private final Repositorio repositorio;

    public MiembroServicio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    // --- CRUD genérico ---
    public void guardar(Miembro miembro) {
        try {
            repositorio.iniciarTransaccion();
            repositorio.insertar(miembro);
            repositorio.confirmarTransaccion();
        } catch (Exception e) {
            repositorio.descartarTransaccion();
            throw e;
        }
    }

    public void actualizar(Miembro miembro) {
        try {
            repositorio.iniciarTransaccion();
            repositorio.modificar(miembro);
            repositorio.confirmarTransaccion();
        } catch (Exception e) {
            repositorio.descartarTransaccion();
            throw e;
        }
    }

    public void eliminar(Miembro miembro) {
        try {
            repositorio.iniciarTransaccion();
            repositorio.eliminar(miembro);
            repositorio.confirmarTransaccion();
        } catch (Exception e) {
            repositorio.descartarTransaccion();
            throw e;
        }
    }

    public Miembro buscarPorId(int id) {
        return repositorio.obtenerPorId(Miembro.class, id);
    }


    public Miembro buscarPorClave(String clave) {
        return repositorio.buscarPorClave(Miembro.class, clave);
    }
    

    public List<Miembro> obtenerTodos() {
        return repositorio.obtenerTodos(Miembro.class);
    }

    // --- Métodos específicos por tipo ---
    public List<Usuario> obtenerUsuarios() {
        return repositorio.getEntityManager()
                .createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }

    public List<Bibliotecario> obtenerBibliotecarios() {
        return repositorio.getEntityManager()
                .createQuery("SELECT b FROM Bibliotecario b", Bibliotecario.class)
                .getResultList();
    }

    // --- Login básico ---
    public Miembro login(String nombre, String clave) {
        TypedQuery<Miembro> query = repositorio.getEntityManager().createQuery(
                "SELECT m FROM Miembro m WHERE m.nombre = :nombre AND m.clave = :clave",
                Miembro.class
        );
        query.setParameter("nombre", nombre);
        query.setParameter("clave", clave);

        List<Miembro> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}

