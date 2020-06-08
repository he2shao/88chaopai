package com.ruoyi.framework.shiro.web.filter.order;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.common.weixin.WeixinUtil;

public class VenueOrderFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(VenueOrderFilter.class);

	// 所有需要微信授权登录的访问页面均在此配置url
	private static final String[] uriArr = new String[] { "/system/order/add", "/system/order/myorder" };

	@Autowired
	private WeixinUtil weixinUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		log.info("当前请求路径 = {}, 当前SessionId = {}", uri, ShiroUtils.getSessionId());

		// 检查请求是否需要微信登录处理,如果不需要则直接放行
		if (!checkUri(uri)) {
			chain.doFilter(request, response);
			return;
		}
		// 访问预约场馆页面必须先微信授权登录获取微信openid
		// 从session中获取openid
		String openid = ShiroUtils.getOpenid();
		log.info("当前微信用户登录openid = {}", openid);
		// 如果缓存中存在openid，则放行请求
		if (StringUtils.isNotEmpty(openid)) {
			chain.doFilter(request, response);
			return;
		}
		// 微信授权页面
		String redirectUri = weixinUtil.getWeixinRedirectUrl(uri);
		// 如果缓存中不存在openid，则请求微信开放平台进行授权登录，获取到openid
		// 微信授权登录，用户点击允许后，微信会回传code参数，
		// 取出code参数并进行微信接口调用拿到微信用户的openid
		Map<String, String[]> parameterMap = request.getParameterMap();
		log.info("预约页面访问参数 = {}", JSON.toJSON(parameterMap));

		if (MapUtils.isEmpty(parameterMap)) {
			res.sendRedirect(redirectUri);
			return;
		}
		String code = parameterMap.get("code")[0];
		// 如果没有code参数，则说明不是微信返回的请求，转发进行微信授权
		if (StringUtils.isEmpty(code)) {
			res.sendRedirect(redirectUri);
			return;
		}
		// code参数拿到后，进行openid获取
		JSONObject weixinAccess = weixinUtil.getWeixinAccess(code);
		String wxopenid = weixinUtil.getOpenid(weixinAccess);
		ShiroUtils.setOpenid(wxopenid);

		chain.doFilter(request, response);
	}

	/**
	 * 检查uri确认请求是否需要业务处理
	 * 
	 * @param uri
	 * @return
	 */
	private boolean checkUri(String uri) {
		for (String uriStr : uriArr) {
			if (uri.contains(uriStr)) {
				return true;
			}
		}
		return false;
	}
}
