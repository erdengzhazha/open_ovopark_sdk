package com.ovopark.sdk.openplatform.kit;


/**
 * 
    * @ClassName: StrKit
    * @Description: TODO(字符串工具类)
    * @author Remiel_Mercy xuefei_fly@126.com
    * @date 2017年8月3日 上午11:06:57
 */
public class StrKit {
	public static final char C_SPACE = ' ';
	public static final char C_TAB = '	';
	public static final char C_DOT = '.';
	public static final char C_SLASH = '/';
	public static final char C_BACKSLASH = '\\';
	public static final char C_CR = '\r';
	public static final char C_LF = '\n';
	public static final char C_UNDERLINE = '_';
	public static final char C_COMMA = ',';
	public static final char C_DELIM_START = '{';
	public static final char C_DELIM_END = '}';
	public static final String C_ASK = "?";
	
	public static final String SPACE = " ";
	public static final String TAB = "	";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String BACKSLASH = "\\";
	public static final String EMPTY = "";
	public static final String CR = "\r";
	public static final String LF = "\n";
	public static final String CRLF = "\r\n";
	public static final String UNDERLINE = "_";
	public static final String COMMA = ",";

	public static final String HTML_NBSP = "&nbsp;";
	public static final String HTML_AMP = "&amp";
	public static final String HTML_QUOTE = "&quot;";
	public static final String HTML_LT = "&lt;";
	public static final String HTML_GT = "&gt;";

	public static final String EMPTY_JSON = "{}";
	public static final int INDEX_NOT_FOUND = -1;

	/**

	 * 首字母变小写

	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**

	 * 首字母变大写

	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**

	 * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true

	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		}
		int len = str.length();
		if (len == 0) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case ' ':
			case '\t':
			case '\n':
			case '\r':
			// case '\b':

			// case '\f':

				break;
			default:
				return false;
			}
		}
		return true;
	}
	
	public static boolean notBlank(String str) {
		return !isBlank(str);
	}
	
	public static boolean notBlank(String... strings) {
		if (strings == null || strings.length == 0) {
			return false;
		}
		for (String str : strings) {
			if (isBlank(str)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean notNull(Object... paras) {
		if (paras == null) {
			return false;
		}
		for (Object obj : paras) {
			if (obj == null) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 字符串是否为空，空的定义如下 1、为null <br>
	 * 2、为""<br>
	 * 
	 * @param str 被检测的字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 字符串是否为非空白 空白的定义如下： <br>
	 * 1、不为null <br>
	 * 2、不为""<br>
	 * 
	 * @param str 被检测的字符串
	 * @return 是否为非空
	 */
	public static boolean isNotEmpty(String str) {
		return false == isEmpty(str);
	}
	/**
	    * @Title: subBefore
	    * @Description: TODO(	
	    * 截取分隔字符串之前的字符串，不包括分隔字符串<br>
	    * 如果给定的字符串为空串（null或""）或者分隔字符串为null，返回原字符串<br>
	    * 如果分隔字符串为空串""，则返回空串，如果分隔字符串未找到，返回原字符串
	    * @param @param str
	    * @param @param separator
	    * @param @param isLastSeparator
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public static String subBefore(String str, String separator,boolean isLastSeparator) {
		if (isEmpty(str) || separator == null) {
			return null == str ? null : str.toString();
		}
		if (separator.isEmpty()) {
			return EMPTY;
		}
		int pos = isLastSeparator ? str.lastIndexOf(separator) : str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return str;
		}
		return str.substring(0, pos);
	}
	/**
	    * @Title: subBefore
	    * @Description: TODO(	
	    * 截取分隔字符串之后的字符串，不包括分隔字符串<br>
	 	* 如果给定的字符串为空串（null或""），返回原字符串<br>
	 	* 如果分隔字符串为空串（null或""），则返回空串，如果分隔字符串未找到，返回空串
	    * @param @param str	被查找的字符串
	    * @param @param separator	分隔字符串
	    * @param @param isLastSeparator	是否查找最后一个分隔字符串（多次出现分隔字符串时选取最后一个），true为选取最后一个
	    * @param @return    切割后的字符串
	    * @return String    返回类型
	    * @throws
	 */
	public static String subAfter(String str, String separator, boolean isLastSeparator) {
		if (isEmpty(str)) {
			return null == str ? null : str.toString();
		}
		if (separator == null) {
			return EMPTY;
		}
		final int pos = isLastSeparator ? str.lastIndexOf(separator) : str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

}
