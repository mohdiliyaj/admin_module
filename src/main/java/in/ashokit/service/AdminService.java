package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import in.ashokit.binding.CategoriesResponse;
import in.ashokit.binding.CategoryRequest;
import in.ashokit.binding.CategorySearchCriteria;
import in.ashokit.binding.QuestionRequest;
import in.ashokit.binding.QuestionSearchCriteria;
import in.ashokit.binding.QuestionsResponse;
import in.ashokit.binding.SubjectsResponse;
import in.ashokit.entity.Answer;
import in.ashokit.entity.Categories;
import in.ashokit.entity.Options;
import in.ashokit.entity.Questions;
import in.ashokit.entity.Subjects;
import in.ashokit.repo.AnswerRepo;
import in.ashokit.repo.CategoriesRepo;
import in.ashokit.repo.OptionsRepo;
import in.ashokit.repo.QuestionsRepo;
import in.ashokit.repo.SubjectsRepo;
import in.ashokit.spec.CategorySpecification;
import in.ashokit.spec.QuestionSpecification;
import in.ashokit.spec.SubjectSpecification;
import jakarta.transaction.Transactional;

@Service
public class AdminService implements IAdminService {

	private SubjectsRepo subjectRepo;
	private CategoriesRepo categoryRepo;
	private QuestionsRepo questionRepo;
	private OptionsRepo optionRepo;
	private AnswerRepo answerRepo;

	public AdminService(SubjectsRepo subjectRepo, CategoriesRepo categoryRepo, QuestionsRepo questionRepo,
			OptionsRepo optionRepo, AnswerRepo answerRepo) {
		this.subjectRepo = subjectRepo;
		this.categoryRepo = categoryRepo;
		this.questionRepo = questionRepo;
		this.optionRepo = optionRepo;
		this.answerRepo = answerRepo;
	}

	@Override
	public Subjects getSubjectByName(String name) {
		return subjectRepo.findBySubjectNameIgnoreCase(name);
	}

	@Override
	public Subjects getBySubjectId(Integer subjectsId) {
		Optional<Subjects> byId = subjectRepo.findById(subjectsId);
		if (byId.isPresent()) {
			return byId.get();
		}
		return null;
	}

	@Override
	public boolean saveSubject(Subjects subject) {
		Subjects save = subjectRepo.save(subject);
		if (save != null) {
			return true;
		}
		return false;
	}

	@Override
	public SubjectsResponse getAllSubejects(int pageNo, int pageSize, String name) {
		Specification<Subjects> spec = Specification.where(null);
		if (name != null) {
			spec = spec.and(SubjectSpecification.likeName(name.toLowerCase()));
		}
		Pageable of = PageRequest.of(pageNo - 1, pageSize);
		Page<Subjects> all = subjectRepo.findAll(spec, of);
		SubjectsResponse res = new SubjectsResponse();
		res.setCurrentPage(all.getNumber() + 1);
		res.setTotalPages(all.getTotalPages());
		res.setPageSize(all.getSize());
		res.setTotalRecords(all.getTotalElements());
		res.setAllSubjects(all.getContent());
		return res;
	}
	
	@Override
	public List<Subjects> getSubjects() {
		return subjectRepo.findAll();
	}

	@Override
	public boolean updateSubject(Subjects subject) {
		Optional<Subjects> byId = subjectRepo.findById(subject.getSubjectId());
		if (byId.isPresent()) {
			Subjects retreivedsubject = byId.get();
			if (!retreivedsubject.getSubjectName().equals(subject.getSubjectName())) {
				retreivedsubject.setSubjectName(subject.getSubjectName());
			}
			if (!retreivedsubject.getSubjectDesc().equals(subject.getSubjectDesc())) {
				retreivedsubject.setSubjectDesc(subject.getSubjectDesc());
			}
			Subjects save = subjectRepo.save(retreivedsubject);
			if (save != null) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Categories getCategoryByName(String name) {
		return categoryRepo.findByCategoryName(name);
	}

	@Override
	public Categories getCategoryById(Integer categoryId) {
		Optional<Categories> byId = categoryRepo.findById(categoryId);
		if (byId.isPresent()) {
			return byId.get();
		}
		return null;
	}

	@Override
	public boolean saveCategory(CategoryRequest category) {
		Categories cate = new Categories();
		cate.setCategoryName(category.getCategoryName());
		cate.setCategoryDesc(category.getCategoryDesc());
		cate.setUserId(category.getUserId());
		Subjects subject = new Subjects();
		subject.setSubjectId(category.getSubjectId());
		cate.setSubject(subject);
		Categories save = categoryRepo.save(cate);
		if (save != null) {
			return true;
		}
		return false;
	}

	@Override
	public CategoriesResponse getAllCategories(int pageNo, int pageSize, CategorySearchCriteria searchCriteria) {
		Specification<Categories> spec = Specification.where(null);
		if (searchCriteria != null) {
			if (searchCriteria.getCategoryName() != null) {
				spec = spec.and(CategorySpecification.likeCategoryName(searchCriteria.getCategoryName()));
			}
			if (searchCriteria.getSubjectName() != null) {
				spec = spec.and(CategorySpecification.likeSubjectName(searchCriteria.getSubjectName()));
			}
		}
		Pageable of = PageRequest.of(pageNo - 1, pageSize);
		Page<Categories> all = categoryRepo.findAll(spec, of);
		CategoriesResponse response = new CategoriesResponse();
		response.setCurrentPage(all.getNumber() + 1);
		response.setPageSize(all.getSize());
		response.setTotalPages(all.getTotalPages());
		response.setTotalRecords(all.getTotalElements());
		response.setAllCategories(all.getContent());
		return response;
	}
	
	@Override
	public List<Categories> getCategories(Integer subjectId) {
		Subjects subject = new Subjects();
		subject.setSubjectId(subjectId);
		return categoryRepo.findBySubject(subject);
	}

	@Override
	public boolean updateCategory(Categories category) {
		Optional<Categories> byId = categoryRepo.findById(category.getCategoryId());
		if (byId.isPresent()) {
			Categories categories = byId.get();
			if (!categories.getCategoryName().equals(category.getCategoryName())) {
				categories.setCategoryName(category.getCategoryName());
			}
			if (!categories.getCategoryDesc().equals(category.getCategoryDesc())) {
				categories.setCategoryDesc(category.getCategoryDesc());
			}
			Categories save = categoryRepo.save(categories);
			if (save != null) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public Questions getQuestionByName(String question) {
		return questionRepo.findByQuestionValue(question);
	}

	@Override
	@Transactional(rollbackOn = DataAccessException.class)
	public boolean saveQuestion(QuestionRequest question) {
		Categories category = new Categories();
		category.setCategoryId(question.getCategoryId());
		Questions questions = new Questions();
		questions.setCategory(category);
		questions.setUser(question.getUserId());
		questions.setQuestionValue(question.getQuestionValue());
		Questions save = questionRepo.save(questions);
		for (int i = 1; i <= 4; i++) {
			Options option = new Options();
			option.setOptionNumber(i);
			option.setQuestion(save);
			if (i == 1) {
				option.setOptionValue(question.getOptionOne());
			}
			if (i == 2) {
				option.setOptionValue(question.getOptionTwo());
			}
			if (i == 3) {
				option.setOptionValue(question.getOptionThree());
			}
			if (i == 4) {
				option.setOptionValue(question.getOptionFour());
			}
			optionRepo.save(option);
		}
		Answer answer = new Answer();
		answer.setCorrectAnswer(question.getCorrectOption());
		answer.setQuestion(save);
		Answer save2 = answerRepo.save(answer);
		return save2 != null ? true : false;
	}

	@Override
	public Questions getQuestionById(Integer questionId) {
		Optional<Questions> byId = questionRepo.findById(questionId);
		if (byId.isPresent()) {
			return byId.get();
		}
		return null;
	}

	@Override
	public QuestionsResponse getAllQuestions(int pageNo, int pageSize, QuestionSearchCriteria searchCriteria) {
		Specification<Questions> spec = Specification.where(null);
		if (searchCriteria != null) {
			if (searchCriteria.getQuestionName() != null) {
				spec = spec.and(QuestionSpecification.likeQuestionName(searchCriteria.getQuestionName()));
			}
			if (searchCriteria.getCategoryName() != null) {
				spec = spec.and(QuestionSpecification.likeCategoryName(searchCriteria.getCategoryName()));
			}
		}

		Pageable of = PageRequest.of(pageNo - 1, pageSize);
		Page<Questions> all = questionRepo.findAll(spec, of);
		QuestionsResponse response = new QuestionsResponse();
		response.setCurrentPage(all.getNumber() + 1);
		response.setPageSize(all.getSize());
		response.setTotalPages(all.getTotalPages());
		response.setTotalRecords(all.getTotalElements());
		response.setAllQuestions(all.getContent());
		return response;
	}

	@Override
	@Transactional(rollbackOn = DataAccessException.class)
	public boolean updateQuestion(QuestionRequest question) {
		Optional<Questions> byId = questionRepo.findById(question.getQuestionId());
		if (byId.isPresent()) {
			Questions questions = byId.get();
			if (!questions.getQuestionValue().equals(question.getQuestionValue())) {
				questions.setQuestionValue(question.getQuestionValue());
			}
			Questions save = questionRepo.save(questions);
			Answer answer = questions.getAnswer();
			answer.setCorrectAnswer(question.getCorrectOption());
			answerRepo.save(answer);
			for (int i = 0; i < questions.getOptions().size(); i++) {
				Options options = questions.getOptions().get(i);
				if (i == 0) {
					options.setOptionValue(question.getOptionOne());
				}
				if (i == 1) {
					options.setOptionValue(question.getOptionTwo());
				}
				if (i == 2) {
					options.setOptionValue(question.getOptionThree());
				}
				if (i == 3) {
					options.setOptionValue(question.getOptionFour());
				}
				optionRepo.save(options);
			}
			return save!=null ? true : false;
		}
		return false;
	}
	
	@Override
	public List<Questions> getQuestions(Integer categoryId) {
		Categories category = new Categories();
		category.setCategoryId(categoryId);
		return questionRepo.findByCategory(category);
	}
}
