package in.ashokit.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Categories;
import in.ashokit.entity.Questions;
import java.util.List;


public interface QuestionsRepo extends JpaRepository<Questions, Integer>{
	
	public Questions findByQuestionValue(String question);
	
	public Page<Questions> findAll(Specification<Questions> spec, Pageable pageable);
	
	public List<Questions> findByCategory(Categories category);
}
