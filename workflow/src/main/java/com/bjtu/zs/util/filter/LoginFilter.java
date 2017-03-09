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

		//请求的项目访问地址
		String contextPath = request.getContextPath();
		//请求的资源路径
		String servletPath = request.getServletPath();

		Object user = session.getAttribute("user");

		if (null != excludedPagesArray) {
			// 排除不需要拦截的页面
			for (String page : excludedPagesArray) {
				// TODO 没看懂
				// 如果当前请求的页面是登录页面，需要先判断是否登录，之后再决定是否跳转到登录界面
				if (RegUtils.isMatch("(?i)^.*login\\.jsp$", servletPath)) {
//					isExcludedPage = true;
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
		} else {
			//如果访问的资源不是登录界面，并且用户未登录则跳转到登录界面
/*			if (null == user && !"/pages/login/login.jsp".equals(servletPath)) {
				response.sendRedirect(contextPath+"/pages/login/login.jsp");
			} else if(null != user && "/pages/login/login.jsp".equals(servletPath)){//如果当前用户访问的资源是登录界面，并且用户已登录，则跳转到首页
				response.sendRedirect(contextPath+"/pages/main/main.jsp");
			}else{//1.用户访问的是登录界面，并且未登录，则不过滤；2用户访问的界面不是登录界面；并且已登录，则不过滤
				chain.doFilter(request, response);
			}
*/
			if(null == user){
				if (RegUtils.isMatch("(?i)^.*\\.jsp$", servletPath)&& !"/pages/login/login.jsp".equals(servletPath)) {
					response.sendRedirect(contextPath + "/pages/login/login.jsp");
				} else if (RegUtils.isMatch("(?i)^.*\\.(js|css|png|jpg|icon|woff)$", servletPath)) {
					chain.doFilter(req, rep);
				} else if (RegUtils.isMatch("^/$", servletPath)) {
					response.sendRedirect(contextPath + "/pages/login/login.jsp");
				} else if("/pages/login/login.jsp".equals(servletPath)){
					chain.doFilter(req, rep);
				}
			}else{
				if (RegUtils.isMatch("(?i)^.*login\\.jsp$", servletPath)) {
					response.sendRedirect(contextPath + "/pages/main/main.jsp");
				} 
				else if (RegUtils.isMatch("^/$", servletPath)) {
					response.sendRedirect(contextPath + "/pages/main/main.jsp");
				} 
				else{
					chain.doFilter(req, rep);
				}
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
	
    /** 
     * 判断是否为Ajax请求 
     * 
     * @param request HttpServletRequest 
     * @return 是true, 否false 
     */  
    public static boolean isAjaxRequest(HttpServletRequest request) {  
//        return request.getRequestURI().startsWith("/api");  
        String requestType = request.getHeader("X-Requested-With");  
        return requestType != null && requestType.equals("XMLHttpRequest");  
    }  

}
