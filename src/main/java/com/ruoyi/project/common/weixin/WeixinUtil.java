package com.ruoyi.project.common.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.system.config.service.IConfigService;

@Component
public class WeixinUtil {
	private static final Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	@Value("${weixin.api.url}")
	private String weixinApiUrl;
	@Value("${weixin.open.url}")
	private String weixinOpenUrl;

	@Autowired
	private IConfigService configService;

	/**
	 * 微信网页授权 》第一步：组装用户静默同意授权链接 https://open.weixin.qq.com/connect/oauth2/authorize?
	 * appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php
	 * &response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getWeixinRedirectUrl(String uri) throws UnsupportedEncodingException {
		String redirectUri = configService.selectConfigByKey("weixin.add.url");
		if (uri.contains("myorder")) {
			redirectUri = configService.selectConfigByKey("weixin.myorder.url");
		}
		String appId = configService.selectConfigByKey("weixin.appId");
		String scope = configService.selectConfigByKey("weixin.scope");
		String url = weixinOpenUrl + "/connect/oauth2/authorize?appid=" + appId + "&redirect_uri="
				+ URLEncoder.encode(redirectUri, "utf8") + "&response_type=code&scope=" + scope
				+ "&state=STATE#wechat_redirect";
		log.info("微信网页授权 》第一步： url = {}", url);
		return url;
	}

	/**
	 * 微信网页授权 》第二步：通过code换取网页授权access_token
	 * 
	 * 官方接口示例 https://api.weixin.qq.com/sns/oauth2/access_token?
	 * appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	 * 
	 * @param code
	 * @return 官方返回参数示例 { "access_token":"ACCESS_TOKEN", "expires_in":7200,
	 *         "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
	 */
	public JSONObject getWeixinAccess(String code) {
		String appId = configService.selectConfigByKey("weixin.appId");
		String appSecret = configService.selectConfigByKey("weixin.appSecret");

		String url = weixinApiUrl + "/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code
				+ "&grant_type=authorization_code";
		log.info("微信网页授权 》第二步： url = {}", url);
		String result = RestTemplateUtils.get(url, String.class);
		log.info("微信网页授权 》第二步：通过code换取网页授权access_token = {}", result);
		return JSONObject.parseObject(result, JSONObject.class);
	}

	public String getAccessToken(JSONObject jsonObject) {
		return jsonObject.getString("access_token");
	}

	public String getOpenid(JSONObject jsonObject) {
		return jsonObject.getString("openid");
	}

	public String getWeixinApiUrl() {
		return weixinApiUrl;
	}

	public String getWeixinOpenUrl() {
		return weixinOpenUrl;
	}
}
