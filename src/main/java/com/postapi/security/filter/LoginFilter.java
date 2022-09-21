package com.postapi.security.filter;

import com.google.gson.Gson;

import com.postapi.context.ContextHolderService;
import com.postapi.security.service.UserDetailsServiceImpl;
import com.postapi.user.constants.ErrorMessage;
import com.postapi.security.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class LoginFilter implements Filter {

    private final JwtTokenUtil jwtTokenUtil;
    private final ContextHolderService contextHolderService;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public LoginFilter(JwtTokenUtil jwtTokenUtil, ContextHolderService contextHolderService, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.contextHolderService = contextHolderService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String requestTokenHeader = request.getHeader("Authorization");
        String url = request.getRequestURI();
        log.info("Inside JWT Filter:: remote host :: {}, remote address:: {}, request uri:: {} ", servletRequest.getRemoteHost(), servletRequest.getRemoteAddr(), url);
        if (!isByPassUrl(url)) {
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                String jwtToken = requestTokenHeader.substring(7);
                String username;
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                } catch (ExpiredJwtException e) {
                    prepareException(response, "JWT token expired", request.getRequestURI(), HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return;
                }
                String userType = jwtTokenUtil.getByKey(jwtToken, "userType");
                List<String> permissions = jwtTokenUtil.getPermissions(jwtToken);
                log.info("Username from token:: " + username + " " + userType);
                contextHolderService.setContext(username, userType, permissions);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                prepareException(response, "JWT token not valid", request.getRequestURI(), HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void prepareException(HttpServletResponse response, String messageString, String requestUrl, int code, String error) throws IOException {
        ErrorMessage message = new ErrorMessage(messageString, requestUrl, code, error);
        String employeeJsonString = new Gson().toJson(message);
        response.setStatus(code);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }

    @Override
    public void destroy() {

    }

    private boolean isByPassUrl(String url) {
        final String ADMIN_LOGIN = "/support/v1/api/admin/login";
        final String TERMS_CONDITION = "/support/v1/api/privacy-terms/get-terms-condition";
        final String PRIVACY_POLICY = "/support/v1/api/privacy-terms/get-privacy-policy";
        final String COUNTRY = "/support/v1/api/country";
        final String USER_REGISTER_EMAIL = "/user/register-email";
        final String USER_REGISTER_PHONE = "/user/register-phone";
        final String USER_RESEND_OTP = "/user/resend-otp";
        final String USER_VALIDATE_OTP = "/user/validate-otp";
        final String USER_LOGIN = "/user/login";
        final String USER_TEMPORARY_LOGIN = "/user/temporary-login";

        // final String REQUEST_LEAD = "/support/v1/api/customer/lead/add";
        //
        // final String VIEW_COMPANY_SERVICE =
        // "/support/v1/api/company-service/user/view";
        // final String VIEW_COMPANY_BRANCH =
        // "/support/v1/api/company-branch/user/view";

        final String WEB_SOCKET_CONNECTION = "/support/support";

        List<String> byPassUrl = Arrays.asList(ADMIN_LOGIN,TERMS_CONDITION, PRIVACY_POLICY, COUNTRY, USER_REGISTER_EMAIL, USER_REGISTER_PHONE, USER_RESEND_OTP,USER_VALIDATE_OTP, WEB_SOCKET_CONNECTION, USER_LOGIN, USER_TEMPORARY_LOGIN
                // REQUEST_LEAD, VIEW_COMPANY_SERVICE, VIEW_COMPANY_BRANCH

        );

        return byPassUrl.stream().anyMatch(url::equalsIgnoreCase);
    }
}
