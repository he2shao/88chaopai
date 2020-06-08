package com.ruoyi.project.common.weixin;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.config.service.IConfigService;

@RestController
@RequestMapping("/wx/oauth")
public class WxRestAuthController {

	@Autowired
	private IConfigService configService;
	@Autowired
	WebApplicationContext webApplicationContext;

	/**
	 * 初始化微信扫码器配置参数
	 * 
	 * @return
	 */
	@GetMapping(value = "/getWx")
	public AjaxResult getWx() {
		ServletContext servletContext = webApplicationContext.getServletContext();
		String timestamp = (String) servletContext.getAttribute("timestamp");
		String nonceStr = (String) servletContext.getAttribute("nonceStr");
		String signature = (String) servletContext.getAttribute("signature");
		Wx wx = new Wx(configService.selectConfigByKey("weixin.appId"), timestamp, nonceStr, signature);
		return AjaxResult.success(wx);
	}

}