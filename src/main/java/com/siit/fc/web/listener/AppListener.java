package com.siit.fc.web.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import common.utils.Convert;

/**
 * 应用启动监听
 * 
 * @author siit
 *
 */
@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(AppListener.class);

	/**
	 * 当前节点唯一标识
	 */
	private static String appSign;
	/**
	 * libreoffice 安装目录
	 */
	private static String libreofficeInstallPath;
	/**
	 * 任务一次抓取行数
	 */
	private static String lockRownum;

	public static String getAppSign() {
		return appSign;
	}

	public void setAppSign(String appSign) {
		AppListener.appSign = appSign;
	}

	public static String getLibreofficeInstallPath() {
		return libreofficeInstallPath;
	}

	public static void setLibreofficeInstallPath(String libreofficeInstallPath) {
		AppListener.libreofficeInstallPath = libreofficeInstallPath;
	}

	public static String getLockRownum() {
		return lockRownum;
	}

	public static void setLockRownum(String lockRownum) {
		AppListener.lockRownum = lockRownum;
	}

	/**
	 * 项目启动运行
	 */
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (!Convert.IsNullOrEmpty(appSign)) {
			System.out.println("------监听器再次进入------");
			return;
		}
		// 获取项目启动标识
		appSign = Convert.getUUID();
		logger.info("------项目启动标识[" + appSign + "]------");

		// 加载app.properties
		loadAppConfig();
	}

	/**
	 * 加载app.properties
	 */
	public void loadAppConfig() {
		try {

			InputStream inputStream = this.getClass().getResourceAsStream("/properties/app.properties");

			Properties prop = new Properties();
			prop.load(inputStream);

			setLibreofficeInstallPath(prop.getProperty("LIBREOFFICE_INSTALL_PATH"));
			logger.info("------启动加载[LIBREOFFICE_INSTALL_PATH][" + getLibreofficeInstallPath() + "]------");

			setLockRownum(prop.getProperty("LOCK_ROWNUM"));
			logger.info("------启动加载[LOCK_ROWNUM][" + getLockRownum() + "]------");

			logger.info("------项目启动配置加载完成------");
		} catch (IOException e) {
			logger.error("------项目启动配置加载异常------", e);
		}
	}

}
