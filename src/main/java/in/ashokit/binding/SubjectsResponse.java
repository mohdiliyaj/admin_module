package in.ashokit.binding;

import java.util.List;

import in.ashokit.entity.Subjects;

public class SubjectsResponse {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalRecords;
	private List<Subjects> allSubjects;
	
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
	public List<Subjects> getAllSubjects() {
		return allSubjects;
	}
	public void setAllSubjects(List<Subjects> allSubjects) {
		this.allSubjects = allSubjects;
	}
}
