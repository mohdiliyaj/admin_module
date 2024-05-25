package in.ashokit.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import in.ashokit.exception.CategoryNotFoundException;
import in.ashokit.exception.QuestionNotFoundException;
import in.ashokit.exception.SubjectNotFoundException;
import in.ashokit.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private IAdminService adminService;

	public AdminController(IAdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/addSubject")
	public ResponseEntity<String> saveSubject(@RequestBody Subjects subject) {
		Subjects subjectByName = adminService.getSubjectByName(subject.getSubjectName());
		if (subjectByName != null) {
			return new ResponseEntity<>("record is already avaliable", HttpStatus.CONFLICT);
		}
		boolean saveSubject = adminService.saveSubject(subject);
		if (saveSubject) {
			return new ResponseEntity<>("record created successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to insert the record", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getAllSubjects")
	public ResponseEntity<SubjectsResponse> getAllSubjects(@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "size", defaultValue = "7") int pageSize,
			@RequestParam(value = "name", required = false) String subjectName) {
		SubjectsResponse subjects = adminService.getAllSubejects(pageNo, pageSize, subjectName);
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}

	@GetMapping("/getSubject/{subjectId}")
	public ResponseEntity<Subjects> getSubjectBySubjectId(@PathVariable("subjectId") Integer SubjectId) {
		Subjects bySubjectId = adminService.getBySubjectId(SubjectId);
		if (bySubjectId != null) {
			return new ResponseEntity<>(bySubjectId, HttpStatus.OK);
		}
		throw new SubjectNotFoundException("Invalid subject Id");
	}
	
	@GetMapping("/getSubjects")
	public ResponseEntity<List<Subjects>> getSubjects(){
		List<Subjects> subjects = adminService.getSubjects();
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}

	@PutMapping("/editSubject/{subjectId}")
	public ResponseEntity<String> editSubject(@PathVariable("subjectId") Integer subjectId,
			@RequestBody Subjects subject) {
		Subjects bySubjectId = adminService.getBySubjectId(subjectId);
		if (bySubjectId != null) {
			boolean updateSubject = adminService.updateSubject(subject);
			if (updateSubject) {
				return new ResponseEntity<>("Subject record updated successfully", HttpStatus.OK);
			}
			return new ResponseEntity<>("Unable to updated the subject record", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Subject not found by Id : " + subjectId, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/addCategory")
	public ResponseEntity<String> saveCategory(@RequestBody CategoryRequest category) {
		Categories categoryByName = adminService.getCategoryByName(category.getCategoryName());
		if (categoryByName != null) {
			return new ResponseEntity<>("Category already exists", HttpStatus.CONFLICT);
		}
		boolean saveCategory = adminService.saveCategory(category);
		if (saveCategory) {
			return new ResponseEntity<>("Category saved successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to save category", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<Categories> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		Categories categoryById = adminService.getCategoryById(categoryId);
		if (categoryById != null) {
			return new ResponseEntity<>(categoryById, HttpStatus.OK);
		}
		throw new CategoryNotFoundException("Category not found");
	}

	@GetMapping("/getAllCategories")
	public ResponseEntity<CategoriesResponse> getAllCategories(
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "size", defaultValue = "7") int pageSize,
			@RequestParam(value = "categoryName", required = false) String categoryName,
			@RequestParam(value = "subjectName", required = false) String subjectName) {

		CategorySearchCriteria searchFilter = new CategorySearchCriteria();
		if (categoryName != null) {
			searchFilter.setCategoryName(categoryName);
		}
		if (subjectName != null) {
			searchFilter.setSubjectName(subjectName);
		}
		CategoriesResponse allCategories = adminService.getAllCategories(pageNo, pageSize, searchFilter);
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}
	
	@GetMapping("/getCategories/{subjectId}")
	public ResponseEntity<List<Categories>> getCategories(@PathVariable("subjectId") Integer subjectId){
		List<Categories> categories = adminService.getCategories(subjectId);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PutMapping("/editCategory/{categoryId}")
	public ResponseEntity<String> editCategory(@PathVariable("categoryId") Integer categoryId,
			@RequestBody Categories category) {
		Categories categoryById = adminService.getCategoryById(categoryId);
		if (categoryById != null) {
			boolean updateCategory = adminService.updateCategory(category);
			if (updateCategory) {
				return new ResponseEntity<>("Category details updated successfully", HttpStatus.OK);
			}
			return new ResponseEntity<>("Failed to update the details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Category not available" + categoryId, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/addQuestion")
	public ResponseEntity<String> saveQuestion(@RequestBody QuestionRequest question) {
		Questions questionByName = adminService.getQuestionByName(question.getQuestionValue());
		if (questionByName != null) {
			return new ResponseEntity<>("question already exists", HttpStatus.CONFLICT);
		}
		boolean saveQuestion = adminService.saveQuestion(question);
		if (saveQuestion) {
			return new ResponseEntity<>("question inserted successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getQuestion/{questionId}")
	public ResponseEntity<Questions> getQuestion(@PathVariable("questionId") Integer questionId) {
		Questions questionById = adminService.getQuestionById(questionId);
		if (questionById != null) {
			return new ResponseEntity<>(questionById, HttpStatus.OK);
		}
		throw new QuestionNotFoundException("Question not found");
	}

	@GetMapping("/getAllQuestions")
	public ResponseEntity<QuestionsResponse> getAllQuestions(
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "size", defaultValue = "7") int pageSize,
			@RequestParam(value = "question", required = false) String questionName,
			@RequestParam(value = "categoryName", required = false) String categoryName) {

		QuestionSearchCriteria search = new QuestionSearchCriteria();
		if (questionName != null) {
			search.setQuestionName(questionName);
		}
		if(categoryName != null) {
			search.setCategoryName(categoryName);
		}
		
		QuestionsResponse allQuestions = adminService.getAllQuestions(pageNo, pageSize, search);
		return new ResponseEntity<>(allQuestions, HttpStatus.OK);
	}
	
	@GetMapping("/getQuestions/{categoryId}")
	public ResponseEntity<List<Questions>> getAllQuestions(@PathVariable("categoryId") Integer categoryId){
		List<Questions> questions = adminService.getQuestions(categoryId);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	@PutMapping("/editQuestion/{questionId}")
	public ResponseEntity<String> editQuestion(@PathVariable("questionId")Integer questionId, 
			@RequestBody QuestionRequest question){
		Questions questionById = adminService.getQuestionById(questionId);
		if(questionById != null) {
			boolean updateQuestion = adminService.updateQuestion(question);
			if(updateQuestion) {
				return new ResponseEntity<>("Question details updated successfully", HttpStatus.OK);
			}
			return new ResponseEntity<>("Failed to update the details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Question not available", HttpStatus.NOT_FOUND);
	}
}
