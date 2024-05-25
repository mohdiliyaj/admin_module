package in.ashokit.spec;

import org.springframework.data.jpa.domain.Specification;

import in.ashokit.entity.Categories;
import in.ashokit.entity.Categories_;
import in.ashokit.entity.Subjects;
import jakarta.persistence.criteria.Join;

public class CategorySpecification {

	public static Specification<Categories> likeCategoryName(String name) {
		return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("categoryName"), "%" + name + "%");
	}

	public static Specification<Categories> likeSubjectName(String name) {
		return (root, query, cb) -> {
			Join<Categories, Subjects> subjectJoin = root.join(Categories_.subject);
			return cb.like(subjectJoin.get("subjectName"), "%" + name + "%");
		};
	}

}
