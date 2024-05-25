package in.ashokit.binding;

import java.util.List;

import in.ashokit.entity.Questions;

public class QuestionsResponse {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalRecords;
	private List<Questions> allQuestions;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<Questions> getAllQuestions() {
		return allQuestions;
	}
	public void setAllQuestions(List<Questions> allQuestions) {
		this.allQuestions = allQuestions;
	}
}
