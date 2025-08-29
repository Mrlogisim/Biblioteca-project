package com.mycompany.bibliotecapoo1.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rack.class)
public abstract class Rack_ {

	public static volatile SingularAttribute<Rack, String> ubicacion;
	public static volatile ListAttribute<Rack, CopiaLibro> copias;
	public static volatile SingularAttribute<Rack, Integer> id;

	public static final String UBICACION = "ubicacion";
	public static final String COPIAS = "copias";
	public static final String ID = "id";

}

