package cn.gpnu.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class TimeUtils {

	// 获取当前时间并转为String
	public static String getTime() {
		// 获取系统时间
		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(currDate);
		return time;
	}

	// 获取当前时间并返回int型
	public static int getTimeByInt() {
		Integer date = Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10));
		return date;
	}

	// 两时间段差
	public static Long betweenTime(String start, String end) { // 结束时间=开始时间

		Date date1 = null, date2 = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			date1 = df.parse(start);
			date2 = df.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long l = date2.getTime() - date1.getTime();
		// 返回相差的毫秒秒数
		return l;
	}

	// 格式化毫秒时间,返回以小时为单位的时间
	public static BigDecimal fomatTime(Long l) {
		long day1 = l / (24 * 60 * 60 * 1000);
		long hour1 = (l / (60 * 60 * 1000) - day1 * 24);
		long min1 = ((l / (60 * 1000)) - day1 * 24 * 60 - hour1 * 60);
		long s1 = (l / 1000 - day1 * 24 * 60 * 60 - hour1 * 60 * 60 - min1 * 60);
		System.out.println(((double) min1) / 60);
		double time = ((double) day1) * 24 + (double) hour1 + ((double) min1) / 60 + ((double) s1) / 3600; // 转为double
		System.out.println(day1 + "天" + hour1 + "小时" + min1 + "分" + s1 + "秒");

		// double类型转换成 bigDecima
		DecimalFormat df = new DecimalFormat("#0.00");
		BigDecimal a = new BigDecimal(df.format(time));

		// 返回小时数
		return a;

	}

}
