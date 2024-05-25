package in.ashokit.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Subjects.class)
public class Subjects_ {
	public static volatile SingularAttribute<Subjects, String> subjectName;
}