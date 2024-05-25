package in.ashokit.spec;

import org.springframework.data.jpa.domain.Specification;

import in.ashokit.entity.Subjects;

public class SubjectSpecification {
	
	public static Specification<Subjects> likeName(String name){
		return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("subjectName"), "%" +name+"%");
	}
	
}
