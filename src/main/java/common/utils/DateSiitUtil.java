package common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSiitUtil {

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return ""; //$NON-NLS-1$
		}
		DateFormat format = getDateFormat("yyyy-MM-dd HH:mm:ss"); //$NON-NLS-1$
		return format.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Timestamp date) {
		if (date == null) {
			return ""; //$NON-NLS-1$
		}
		DateFormat format = getDateFormat("yyyy-MM-dd HH:mm:ss"); //$NON-NLS-1$
		return format.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String fm) {
		if (date == null) {
			return ""; //$NON-NLS-1$
		}
		DateFormat format = new SimpleDateFormat(fm);
		return format.format(date);
	}

	/**
	 * 返回日期格式工具
	 * 
	 * @param fms
	 * @return
	 */
	public static DateFormat getDateFormat(String fms) {
		return new SimpleDateFormat(fms);
	}
}
