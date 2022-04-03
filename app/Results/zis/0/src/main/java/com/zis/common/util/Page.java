package com.zis.common.util;

/**
 * 分页相关数据包装类
 * 
 * @author yz
 * 
 */
public class Page {

	/** 默认每页记录数 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	// 每页显示数量
	private int pageSize;
	// 总页数
	private int totalPageCount;
	// 当前页码
	private int currentPage;
	// 总记录数
	private int totalRecordCount;
	// 起始记录数
	private int beginIndex;
	// 是否还有下一页
	private boolean hasNext;
	// 是否还有上一页
	private boolean hasPre;

	/**
	 * 创建一个Page对象
	 * 
	 * @param currentPage
	 *            当前页码，从1开始
	 * @param pageSize
	 *            每页显示记录数量
	 * @param totalRecordCount
	 *            记录总数
	 * @return
	 */
	public static Page createPage(int currentPage, int pageSize, int totalRecordCount) {
		Page page = new Page();
		page.currentPage = initCurrentPage(currentPage);
		page.pageSize = initPageSize(pageSize);
		page.totalRecordCount = totalRecordCount;
		page.beginIndex = (currentPage - 1) * pageSize;
		page.totalPageCount = initTotalPageCount(page.totalRecordCount, page.pageSize);
		page.hasPre = (page.currentPage == 1) ? false : true;
		page.hasNext = (page.currentPage == page.totalPageCount || page.totalPageCount == 0) ? false : true;
		return page;
	}
	
	private Page() {
	}

	private static int initCurrentPage(int currentPageInput) {
		return currentPageInput < 1 ? 1 : currentPageInput;
	}

	private static int initPageSize(int pageSizeInput) {
		return pageSizeInput < 1 ? DEFAULT_PAGE_SIZE : pageSizeInput;
	}

	private static int initTotalPageCount(int totalRecordCount, int pageSize) {
		int mod = totalRecordCount % pageSize;
		if (mod == 0) {
			return totalRecordCount / pageSize;
		} else {
			return totalRecordCount / pageSize + 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPre() {
		return hasPre;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}
}
