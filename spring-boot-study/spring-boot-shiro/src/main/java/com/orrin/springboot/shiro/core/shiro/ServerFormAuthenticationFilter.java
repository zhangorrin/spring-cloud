package com.orrin.springboot.shiro.core.shiro;

import com.orrin.springboot.shiro.core.utils.WebUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: Zhang aolin
 * <p>Date: 14-3-16
 * <p>Version: 1.0
 */
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(ServerFormAuthenticationFilter.class);

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new UsernamePasswordCaptchaToken(username,
                password.toCharArray(), rememberMe, host, captcha);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if(request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {

        String baseUrl = WebUtil.getWholeUrl(WebUtils.toHttp(request));

        logger.info("go to = {}", baseUrl + "/pages/index");
        WebUtils.issueRedirect(request, response, baseUrl + "/pages/index");

    }

}
