package in.ashokit.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Categories.class)
public class Categories_ {
	public static volatile SingularAttribute<Categories, Long> id;
	public static volatile SingularAttribute<Categories, String> categoryName;
	public static volatile SingularAttribute<Categories, Subjects> subject;
}