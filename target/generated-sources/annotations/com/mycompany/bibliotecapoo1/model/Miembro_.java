package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Miembro.class)
public abstract class Miembro_ {

	public static volatile SingularAttribute<Miembro, String> clave;
	public static volatile SingularAttribute<Miembro, EstadoMiembro> estado;
	public static volatile SingularAttribute<Miembro, String> apellido;
	public static volatile SingularAttribute<Miembro, Integer> id;
	public static volatile SingularAttribute<Miembro, String> nombre;
	public static volatile ListAttribute<Miembro, Prestamo> prestamos;
	public static volatile SingularAttribute<Miembro, Boolean> activo;

	public static final String CLAVE = "clave";
	public static final String ESTADO = "estado";
	public static final String APELLIDO = "apellido";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String PRESTAMOS = "prestamos";
	public static final String ACTIVO = "activo";

}

