package com.bjtu.zs.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjtu.zs.util.RegUtils;

/**
 * @className: LoginFilter
 * @Description:登录验证过滤器
 * @author zengshuang
 * @date 2017年2月24日14:26:37
 *
 */
public class LoginFilter implements Filter {
	/** 不需要拦截的页面 **/
	private String excludedPages;

	/** 不需要拦截的页面（数组） **/
	private String[] excludedPagesArray;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		HttpSession session = request.getSession();

		boolean isExcludedPage = false;

		// 请求路径
		String path = request.getRequestURI();
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();

		String user = (String) session.getAttribute("user");

		if (null != excludedPagesArray) {
			// 排除不需要拦截的页面
			for (String page : excludedPagesArray) {
				// TODO 没看懂
				// 如果当前请求的页面是登录页面
				if (RegUtils.isMatch("(?i)^.*login\\.jsp$", servletPath)) {
					isExcludedPage = true;
					continue;
				}
				// 如果当前请求的页面是不需要拦截的页面，则标记为不拦截
				if (RegUtils.isMatch(page, servletPath)) {
					isExcludedPage = true;
					break;
				} else {// 如果当前页面需要拦截

				}
			}
		}

		//servletPath.indexOf("login.jsp") > -1
		if (isExcludedPage) {// 不需要拦截的页面直接访问
			chain.doFilter(request, response);
			return;
		} else {// 需要拦截的页面则判断是否登录
			if (null == user) {
				response.sendRedirect(contextPath+"/pages/login/login.jsp");
			} else {
				chain.doFilter(request, response);
			}

		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 保存要
		this.excludedPages = config.getInitParameter("excludedPages");

		if (!excludedPages.isEmpty()) {
			this.excludedPagesArray = excludedPages.split(",");
		} else {

		}
		return;
	}

}