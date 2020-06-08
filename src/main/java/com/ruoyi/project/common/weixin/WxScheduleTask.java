package com.ruoyi.project.common.weixin;

import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.system.config.service.IConfigService;

@Component
@Configuration
@EnableScheduling
public class WxScheduleTask {

	private static final Logger log = LoggerFactory.getLogger(WxScheduleTask.class);

	@Value("${weixin.api.url}")
	private String weixinApiUrl;
	@Autowired
	private IConfigService configService;
	@Autowired
	WebApplicationContext webApplicationContext;

	// 添加定时任务 每小时刷新获得的微信签名
	@Scheduled(fixedRate = 3600000)
	// @Scheduled(fixedRate = 60000)
	private void configureTasks() {
		log.debug("定时任务开始执行");
		CreateSignature();
		log.debug("定时任务执行结束");
	}

	/**
	 * 获取访问token
	 *
	 * @return
	 */
	private String getAccessToken() {
		String appId = configService.selectConfigByKey("weixin.appId");
		String appSecret = configService.selectConfigByKey("weixin.appSecret");
		String url = weixinApiUrl + "/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret="
				+ appSecret;
		JSONObject jsonObject = (JSONObject) JSON.parse(RestTemplateUtils.get(url, String.class));
		log.info("获取访问token=====" + jsonObject.toJSONString());
		return jsonObject.getString("access_token");
	}

	/**
	 * 获取ticket
	 *
	 * @return
	 */
	private String getTicket() {
		String accessToken = getAccessToken();
		String url = weixinApiUrl + "/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
		JSONObject jsonObject = (JSONObject) JSON.parse(RestTemplateUtils.get(url, String.class));
		System.out.println("获取ticket----------------" + jsonObject);
		return jsonObject.getString("ticket");
	}

	/**
	 * 创建签名
	 */
	private void CreateSignature() {
		String nonceStr = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String ticket = getTicket();
		String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ configService.selectConfigByKey("weixin.check.url");

		String signature = DigestUtils.SHA1(str);
		ServletContext servletContext = webApplicationContext.getServletContext();
		servletContext.setAttribute("nonceStr", nonceStr);
		servletContext.setAttribute("timestamp", timestamp);
		servletContext.setAttribute("signature", signature);
	}
}
