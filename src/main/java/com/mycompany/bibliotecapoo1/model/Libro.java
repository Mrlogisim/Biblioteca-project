/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "autor")
    private String autor;
    
    @Column(name = "editorial")
    private String editorial;
    
    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "idioma")
    private String idioma;

    @Enumerated(EnumType.STRING)
    private CategoriaLibro categoria;

    @OneToMany(mappedBy = "libro")
    private List<CopiaLibro> copias;

    
    
    // Constructores
    public Libro() {
    }

    public Libro(int id, String titulo, String autor, String editorial, String isbn, String idioma, CategoriaLibro categoria, List<CopiaLibro> copias) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.idioma = idioma;
        this.categoria = categoria;
        this.copias = copias;
    }
  
    
    
    // Getters 

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public CategoriaLibro getCategoria() {
        return categoria;
    }

    public List<CopiaLibro> getCopias() {
        return copias;
    }

    
   
    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setCategoria(CategoriaLibro categoria) {
        this.categoria = categoria;
    }

    public void setCopias(List<CopiaLibro> copias) {
        this.copias = copias;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

