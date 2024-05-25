package in.ashokit.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Subjects;

public interface SubjectsRepo extends JpaRepository<Subjects, Integer> {
	
	public Subjects findBySubjectNameIgnoreCase(String name);
	
	public Page<Subjects> findAll(Specification<Subjects> spec, Pageable pageable);
	
}
