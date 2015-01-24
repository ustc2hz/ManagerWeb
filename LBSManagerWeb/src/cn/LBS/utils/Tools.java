package cn.LBS.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileInputStream;

import cn.LBS.db.DBService;

/**
 * ������
 */
public class Tools {

	public static int seq = 0;

	public static synchronized String getSeqNo() {
		if (seq == 9999) {
			seq = 0;
		}
		return headFillZero(String.valueOf(seq), 4);
	}

	/**
	 * ����MD5����
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// ���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			// �������
			byte[] md = mdInst.digest();
			// ������ת����ʮ�����Ƶ��ַ�����ʽ
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��������ǰ��0
	 * 
	 * @param str
	 *            ������ַ���
	 * @param num
	 *            ����
	 * @return String ǰ��0����ַ���
	 */
	public static String headFillZero(String str, int num) {
		// stringbuffer���0
		StringBuffer buffer = new StringBuffer();
		// �������С��ָ�����ȣ���ǰ��0
		if (str.length() < num) {
			for (int i = 0; i < num - str.length(); i++) {
				buffer.append("0");
			}
		}
		// ���ص��ַ���
		str = buffer.toString() + str;

		return str;
	}

	/**
	 * ��ISO����ת��ΪGBK����
	 * 
	 * @param output
	 *            String �����ַ���
	 * @return String ����ַ���
	 */
	public static String decode(String output) {
		try {
			return new String(output.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * ��GBK����ת��ΪISO����
	 * 
	 * @param input
	 *            String �����ַ���
	 * @return String ����ַ���
	 */
	public static String encode(String input) {
		try {
			return new String(input.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * ��ʽ�����ڣ���ʽ��2006-07-09
	 * 
	 * @param date
	 *            Date ����
	 * @return String ����ַ���
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		} else {
			String disp = "";
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			disp = bartDateFormat.format(date);
			return disp;
		}
	}

	/**
	 * ��ʽ�����ڣ���ʽ��2006-07-09 23:00:00
	 * 
	 * @param date
	 *            Date ����
	 * @return String ����ַ���
	 */
	public static String formatDate2(Date date) {
		if (date == null) {
			return "";
		} else {
			String disp = "";
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			disp = bartDateFormat.format(date);
			return disp;
		}
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyyMMddHHmmssת��Ϊyyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */
	public static String formatDate3(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyyMMddHHmmת��Ϊyyyy-MM-dd HH:mm
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */
	public static String formatDate_3(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyy-MM-ddת��ΪyyyyMMdd
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */
	public static String formatDate4(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyyMMdd");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyyMMddת��Ϊyyyy-MM-dd
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */
	public static String formatDate5(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyy-MM-dd");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyy-MM-dd HH:mm:ssת��ΪyyyyMMddHHmmss
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */

	public static String formatDate6(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyyMMddHHmmss");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyy-MM-dd HH:mmת��ΪyyyyMMddHHmm
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */

	public static String formatDate_6(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyyMMddHHmm");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ������ΪyyyyMMddHHmmss��ʽ
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate7(Date date) {
		if (date == null) {
			return "";
		} else {
			String disp = "";
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyyMMddHHmmss");
			disp = bartDateFormat.format(date);
			return disp;
		}
	}

	/**
	 * ��ʽ�����ڣ������ڴ�yyyyMMddת��Ϊyyyy-MM-dd
	 * 
	 * @param datetime
	 *            String
	 * @return String
	 */
	public static String formatDate8(String datetime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date date1 = df.parse(datetime);
			df = new SimpleDateFormat("yyyy/MM/dd");
			datetime = df.format(date1);
		} catch (Exception ex) {
			datetime = "";
		}
		return datetime;
	}

	/**
	 * ��ʽ��double����Ϊ�ַ�������ʽΪ##################0.00
	 * 
	 * @param b
	 *            double
	 * @return String
	 */
	public static String formatE(double b) {
		try {
			DecimalFormat df1 = new DecimalFormat("##################0.00");
			return df1.format(b);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * ��ʽ��float����Ϊ�ַ�������ʽΪ##################0.00
	 * 
	 * @param b
	 *            double
	 * @return String
	 */
	public static String formatE(float b) {
		try {
			DecimalFormat df1 = new DecimalFormat("##################0.00");
			return df1.format(b);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * ��ʽ��float����Ϊ�ַ�������ʽΪ##################0.00
	 * 
	 * @param b
	 *            double
	 * @return String
	 */
	public static String formatE2(float b) {
		String result = "";
		try {
			DecimalFormat df1 = new DecimalFormat("##################0.000");
			result = df1.format(b);
			String ss[] = result.split("\\.");
			String ss1 = ss[0];
			String ss2 = ss[1];
			String s21 = ss2.substring(0, 2);
			String s22 = ss2.substring(2, 3);
			if (Integer.parseInt(s22) > 4) {
				s21 = (Integer.parseInt(s21) + 1) + "";

				if (Integer.parseInt(s21) == 100) {
					s21 = "00";
					ss1 = (Integer.parseInt(ss1) + 1) + "";
					result = ss1 + "." + s21;
				} else {
					if (Integer.parseInt(s21) < 10) {
						s21 = "0" + s21;
					}
					result = ss1 + "." + s21;
				}
			} else {
				result = ss1 + "." + s21;
			}
			return result;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * ����Ÿ�ʽ��Ϊ�ַ���������λ����ǰ�油0
	 * 
	 * @param sn
	 *            int ���
	 * @param length
	 *            int �ַ���λ��
	 * @return String
	 */
	public static String formatSn(long sn, int length) {
		String str = sn + "";
		String strSn = str;
		for (int i = 0; i < length - str.length(); i++) {
			strSn = "0" + strSn;
		}
		return strSn;
	}

	/**
	 * ���ַ������ݷָ����ֽ���ַ�����
	 * 
	 * @param value
	 *            String �ַ���
	 * @param compart
	 *            String �ָ���
	 * @return Vector
	 */
	public static Vector formatString(String value, String compart) {
		int index = 0;
		int i = 0;
		Vector vc = new Vector();
		while ((index = value.indexOf(compart)) != -1) {
			vc.add(value.substring(0, index));
			i++;
			value = value.substring(index + 1);
		}
		return vc;
	}

	/**
	 * ����ٷֱ�
	 * 
	 * @param str1
	 *            String �ַ���
	 * @param str2
	 *            String �ָ���
	 * @return String
	 */
	public static String formatPercent(String str1, String str2) {
		if (str1.equals("0")) {
			return "0��";
		}
		double t1 = Double.parseDouble(str1);
		double t2 = Double.parseDouble(str2);
		String tt = "0";
		tt = 100 * (t1 / t2) + "0000.00000";
		String[] comm = tt.split("\\.");
		String comm1 = comm[0];
		String comm2 = comm[1].substring(0, 1);
		String comm3 = comm[1].substring(1, 2);
		String comm4 = comm[1].substring(2, 3);
		if (Integer.parseInt(comm4) >= 5) {
			comm3 = (Integer.parseInt(comm3) + 1) + "";
			if (Integer.parseInt(comm3) == 10) {
				comm3 = "0";
				comm2 = (Integer.parseInt(comm2) + 1) + "";
				if (Integer.parseInt(comm2) == 10) {
					comm2 = "0";
					comm1 = (Integer.parseInt(comm1) + 1) + "";
				}
			}
		}
		if (comm3.equals("0")) {
			if (comm2.equals("0")) {
				if (comm1.equals("0")) {
					tt = "0.01��";
				} else {
					tt = comm1 + "��";
				}
			} else {
				tt = comm1 + "." + comm2 + "��";
			}
		} else {
			tt = comm1 + "." + comm2 + comm3 + "��";
		}
		return tt;
	}

	/**
	 * ����ٷֱ�
	 * 
	 * @param str1
	 *            String �����ַ���
	 * @param str21
	 *            String �����ַ���
	 * @param str22
	 *            String �����ַ���
	 * @return String
	 */
	public static String formatPercent(String str1, String str21, String str22) {
		if (str1.equals("0")) {
			return "0��";
		}
		double t1 = Double.parseDouble(str1);
		double t2 = Double.parseDouble(str21) + Double.parseDouble(str22);
		String tt = "0";
		tt = 100 * (t1 / t2) + "0000.00000";
		String[] comm = tt.split("\\.");
		String comm1 = comm[0];
		String comm2 = comm[1].substring(0, 1);
		String comm3 = comm[1].substring(1, 2);
		String comm4 = comm[1].substring(2, 3);
		if (Integer.parseInt(comm4) >= 5) {
			comm3 = (Integer.parseInt(comm3) + 1) + "";
			if (Integer.parseInt(comm3) == 10) {
				comm3 = "0";
				comm2 = (Integer.parseInt(comm2) + 1) + "";
				if (Integer.parseInt(comm2) == 10) {
					comm2 = "0";
					comm1 = (Integer.parseInt(comm1) + 1) + "";
				}
			}
		}

		if (comm3.equals("0")) {
			if (comm2.equals("0")) {
				if (comm1.equals("0")) {
					tt = "0.01��";
				} else {
					tt = comm1 + "��";
				}
			} else {
				tt = comm1 + "." + comm2 + "��";
			}
		} else {
			tt = comm1 + "." + comm2 + comm3 + "��";
		}
		return tt;
	}

	/**
	 * ��ñ���·��
	 * 
	 * @return String
	 */
	public static String getBackup() {
		return File.separator + "app" + File.separator + "iservice_server"
				+ File.separator + "backup";
	}

	/**
	 * ���EXCEL�ļ�����·��
	 * 
	 * @return String
	 */
	public static String getExcelServer() {
		return System.getProperty("catalina.home") + File.separator + "webapps"
				+ File.separator + "iservice_smp_client";
	}

	/**
	 * ��ð�����ʼ����ֹʱ�䣬������е�����
	 * 
	 * @param startTime
	 *            String
	 * @param endTime
	 *            String
	 * @return Vector
	 */
	public static Vector getMonth(String startTime, String endTime) {
		Vector vcMonth = new Vector();
		try {
			SimpleDateFormat startDF = new SimpleDateFormat("yyyyMMddHHmmss");
			startDF.parse(startTime);
			Calendar startCal = startDF.getCalendar();
			SimpleDateFormat endDF = new SimpleDateFormat("yyyyMMddHHmmss");
			endDF.parse(endTime);
			Calendar endCal = endDF.getCalendar();
			vcMonth.add(startTime);
			startCal.set(Calendar.DAY_OF_MONTH, 1);
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startCal.set(Calendar.MINUTE, 0);
			startCal.set(Calendar.SECOND, 0);
			startCal.add(Calendar.MONTH, 1);
			startCal.add(Calendar.SECOND, -1);
			while (endCal.after(startCal)) {
				vcMonth.add(formatDate7(startCal.getTime()));
				vcMonth.add(Integer.toString(startCal.get(2) + 1));
				startCal.add(Calendar.SECOND, 1);
				vcMonth.add(formatDate7(startCal.getTime()));
				startCal.add(Calendar.MONTH, 1);
				startCal.add(Calendar.SECOND, -1);
			}
			vcMonth.add(endTime);
			vcMonth.add(Integer.toString(endCal.get(2) + 1));
		} catch (Exception ex) {
			vcMonth = new Vector();
		}
		return vcMonth;
	}

	/**
	 * ��õ���ʱ�䣬��ʽΪyyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getNow() {
		Date d = new Date();
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = formatter.format(d);
		return str;
	}

	/**
	 * ��õ�ǰʱ��
	 * 
	 * @return String ��õ�ǰʱ����ַ�����������"20030114101211"
	 */
	public static String getNow1() {
		Date d = new Date();
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		str = formatter.format(d);
		return str;
	}

	/**
	 * ��õ������ڣ���ʽΪyyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getNow2() {
		Date d = new Date();
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		str = formatter.format(d);
		return str;
	}

	/**
	 * ��õ���ʱ�䣬��ʽΪyyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getNow3() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = dateFormat.format(new Date());
		return strDate;
	}

	/**
	 * ��õ������ڣ���ʽΪyyyyMMdd
	 * 
	 * @return String
	 */
	public static String getNow4() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String strDate = dateFormat.format(new Date());
		return strDate;
	}

	/**
	 * ��õ������ڣ���ʽΪyyMM
	 * 
	 * @return String
	 */
	public static String getNowMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		String strDate = dateFormat.format(new Date());
		return strDate;
	}

	/**
	 * ���ǰһ���ʱ��
	 * 
	 * @return String
	 */
	public static String getPreviousDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		str = formatter.format(cal.getTime());
		return str;
	}

	/**
	 * ���ǰ���µĵ�ʱ��
	 * 
	 * @return String
	 */
	public static String getPreviousMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		str = formatter.format(cal.getTime());
		return str;
	}

	/**
	 * ��ʽ�����
	 * 
	 * @param acc
	 *            String
	 * @return String
	 */
	public static String formatAcc(String acc) {
		// �����0��ֱ�ӷ���
		if (Float.parseFloat(acc) == 0) {
			return "��Ԫ";
		}

		int length = acc.length();
		String upper = "";
		double fee = Double.parseDouble(acc);
		if (fee % 10000D == 0.0D) {
			for (int i = length - 1; i >= 0; i--) {
				if (i <= 0) {
					char array[] = new char[1];
					array[0] = acc.charAt(i);
					if (Integer.parseInt(new String(array)) != 0) {
						upper = formatNum(Integer.parseInt(new String(array)))
								+ formatIndex(length - i) + upper;
					}
				}
			}
			upper = upper + "Ԫ��";
		} else if (fee % 1000D == 0.0D) {
			for (int i = length - 1; i >= 0; i--) {
				if (i <= 1) {
					char array[] = new char[1];
					array[0] = acc.charAt(i);
					if (Integer.parseInt(new String(array)) != 0) {
						upper = formatNum(Integer.parseInt(new String(array)))
								+ formatIndex(length - i) + upper;
					}
				}
			}
			upper = upper + "Ԫ��";
		} else if (fee % 100D == 0.0D) {
			for (int i = length - 1; i >= 0; i--) {
				if (i <= 2) {
					char array[] = new char[1];
					array[0] = acc.charAt(i);
					if (Integer.parseInt(new String(array)) != 0) {
						upper = formatNum(Integer.parseInt(new String(array)))
								+ formatIndex(length - i) + upper;
					}
				}
			}
			upper = upper + "Ԫ��";
		} else if (fee % 10D == 0.0D) {
			for (int i = length - 1; i >= 0; i--) {
				if (i <= 3) {
					if (i != length - 3) {
						char array[] = new char[1];
						array[0] = acc.charAt(i);
						if (Integer.parseInt(new String(array)) != 0) {
							upper = formatNum(Integer
									.parseInt(new String(array)))
									+ formatIndex(length - i) + upper;
						}
					}
				}
			}
			upper = upper + "Ԫ��";
		} else if (fee % 1.0D == 0.0D) {
			for (int i = length - 1; i >= 0; i--) {
				if (i <= 4) {
					if (i != length - 3) {
						char array[] = new char[1];
						array[0] = acc.charAt(i);
						if (Integer.parseInt(new String(array)) != 0) {
							upper = formatNum(Integer
									.parseInt(new String(array)))
									+ formatIndex(length - i) + upper;
						}
					}
				}
			}
			upper = upper + "��";
		} else {
			for (int i = length - 1; i >= 0; i--) {
				if (i != length - 3) {
					char array[] = new char[1];
					array[0] = acc.charAt(i);
					upper = formatNum(Integer.parseInt(new String(array)))
							+ formatIndex(length - i) + upper;
				}
			}
		}
		return upper;
	}

	/**
	 * ��ʽ�����
	 * 
	 * @param index
	 *            int
	 * @return String
	 */
	private static String formatIndex(int index) {
		switch (index) {
		case 1: // '\001'
			return "��";

		case 2: // '\002'
			return "��";

		case 3: // '\003'
			return "";

		case 4: // '\004'
			return "Ԫ";

		case 5: // '\005'
			return "ʰ";

		case 6: // '\006'
			return "��";

		case 7: // '\007'
			return "Ǫ";

		case 8: // '\b'
			return "��";
		}
		return "";
	}

	/**
	 * ��ʽ�����֣���ô�д
	 * 
	 * @param num
	 *            int ����
	 * @return String
	 */
	private static String formatNum(int num) {
		switch (num) {
		case 0:
			return "��";

		case 1:
			return "Ҽ";

		case 2:
			return "��";

		case 3:
			return "��";

		case 4:
			return "��";

		case 5:
			return "��";

		case 6:
			return "½";

		case 7:
			return "��";

		case 8:
			return "��";

		case 9:
			return "��";
		}
		return "";
	}

	/**
	 * �������ܣ���õ�ǰ����
	 * 
	 * @return ���ص�ǰ���ڵ��ַ���������"120000"
	 */
	public static String getNowTime() {
		Date d = new Date();
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		str = formatter.format(d);
		return str;
	}

	/**
	 * ��õ�ǰ�·�
	 * 
	 * @return String ��ʽ��200705
	 */
	public static String getMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String strDate = dateFormat.format(new Date());
		return strDate;
	}

	/**
	 * ��õ�ǰ�·�
	 * 
	 * @return String ��ʽ��2007-05
	 */
	public static String getMonth2() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String strDate = dateFormat.format(new Date());
		return strDate;
	}

	/**
	 * ��ø��µĵ�һ�����ں����һ������
	 * 
	 * @param year
	 *            int ���
	 * @param month
	 *            int �·�
	 * @return String[]
	 */
	public static String[] getMonth(int year, int month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String[] dates = new String[2];
		Calendar startCal = Calendar.getInstance();
		startCal.set(year, month - 1, 1);
		dates[0] = format.format(startCal.getTime());

		Calendar endCal = Calendar.getInstance();
		endCal.set(year, month, 1);
		endCal.add(Calendar.DATE, -1);
		dates[1] = format.format(endCal.getTime());
		return dates;
	}

	/**
	 * �����ڻ�ȡ���������·�
	 * 
	 * @param date
	 *            ���ڱ�������2003-3-3
	 * @param type
	 *            (1��ʾ��ǰ���ڵ��ַ�����ʽ,��"200612" ,2��ʾ��ǰ���ڵ����ڸ�ʽ,��"2006-12")
	 * @return �·�
	 */
	public static String formatMonth(Date date, int type) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = null;
		if (type == 1) {
			bartDateFormat = new SimpleDateFormat("yyyyMM");
		}
		if (type == 2) {
			bartDateFormat = new SimpleDateFormat("yyyy-MM");
		}
		String disp = bartDateFormat.format(date);
		return disp;
	}

	/**
	 * �����������֮��������·ݣ�
	 * 
	 * @param startdate
	 *            ��ʼ����
	 * @param enddate
	 *            ��ֹ����
	 * @return �����·�֮��������·�
	 */
	public static List getAllMonth(String startdate, String enddate) {
		String startMon = startdate.substring(0, 6);
		String endMon = enddate.substring(0, 6);
		List dateList = new ArrayList();
		if (startMon == null || startMon.length() == 0 || endMon == null
				|| endMon.length() == 0) {
			return dateList;
		}

		try {
			SimpleDateFormat startDF = new SimpleDateFormat("yyyyMM");
			startDF.parse(startMon);
			Calendar startCal = startDF.getCalendar();
			SimpleDateFormat endDF = new SimpleDateFormat("yyyyMM");
			endDF.parse(endMon);
			Calendar endCal = endDF.getCalendar();

			while (startCal.before(endCal)) {
				dateList.add(formatMonth(startCal.getTime(), 1));
				startCal.add(Calendar.MONTH, 1);
			}
			dateList.add(endMon);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateList;
	}

	/**
	 * ���������֮��������·ݣ�
	 * 
	 * @param startMon
	 *            ��ʼ�·�
	 * @param endMon
	 *            ��ֹ�·�
	 * @return �����·�֮��������·�
	 */
	public static List getAllMonths(String startMon, String endMon) {
		List dateList = new ArrayList();
		if (startMon == null || startMon.length() == 0 || endMon == null
				|| endMon.length() == 0) {
			return dateList;
		}

		try {
			SimpleDateFormat startDF = new SimpleDateFormat("yyyyMM");
			startDF.parse(startMon);
			Calendar startCal = startDF.getCalendar();
			SimpleDateFormat endDF = new SimpleDateFormat("yyyyMM");
			endDF.parse(endMon);
			Calendar endCal = endDF.getCalendar();

			while (startCal.before(endCal)) {
				dateList.add(formatMonth(startCal.getTime(), 1));
				startCal.add(Calendar.MONTH, 1);
			}
			dateList.add(endMon);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateList;
	}

	/**
	 * ������ֹ���ڻ�ø�����Ҫͳ�ƵļƷ��¶�
	 * 
	 * @param startdate
	 *            ��ʼ����
	 * @param enddate
	 *            ��ֹ����
	 * @return �·��б�
	 */
	public static List getMonths(String startdate, String enddate) {
		List monthList = new ArrayList();
		try {
			startdate = formatDate4(startdate);
			enddate = formatDate4(enddate);

			SimpleDateFormat startDF = new SimpleDateFormat("yyyyMMdd");
			startDF.parse(startdate);
			Calendar startCal = startDF.getCalendar();

			SimpleDateFormat endDF = new SimpleDateFormat("yyyyMMdd");
			endDF.parse(enddate);
			Calendar endCal = endDF.getCalendar();

			// �ж���ʼ���ڵ����������¶�
			startCal.set(Calendar.DAY_OF_MONTH, 1);
			startCal.add(Calendar.MONTH, 1);

			// �������еĲ����¶�
			while (endCal.after(startCal)) {
				monthList
						.add(Integer.toString(startCal.get(Calendar.MONTH) + 1));
				startCal.add(Calendar.MONTH, 1);
			}
			monthList.add(Integer.toString(endCal.get(Calendar.MONTH) + 1));
		} catch (Exception ex) {
			monthList = null;
		}
		return monthList;
	}

	/**
	 * ��ô�2007������ǰ���ǰ���N�����ݲ������ƣ�����������
	 * 
	 * @param num
	 *            ���뵱���һ����������1��ʾ��������꣬-2��ʾǰ�ꡣ
	 * @return �������
	 */
	private static String[] getToCurYearsWithNum(int num) {
		int startYear = 2007;
		Calendar c = Calendar.getInstance();
		int curYear = c.get(Calendar.YEAR) + num;
		String[] years = new String[curYear - startYear + 1];
		for (int i = startYear, j = 0; i <= curYear; i++, j++) {
			years[j] = "" + i;
		}
		return years;
	}

	/**
	 * ��ô�2007����ǰ����������
	 * 
	 * @return String[] 2007����ǰ����������
	 */
	public static String[] getToCurYears() {
		return getToCurYearsWithNum(0);
	}

	/**
	 * ������е���λ���������ֶ�Ӧ��12���·ݡ�
	 * 
	 * @return �����·ݣ����磺String[]{ { "01", "1" }, { "02", "2" }, ... { "12", "12"
	 *         } }
	 */
	public static String[][] getMonths() {
		String[][] months = new String[12][2];

		for (int i = 1, j = 0; i <= 12; i++, j++) {
			months[j][0] = i < 10 ? "0" + i : "" + i;
			months[j][1] = "" + i;
		}

		return months;
	}

	/**
	 * ����ϸ��Ʒ��¶�
	 * 
	 * @return �ϸ��Ʒ��¶�
	 */
	public static String getPreFeeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, -1);
		return formatMonth(cal.getTime(), 1);
	}

	/**
	 * �����������»�ȡ����һ���·�(YYYYMM)
	 * 
	 * @param yearMonth
	 *            ����
	 * @return ����(YYYYMM)
	 */
	public static String getPreMonth(String yearMonth) {
		int year = Integer.parseInt(yearMonth.substring(0, 4));
		int month = Integer.parseInt(yearMonth.substring(4, 6));
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1);
		c.add(Calendar.DATE, -1);
		String mon;
		if (c.get(Calendar.MONTH) + 1 < 10) {
			mon = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		} else
			mon = String.valueOf(c.get(Calendar.MONTH) + 1);

		return String.valueOf(c.get(Calendar.YEAR)) + mon;

	}

	/**
	 * ������µ����һ��͸������һ������
	 * 
	 * @param year
	 *            int ���
	 * @param month
	 *            int �·�
	 * @return String[]
	 */
	public static String[] getMonth2(int year, int month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String[] dates = new String[2];
		Calendar startCal = Calendar.getInstance();
		startCal.set(year, month - 1, 1);
		startCal.add(Calendar.DATE, -1);
		dates[0] = format.format(startCal.getTime());

		Calendar endCal = Calendar.getInstance();
		endCal.set(year, month, 1);
		endCal.add(Calendar.DATE, -1);
		dates[1] = format.format(endCal.getTime());
		return dates;
	}

	/**
	 * ���ǰһ�������
	 * 
	 * @return ����ǰһ�����ڵ��ַ�����������"20030711"
	 */
	public static String getlastDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		str = formatter.format(cal.getTime());
		return str;
	}

	/**
	 * ���ϵͳǰһ����ݵ���ʼ���ں���ֹ����
	 * 
	 * @return �����һ����������ʼ���ڣ��ڶ�������ֹ����
	 */
	public static String[] getWeek() {
		String[] dates = new String[2];
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day == 1) {
			cal.add(Calendar.DATE, -7);
		} else {
			cal.add(Calendar.DATE, -(day - 1));
		}
		dates[1] = formatDate(cal.getTime());
		cal.add(Calendar.DATE, -6);
		dates[0] = formatDate(cal.getTime());
		return dates;
	}

	/**
	 * ���ڼӼ�
	 * 
	 * @param offset
	 *            ���ڼ������
	 * @return ���ڣ�yyyy-MM-dd��
	 */
	public static String changeDay(int offset) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String day = formatter.format(calendar.getTime());
		return day;
	}

	/**
	 * �����ַ�����MACУ����
	 * 
	 * @param src
	 *            �ַ���
	 * @return MACУ����
	 */
	public static String getMac(String src) {
		byte[] data = src.getBytes();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ByteArrayInputStream input;
		byte[] mac = new byte[8];
		try {
			bout.write(data);

			int cover = data.length % 8;

			if (cover != 0) {// �����Ϊ8�ı�������λ
				for (int i = 0; i < (8 - cover); i++) {
					bout.write(0xff);
				}
			}
			data = bout.toByteArray();
			input = new ByteArrayInputStream(data);
			bout = new ByteArrayOutputStream();
			int ennumber = data.length / 8;
			byte[] temp;
			for (int i = 0; i < ennumber; i++) {
				temp = new byte[8];
				input.read(temp);
				for (int j = 0; j < 8; j++) {
					mac[j] = (byte) (mac[j] ^ temp[j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(mac);
	}

	/**
	 * ����ַ�����MACУ�����ASCII��
	 * 
	 * @param src
	 *            �ַ���
	 * @return ASCII��
	 */
	public static String getMacAscii(String src) {
		String mac = getMac(src);
		StringBuffer ascii = new StringBuffer();
		for (int i = 0, ii = mac.length(); i < ii; i++) {
			ascii.append((int) mac.charAt(i));
		}
		return ascii.toString();
	}

	/**
	 * �ظ��ַ����涨�Ĵ���
	 * 
	 * @param s
	 *            Ҫ�ظ����ַ���
	 * @param num
	 *            �ظ��Ĵ������������0��Ϊ0ʱ����""��
	 * @return �����ظ�����ַ���
	 */
	public static String repeat(String s, int num) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < num; i++) {
			str.append(s);
		}
		return str.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String time = Tools.formatDate3("20081230165823");
		//
		// System.out.println(time);
		// System.out.println(time.substring(0, 10));
		// System.out.println(time.substring(11, time.length()));
		// List monthList = getMonths("2008-01-01","2008-07-04");
		List monthList = getAllMonth("2008-01-01", "2008-07-04");
		for (int i = 0; i < monthList.size(); i++) {
			String str = (String) monthList.get(i);
			System.out.println(str);
		}

	}

	/**
	 * md5����(ITS)
	 * 
	 * @param str
	 * @return
	 */
	public synchronized static final String getMD5Str(String str) { // md5����
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();

			messageDigest.update(str.getBytes());

		} catch (NoSuchAlgorithmException e) {

		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

}
