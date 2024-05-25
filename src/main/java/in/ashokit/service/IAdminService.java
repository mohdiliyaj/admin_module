package in.ashokit.service;


import java.util.List;

import in.ashokit.binding.CategoriesResponse;
import in.ashokit.binding.CategoryRequest;
import in.ashokit.binding.CategorySearchCriteria;
import in.ashokit.binding.QuestionRequest;
import in.ashokit.binding.QuestionSearchCriteria;
import in.ashokit.binding.QuestionsResponse;
import in.ashokit.binding.SubjectsResponse;
import in.ashokit.entity.Categories;
import in.ashokit.entity.Questions;
import in.ashokit.entity.Subjects;

public interface IAdminService {
	
	public Subjects getSubjectByName(String name);
	
	public boolean saveSubject(Subjects subject);

	public Subjects getBySubjectId(Integer subjectsId);
	
	public boolean updateSubject(Subjects subject);

	public SubjectsResponse getAllSubejects(int pageNo, int pageSize, String name);
	
	public List<Subjects> getSubjects();
	
	public Categories getCategoryByName(String name);
	
	public boolean saveCategory(CategoryRequest category);
	
	public Categories getCategoryById(Integer categoryId);
	
	public boolean updateCategory(Categories category);

	public CategoriesResponse getAllCategories(int pageNo, int pageSize, CategorySearchCriteria searchCriteria);
	
	public List<Categories> getCategories(Integer subjectId);
	
	public Questions getQuestionByName(String question);
	
	public boolean saveQuestion(QuestionRequest question);
	
	public Questions getQuestionById(Integer questionId);
	
	public boolean updateQuestion(QuestionRequest question);
	
	public QuestionsResponse getAllQuestions(int pageNo, int pageSize, QuestionSearchCriteria searchCriteria);
	
	public List<Questions> getQuestions(Integer categoryId);
}
