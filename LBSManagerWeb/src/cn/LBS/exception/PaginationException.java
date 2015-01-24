package cn.LBS.exception;

/**
 * 分页异常类
 */
public class PaginationException extends RuntimeException {
	/**
	 * 构造方法
	 * 
	 * @param message
	 * 错误提示信息
	 */
	public PaginationException(String message) {
		super(message);
	}
}
