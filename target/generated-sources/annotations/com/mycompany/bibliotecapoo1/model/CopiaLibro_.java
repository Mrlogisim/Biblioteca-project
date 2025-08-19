package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CopiaLibro.class)
public abstract class CopiaLibro_ {

	public static volatile SingularAttribute<CopiaLibro, Libro> libro;
	public static volatile SingularAttribute<CopiaLibro, TipoCopia> tipo;
	public static volatile SingularAttribute<CopiaLibro, EstadoCopia> estado;
	public static volatile SingularAttribute<CopiaLibro, Rack> rack;
	public static volatile SingularAttribute<CopiaLibro, Boolean> esDeReferencia;
	public static volatile SingularAttribute<CopiaLibro, Integer> id;

	public static final String LIBRO = "libro";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String RACK = "rack";
	public static final String ES_DE_REFERENCIA = "esDeReferencia";
	public static final String ID = "id";

}

