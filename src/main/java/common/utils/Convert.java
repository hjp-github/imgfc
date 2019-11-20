package common.utils;

import java.util.UUID;

/**
 * 工具类
 * 
 * @author siit
 *
 */
public class Convert {

	// private static final Logger logger = LoggerFactory.getLogger(Convert.class);

	/**
	 * 获取UUID
	 */
	public static String getUUID() {
		// 获取随机唯一标识符
		String uuid = UUID.randomUUID().toString();
		// 去掉标识符中的"-"
		uuid = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
		return uuid;
	}

	/**
	 * 判断空
	 * 
	 * @param src
	 * @return
	 */
	public static Boolean IsNullOrEmpty(Object src) {
		Boolean b = false;
		if (src == null) {
			b = true;
		} else {
			if (src.toString().trim().equals("") || src.toString().trim().equals("null")) { //$NON-NLS-1$ //$NON-NLS-2$
				b = true;
			}
		}
		return b;
	}

}
