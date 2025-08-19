/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

import java.util.List;

public class Repositorio {

    private final EntityManager em;

    public Repositorio(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void iniciarTransaccion() {
        em.getTransaction().begin();
    }

    public void confirmarTransaccion() {
        em.getTransaction().commit();
    }

    public void descartarTransaccion() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    public void insertar(Object o) {
        em.persist(o);
    }

    public void modificar(Object o) {
        em.merge(o);
    }

    public void eliminar(Object o) {
        if (!em.contains(o)) {
            o = em.merge(o);
        }
        em.remove(o);
    }

    public void refrescar(Object o) {
        em.refresh(o);
    }

    public <T> T obtenerPorId(Class<T> clase, Object id) {
        return em.find(clase, id);
    }

    public <T> List<T> obtenerTodos(Class<T> clase) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clase);
        Root<T> root = cq.from(clase);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    public <T, P> List<T> obtenerTodosOrdenadosPor(Class<T> clase, SingularAttribute<T, P> atributo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clase);
        Root<T> root = cq.from(clase);
        Order orden = cb.asc(root.get(atributo));
        cq.select(root).orderBy(orden);
        return em.createQuery(cq).getResultList();
    }

    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
    }
}
