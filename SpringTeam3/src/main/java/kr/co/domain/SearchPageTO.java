package kr.co.domain;

public class SearchPageTO<T> extends PageTO<T>{
	private String searchType;
	private String keyword;
	
	
	public SearchPageTO() {
		// TODO Auto-generated constructor stub
	}


	public SearchPageTO(String searchType, String keyword, int curPage) {
		super(curPage);
		this.searchType = searchType;
		this.keyword = keyword;
	}


	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
	

}
