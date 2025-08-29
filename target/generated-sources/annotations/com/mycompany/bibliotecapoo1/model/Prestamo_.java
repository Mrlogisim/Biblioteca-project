package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prestamo.class)
public abstract class Prestamo_ {

	public static volatile SingularAttribute<Prestamo, LocalDate> fechaInicio;
	public static volatile SingularAttribute<Prestamo, LocalDate> fechaVencimiento;
	public static volatile SingularAttribute<Prestamo, Integer> id;
	public static volatile SingularAttribute<Prestamo, CopiaLibro> copia;
	public static volatile SingularAttribute<Prestamo, Miembro> miembro;

	public static final String FECHA_INICIO = "fechaInicio";
	public static final String FECHA_VENCIMIENTO = "fechaVencimiento";
	public static final String ID = "id";
	public static final String COPIA = "copia";
	public static final String MIEMBRO = "miembro";

}

