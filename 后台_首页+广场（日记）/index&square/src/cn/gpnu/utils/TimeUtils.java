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

	// ��ȡ��ǰʱ�䲢תΪString
	public static String getTime() {
		// ��ȡϵͳʱ��
		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(currDate);
		return time;
	}

	// ��ȡ��ǰʱ�䲢����int��
	public static int getTimeByInt() {
		Integer date = Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10));
		return date;
	}

	// ��ʱ��β�
	public static Long betweenTime(String start, String end) { // ����ʱ��=��ʼʱ��

		Date date1 = null, date2 = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			date1 = df.parse(start);
			date2 = df.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long l = date2.getTime() - date1.getTime();
		// �������ĺ�������
		return l;
	}

	// ��ʽ������ʱ��,������СʱΪ��λ��ʱ��
	public static BigDecimal fomatTime(Long l) {
		long day1 = l / (24 * 60 * 60 * 1000);
		long hour1 = (l / (60 * 60 * 1000) - day1 * 24);
		long min1 = ((l / (60 * 1000)) - day1 * 24 * 60 - hour1 * 60);
		long s1 = (l / 1000 - day1 * 24 * 60 * 60 - hour1 * 60 * 60 - min1 * 60);
		System.out.println(((double) min1) / 60);
		double time = ((double) day1) * 24 + (double) hour1 + ((double) min1) / 60 + ((double) s1) / 3600; // תΪdouble
		System.out.println(day1 + "��" + hour1 + "Сʱ" + min1 + "��" + s1 + "��");

		// double����ת���� bigDecima
		DecimalFormat df = new DecimalFormat("#0.00");
		BigDecimal a = new BigDecimal(df.format(time));

		// ����Сʱ��
		return a;

	}

}
