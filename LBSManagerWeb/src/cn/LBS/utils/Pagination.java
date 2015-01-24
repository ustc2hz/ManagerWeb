package cn.LBS.utils;

import cn.LBS.exception.PaginationException;

/**
 * ��ҳ��Ϣ��
 * 
 * @author Master
 * 
 */
public class Pagination {

	/**
	 * ��ҳ�Ƿ����
	 */
	private boolean finished = false;

	/**
	 * Ĭ��ҳ����ʾ������
	 */
	private final int DEFAULT_PAGE_ROWS = 20;

	/**
	 * ������
	 */
	private int rowSize = 0;

	/**
	 * ÿҳ��ʾ������
	 */
	private int pageRows = DEFAULT_PAGE_ROWS;

	/**
	 * ��ǰҳ
	 */
	private int pageIndex = 1;

	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Pagination() {

	}

	/**
	 * �õ�ǰҳ����ʼ����Ĺ��췽��
	 * 
	 * @param pageIndex
	 *            ��ǰҳ��
	 * @throws PaginationException
	 */
	public Pagination(int pageIndex) throws PaginationException {
		setPageIndex(pageIndex);
	}

	/**
	 * �õ���ǰҳ��
	 * 
	 * @return ��ǰҳ��
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * ���õ�ǰҳ����������õĵ�ǰҳ��С��1����Ĭ��Ϊ1��
	 * 
	 * @param pageIndex
	 *            ���õ�ǰҳ��
	 * @throws PaginationException
	 */
	public void setPageIndex(int pageIndex) throws PaginationException {
		this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
	}

	/**
	 * ���ÿҳ��ʾ����
	 * 
	 * @return ÿҳ��ʾ����
	 */
	public int getPageRows() {
		return pageRows;
	}

	/**
	 * ����ÿҳ��ʾ����
	 * 
	 * @param pageRows
	 *            ÿҳ��ʾ����
	 * @throws PaginationException
	 */
	public void setPageRows(int pageRows) throws PaginationException {
		if (pageRows < 1) {
			throw new PaginationException("���õ�ÿҳ��ʾ��������С��1��");
		}

		this.pageRows = pageRows;
	}

	/**
	 * ���������
	 * 
	 * @return ������
	 */
	public int getRowSize() {
		return rowSize;
	}

	/**
	 * ����������
	 * 
	 * @param rowSize
	 *            ������
	 * @throws PaginationException
	 */
	public void setRowSize(int rowSize) throws PaginationException {
		if (rowSize < 0) {
			throw new PaginationException("���õķ�ҳ����������С��0��");
		}

		this.rowSize = rowSize;
		this.finished = true;
	}

	/**
	 * �����ҳ��
	 * 
	 * @return ��ҳ��
	 */
	public int getPageSize() {
		return rowSize / pageRows + (rowSize % pageRows == 0 ? 0 : 1);
	}

	/**
	 * ��õ�ǰҳ����ʼ�����������е���ʼλ�ã������ʼ�д�������������Ϊ���һ��
	 * 
	 * @return ��ǰҳ����ʼ�����������е���ʼλ��
	 */
	public int getStartRow() {
		int startRow = (pageIndex - 1) * pageRows + 1;
		return startRow > this.rowSize ? this.rowSize : startRow;
	}

	/**
	 * ��õ�ǰҳ����������������еĽ���λ�ã���������д�������������Ϊ���һ��
	 * 
	 * @return ��ǰҳ����������������еĽ���λ��
	 */
	public int getEndRow() {
		int endRow = this.getStartRow() + this.pageRows - 1;
		return endRow > this.rowSize ? this.rowSize : endRow;
	}

	/**
	 * ��õ�ǰҳ�����������е�ʵ������
	 * 
	 * @return ��ǰҳ�����������е�ʵ������
	 */
	public int getFactPageRows() {
		return this.getEndRow() - this.getStartRow() + 1;
	}

	/**
	 * ��ҳ�Ƿ���ɡ�
	 * 
	 * @return ��������<code>setRowSize(int)</code>����֮�󣬱�����ҳ��ɡ�
	 */
	public boolean finished() {
		return finished;
	}
}
