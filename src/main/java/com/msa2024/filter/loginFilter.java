package com.msa2024.filter;


import com.msa2024.users.UserVO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
/**
 * Servlet Filter implementation class LoginFilter
 */
@Slf4j
public class loginFilter implements Filter {
    Set<String> actionSet = new HashSet<String>();

    /**
     * @see jakarta.servlet.http.HttpFilter#HttpFilter()
     */
    public loginFilter() {
        super();

    }


    public void destroy() {
        // TODO Auto-generated method stub
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //사용자가 요청한 URL 얻기
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserVO loginVO = (UserVO) session.getAttribute("loginVO");
        String url = req.getRequestURI();

        log.info("loginFilter url = " + url);
        if (!actionSet.contains(url)) {
            if (loginVO == null) {
                //로그인 되지 않았으면 로그인 페이지로 이동한다
                resp.sendRedirect("/users/loginForm");
                return;
            }
        }
        //정상 처리
        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {
        actionSet.add("/users/loginForm");
        actionSet.add("/users/login");
        actionSet.add("/users/insertForm");
        actionSet.add("/users/insert");
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}