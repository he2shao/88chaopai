package com.ruoyi.common.utils.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.shiro.realm.UserRealm;
import com.ruoyi.project.system.user.domain.User;

/**
 * shiro 工具类
 * 
 * @author ruoyi
 */
public class ShiroUtils {
	private static final Logger log = LoggerFactory.getLogger(ShiroUtils.class);

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void logout() {
		getSubject().logout();
	}

	public static User getSysUser() {
		User user = null;
		Object obj = getSubject().getPrincipal();
		if (StringUtils.isNotNull(obj)) {
			user = new User();
			BeanUtils.copyBeanProp(user, obj);
		}
		return user;
	}

	public static void setSysUser(User user) {
		Subject subject = getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);
	}

	public static void clearCachedAuthorizationInfo() {
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
		realm.clearCachedAuthorizationInfo();
	}

	public static Long getUserId() {
		return getSysUser().getUserId().longValue();
	}

	public static String getLoginName() {
		return getSysUser().getLoginName();
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

	public static String getSessionId() {
		return String.valueOf(getSubject().getSession().getId());
	}

	public static void setOpenid(String openid) {
		if (StringUtils.isEmpty(openid) || "null".equals(openid)) {
			log.info("当前openid为空，不能存入session缓存");
			return;
		}
		// sessionId作为key值有点地方可能也会这么用，避免冲突需要进行key值进行拼串处理
		getSession().setAttribute(getSessionId() + "_openid", openid);
	}

	public static String getOpenid() {
		// sessionId作为key值有点地方可能也会这么用，避免冲突需要进行key值进行拼串处理
		return (String) getSession().getAttribute(getSessionId() + "_openid");
	}
}
