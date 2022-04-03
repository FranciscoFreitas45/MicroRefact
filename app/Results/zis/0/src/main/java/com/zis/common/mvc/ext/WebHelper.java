package com.zis.common.mvc.ext;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class WebHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(WebHelper.class);
	
	private static final String KEY_PAGE = "page";
	private static final String KEY_SIZE = "size";
	private static final String KEY_SORT = "sort";
	private static final String KEY_DIRECTION = "direction";
	
	/** 默认每页展示记录数 */
	public static final Integer DEFAULT_PAGE_SIZE = 20;
	/** 默认排序方式 */
	public static final String DEFAULT_DIRECTION = "desc";

	/**
	 * 封装分页查询参数
	 * @param request
	 * @return
	 */
	public static Pageable buildPageRequest(HttpServletRequest request) {
		Integer page = getIntRequestParam(request, KEY_PAGE);
		if(page == null || page < 0) {
			page = 0;
		}
		Integer size = getIntRequestParam(request, KEY_SIZE);
		if(size == null || size <0) {
			size = DEFAULT_PAGE_SIZE;
		}
		String[] sorts = request.getParameterValues(KEY_SORT);
		if(sorts == null) {
			return new PageRequest(page, size);
		} else {
			String directionStr = request.getParameter(KEY_DIRECTION);
			Direction direction = DEFAULT_DIRECTION.equals(directionStr) ? Direction.DESC : Direction.ASC;
			return new PageRequest(page, size, direction, sorts);
		}
	}

	/**
	 * 提取参数值为Integer
	 * @param request
	 * @param paramName 参数名称
	 * @return 如果转换失败，返回null
	 */
	public static Integer getIntRequestParam(HttpServletRequest request,
			String paramName) {
		String valStr = request.getParameter(paramName);
		try {
			return Integer.valueOf(valStr);
		} catch (NumberFormatException e) {
			logger.info("failed to convert param '"+paramName+"' to Integer:" + valStr);
			return null;
		}
	}
	
	/**
	 * 提取参数值为Double
	 * @param request
	 * @param paramName 参数名称
	 * @return 如果转换失败，返回null
	 */
	public static Double getDoubleRequestParam(HttpServletRequest request,
			String paramName) {
		String valStr = request.getParameter(paramName);
		try {
			return Double.valueOf(valStr);
		} catch (NumberFormatException e) {
			logger.info("failed to convert param '"+paramName+"' to Double:" + valStr);
			return null;
		}
	}
}
