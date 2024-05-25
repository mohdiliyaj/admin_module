package in.ashokit.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Categories;
import in.ashokit.entity.Subjects;

public interface CategoriesRepo extends JpaRepository<Categories, Integer> {
	
	public Categories findByCategoryName(String name);
	
	public Page<Categories> findAll(Specification<Categories> spec, Pageable pageable);
	
	public List<Categories> findBySubject(Subjects subject);
	
}
