package in.ashokit.spec;

import org.springframework.data.jpa.domain.Specification;

import in.ashokit.entity.Categories_;
import in.ashokit.entity.Questions;
import in.ashokit.entity.Questions_;
import in.ashokit.entity.Subjects_;

public class QuestionSpecification {

	public static Specification<Questions> likeQuestionName(String name) {
		return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("questionValue"), "%" + name + "%");
	}
	public static Specification<Questions> likeCategoryName(String name) {
		return (root, query, cb) -> {
			return cb.like(root.get(Questions_.category).get(Categories_.categoryName), "%" + name + "%");
		};
	}

}
