package com.zjstudio.official_website.common;

import com.zjstudio.official_website.tool.common.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 添柴灬少年
 * @version 1.0
 * @date 2019/12/9 20:55
 * TODO 父级controller
 **/
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取基础路径
     *
     * @return
     */
    protected String getBasePath() {
        HttpServletRequest request = getRequest();
        return request.getScheme() + "://"
                + request.getServerName()
                + ":" + request.getServerPort();
    }

    /**
     * 输出错误信息
     *
     * @param e
     */
    protected void outError(Exception e) {
        StackTraceElement[] temp = Thread.currentThread().getStackTrace();
        StackTraceElement ste = (StackTraceElement) temp[2];
        logger.error("====ERROR====" + this.getClass() + "." + ste.getMethodName() + "  exception:" + e.getMessage());
        getRequest().setAttribute("exception", e.toString().replaceAll("\n", "<br/>"));
    }

    /**
     * 获取访问者IP
     *
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @return
     */
    protected String getAddr() throws Exception {
        HttpServletRequest request = getRequest();
        String unknown = "unknown";
        String ip = request.getHeader("X-Real-IP");
        if (!CommonUtil.isNullPointer(ip) && !CommonUtil.isEmpty(ip) && !unknown.equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!CommonUtil.isNullPointer(ip) && !CommonUtil.isEmpty(ip) && !unknown.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 得到request对象
     */
    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}
