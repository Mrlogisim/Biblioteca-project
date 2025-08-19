package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Libro.class)
public abstract class Libro_ {

	public static volatile SingularAttribute<Libro, String> editorial;
	public static volatile SingularAttribute<Libro, String> isbn;
	public static volatile SingularAttribute<Libro, CategoriaLibro> categoria;
	public static volatile ListAttribute<Libro, CopiaLibro> copias;
	public static volatile SingularAttribute<Libro, String> titulo;
	public static volatile SingularAttribute<Libro, String> idioma;
	public static volatile SingularAttribute<Libro, Integer> id;
	public static volatile SingularAttribute<Libro, String> autor;

	public static final String EDITORIAL = "editorial";
	public static final String ISBN = "isbn";
	public static final String CATEGORIA = "categoria";
	public static final String COPIAS = "copias";
	public static final String TITULO = "titulo";
	public static final String IDIOMA = "idioma";
	public static final String ID = "id";
	public static final String AUTOR = "autor";

}

