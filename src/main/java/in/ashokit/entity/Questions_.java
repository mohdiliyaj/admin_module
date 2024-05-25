package in.ashokit.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Questions.class)
public class Questions_ {
	public static volatile SingularAttribute<Questions, Categories> category;
}