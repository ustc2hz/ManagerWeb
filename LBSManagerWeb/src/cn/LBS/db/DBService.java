package cn.LBS.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.LBS.Constant.Constants;
import cn.LBS.model.Order;
import cn.LBS.utils.Pagination;

/**
 * ���ݿ�����ķ�װ
 */
public class DBService {
	/**
	 * ��ѯ����������һ�еı�־
	 */
	private static final int END_COL = -1;

	/**
	 * ���ݿ�����
	 */
	private Connection conn;

	/**
	 * ���ݿ��ҳ��Ϣ
	 */
	private Pagination pagination;

	/**
	 * ����һ���Զ��ύ�����SMP���ݿ������װ�ࡣ
	 * 
	 * @throws SQLException
	 */
	public DBService() throws SQLException {
		this(Constants.DBNAME_LBS);
	}

	/**
	 * �������ݿ�������һ���Զ��ύ��������ݿ������װ�ࡣ
	 * 
	 * @param dbName
	 *            ���ݿ����ƣ�Constants.DBNAME_SMP or Constants.DBNAME_SCP��
	 * @throws SQLException
	 */
	public DBService(String dbName) throws SQLException {
		try {
			this.conn = new DatabaseConnection().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����SQL��ѯ���ݿ⣬���һ���������
	 * 
	 * @param sql
	 *            SQL��ѯ���
	 * @return rs
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	public List<Order> getListString(String sql) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		Order order = new Order();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			int columnCount = rs.getMetaData().getColumnCount();
			List list = new ArrayList();
			System.out.println("������" + columnCount);
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					order.setAdmin_name(rs.getString(i));
					order.setDriver_id(rs.getString(++i));
					order.setOrder_date(rs.getDate(++i));
					order.setOrder_price(rs.getDouble(++i));
					order.setOrder_status(rs.getString(++i));
					i++;
					list.add(order);
				}
			}
			// System.out.println("abcdefghi");
			return list;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}

	}

	/**
	 * ����SQL��ѯ���ݿ⣬���һ��StringԪ�ص��б�
	 * 
	 * @param sql
	 *            SQL��ѯ���
	 * @return List(String)
	 * @throws SQLException
	 */
	public List getStringList(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			int columnCount = rs.getMetaData().getColumnCount();
			List list = new ArrayList();
			System.out.println("������" + columnCount);
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					list.add(rs.getString(i));

				}
			}
			// System.out.println("abcdefghi");
			return list;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿ⣬���Ԫ��ΪString���б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return List(String)Ԫ��ΪString���б�
	 * @throws SQLException
	 */
	public List getStringList(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}

			rs = stmt.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			List list = new ArrayList();

			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					list.add(formatNull(rs.getString(i)));
				}
			}
			return list;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ���ݿ⣬���ӽ��������ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 * 
	 * @param sql
	 *            SQL��ѯ���
	 * @return List(String[])Ԫ��ΪString[]���б�
	 * @throws SQLException
	 */
	public List getList(String sql) throws SQLException {
		return getList(sql, 0, END_COL);
	}

	/**
	 * ����SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 * 
	 * @param sql
	 *            SQL��ѯ���
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ��������ʼ�У���
	 * @return List(String[])Ԫ��ΪString[]���б�
	 * @throws SQLException
	 */
	public List getList(String sql, int startCol) throws SQLException {
		return getList(sql, startCol, END_COL);
	}

	/**
	 * ����SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ����ָ����ֹ�л��Ԫ��ΪString[]���б�
	 * 
	 * @param sql
	 *            SQL��ѯ���
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ��������ʼ�У���
	 * @param endCol
	 *            ��ȡ���������ֹ�У���������ֹ�У������Ϊ<code>END_COL</code>�����ô���ʼ�п�ʼ�������е��С�
	 * @return List(String[])Ԫ��ΪString[]���б�
	 * @throws SQLException
	 */
	public List getList(String sql, int startCol, int endCol)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (endCol == END_COL) {
				endCol = rs.getMetaData().getColumnCount();
			}

			int len = endCol - startCol;

			List list = new ArrayList();
			while (rs.next()) {
				String[] info = new String[len];
				for (int i = 0, j = startCol; j < endCol; i++, j++) {
					info[i] = formatNull(rs.getString(j + 1));

				}
				list.add(info);
			}
			return list;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿ⣬���ӽ��������ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return List(String[])Ԫ��ΪString������б�
	 * @throws SQLException
	 */
	public List getList(String sql, Object[] params) throws SQLException {
		return getList(sql, params, 0, END_COL);
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ��������ʼ�У���
	 * @return List(String[])Ԫ��ΪString������б�
	 * @throws SQLException
	 */
	public List getList(String sql, Object[] params, int startCol)
			throws SQLException {
		return getList(sql, params, startCol, END_COL);
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿ⣬���ӽ��������ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ��������ʼ�У���
	 * @param endCol
	 *            ��ȡ���������ֹ�У���������ֹ�У������Ϊ<code>END_COL</code>�����ô���ʼ�п�ʼ�������е��С�
	 * @return List(String[])Ԫ��ΪString������б�
	 * @throws SQLException
	 */
	public List getList(String sql, Object[] params, int startCol, int endCol)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}

			rs = stmt.executeQuery();

			if (endCol == END_COL) {
				endCol = rs.getMetaData().getColumnCount();
			}

			int len = endCol - startCol;

			List list = new ArrayList();
			while (rs.next()) {
				String[] info = new String[len];
				for (int i = 0, j = startCol; j < endCol; i++, j++) {
					info[i] = formatNull(rs.getString(j + 1));
				}
				list.add(info);
			}
			return list;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���ݷ�ҳ��Ϣ��ù涨��һҳ�����б�
	 * 
	 * @param sql
	 *            SQL���
	 * @param pagination
	 *            ��ҳ��Ϣ
	 * @return List(String[])һҳ�����б�
	 * @throws SQLException
	 */
	public List getPageList(String sql, Pagination pagination)
			throws SQLException {
		return getPageList(sql, 0, END_COL, pagination);
	}

	/**
	 * ���ݷ�ҳ��Ϣ��ù涨��һҳ�����б�
	 * 
	 * @param sql
	 *            SQL���
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ��������
	 * @param pagination
	 *            ��ҳ��Ϣ
	 * @return List(String[])һҳ�����б�
	 * @throws SQLException
	 */
	public List getPageList(String sql, int startCol, Pagination pagination)
			throws SQLException {
		return getPageList(sql, startCol, END_COL, pagination);
	}

	/**
	 * ���ݷ�ҳ��Ϣ�����ѯ���涨��һҳ�����б�
	 * 
	 * @param sql
	 *            SQL���
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ��������
	 * @param endCol
	 *            ��ȡ���������ֹ�У�������
	 * @param pagination
	 *            ��ҳ��Ϣ
	 * @return List(String[])һҳ�����б�
	 * @throws SQLException
	 */
	public List getPageList(String sql, int startCol, int endCol,
			Pagination pagination) throws SQLException {
		this.pagination = pagination;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);

			if (endCol == END_COL) {
				endCol = rs.getMetaData().getColumnCount();
			}
			int len = endCol - startCol;

			rs.last();
			pagination.setRowSize(rs.getRow());
			int startRow = pagination.getStartRow();
			if (startRow <= 1) {
				rs.beforeFirst();
			} else {
				rs.absolute(startRow - 1);
			}

			int pageRows = pagination.getPageRows();
			for (int i = 0; i < pageRows && rs.next(); i++) {
				String[] row = new String[len];
				for (int j = 0; j < len; j++) {
					row[j] = formatNull(rs.getString(j + 1));
				}
				list.add(row);
			}
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}

		return list;
	}

	/**
	 * ���ݷ�ҳ��Ϣ��SQL����ѯ���ݿ���һҳ�����б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @param pagination
	 *            ��ҳ��Ϣ
	 * @return List(String[])һҳ�����б�
	 * @throws SQLException
	 */
	public List getPageList(String sql, Object[] params, Pagination pagination)
			throws SQLException {
		return getPageList(sql, params, 0, END_COL, pagination);
	}

	/**
	 * ���ݷ�ҳ��Ϣ��SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ������ֹ�л��һҳ�����б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ����������
	 * @param pagination
	 *            ��ҳ��Ϣ
	 * @return List(String[])һҳ�����б�
	 * @throws SQLException
	 */
	public List getPageList(String sql, Object[] params, int startCol,
			Pagination pagination) throws SQLException {
		return getPageList(sql, params, startCol, END_COL, pagination);
	}

	/**
	 * ���ݷ�ҳ��Ϣ��SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ����ָ����ֹ�л��һҳ�����б�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @param startCol
	 *            ��ȡ���������ʼ�У���0��ʼ����������
	 * @param endCol
	 *            ��ȡ���������ֹ�У�������
	 * @param pagination
	 *            ��ҳ��Ϣ
	 * @return List(String[])һҳ�����б�
	 * @throws SQLException
	 */
	public List getPageList(String sql, Object[] params, int startCol,
			int endCol, Pagination pagination) throws SQLException {
		this.pagination = pagination;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			stmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery(sql);

			if (endCol == END_COL) {
				endCol = rs.getMetaData().getColumnCount();
			}
			int len = endCol - startCol;

			rs.last();
			pagination.setRowSize(rs.getRow());
			int startRow = pagination.getStartRow();
			if (startRow <= 1) {
				rs.beforeFirst();
			} else {
				rs.absolute(startRow - 1);
			}

			int pageRows = pagination.getPageRows();
			for (int i = 0; i < pageRows && rs.next(); i++) {
				String[] row = new String[len];
				for (int j = 0; j < len; j++) {
					row[j] = formatNull(rs.getString(j + 1));
				}
				list.add(row);
			}
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}

		return list;
	}

	/**
	 * ������ݿ��ҳ��Ϣ��ֻ���ȵ�����<code>getPageList(...)</code>����֮��
	 * ���ô˷����Ż᷵��һ����ҳ��Ϣʵ�������򷵻�null��
	 * 
	 * @return ���ݿ��ҳ��Ϣ or null��
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * ִ��һ�� INSERT SQL ��������������ֶε�ֵ��
	 * 
	 * @param sql
	 *            INSERT SQL ���
	 * @return ����������ֶε�ֵ
	 * @throws SQLException
	 */
	public long getGeneratedKey(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql, new int[] { 1 });
			rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽִ�в�����䲢�����������ֵ
	 * 
	 * @param sql
	 *            Ԥ���� INSERT SQL ���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @return ����������ֶε�ֵ
	 * @throws SQLException
	 */
	public long getGeneratedKey(String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql, new int[] { 1 });
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽִ������������䲢�����������ֵ����
	 * 
	 * @param sql
	 *            Ԥ���� INSERT SQL ���
	 * @param paramsList
	 *            SQL��������Ϊnull��
	 * @return ����������ֶε�ֵ
	 * @throws SQLException
	 */
	public long[] getGeneratedKeys(String sql, List paramsList)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		long[] ids = null;
		try {
			stmt = conn.prepareStatement(sql, new int[] { 1 });
			if (paramsList != null && paramsList.size() > 0) {
				ids = new long[paramsList.size()];
				for (int i = 0; i < paramsList.size(); i++) {
					String[] params = (String[]) paramsList.get(i);
					for (int j = 0; j < params.length; j++) {
						stmt.setObject(j + 1, params[j]);
					}
					stmt.executeUpdate();
					rs = stmt.getGeneratedKeys();
					rs.next();
					ids[i] = rs.getLong(1);
				}
			}
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
		return ids;
	}

	/**
	 * ����SQL����ѯ��һ����������Bean������
	 * 
	 * @param bean
	 *            Ҫ���õ�JavaBean����
	 * @param fields
	 *            JavaBean���ֶ�
	 * @param sql
	 *            SQL���
	 * @return true����ѯ����������bean�ɹ�������δ��ѯ�������
	 * @throws SQLException
	 */
	public boolean populate(Object bean, String[] fields, String sql)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return populate(bean, fields, rs);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ�Ľ�������ÿ��Bean�����Բ�����List��
	 * 
	 * @param cls
	 *            Ҫ���õ�JavaBean��Class
	 * @param fields
	 *            JavaBean���ֶ�
	 * @param sql
	 *            SQL���
	 * @return ���bean��List
	 * @throws SQLException
	 */
	public List populate(Class cls, String[] fields, String sql)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return populate(cls, fields, rs);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ��һ����������Bean������
	 * 
	 * @param bean
	 *            Ҫ���õ�JavaBean����
	 * @param fields
	 *            JavaBean���ֶ�
	 * @param sql
	 *            SQL���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @return true����ѯ����������bean�ɹ�������δ��ѯ�������
	 * @throws SQLException
	 */
	public boolean populate(Object bean, String[] fields, String sql,
			Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return populate(bean, fields, rs);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ�Ľ�������ÿ��Bean�����Բ�����List��
	 * 
	 * @param cls
	 *            Ҫ���õ�JavaBean��Class
	 * @param fields
	 *            JavaBean���ֶ�
	 * @param sql
	 *            SQL���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @return ���bean��List
	 * @throws SQLException
	 */
	public List populate(Class cls, String[] fields, String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return populate(cls, fields, rs);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���ݽ�������bean������
	 * 
	 * @param bean
	 *            JavaBean����
	 * @param fields
	 *            JavaBean���ֶ�
	 * @param rs
	 *            �����
	 * @return true����ѯ����������bean�ɹ�������δ��ѯ�������
	 * @throws SQLException
	 */
	private boolean populate(Object bean, String[] fields, ResultSet rs)
			throws SQLException {
		if (rs.next()) {
			Map map = new HashMap();
			for (int i = 0; i < fields.length; i++) {
				map.put(fields[i], formatNull(rs.getString(i + 1)));
			}

			try {
				BeanUtils.populate(bean, map);
			} catch (InvocationTargetException e) {
				throw new SQLException(e.getMessage());
			} catch (IllegalAccessException e) {
				throw new SQLException(e.getMessage());
			}

			return true;
		}
		return false;
	}

	/**
	 * ���ݽ�������bean�����ԣ�������List�С�
	 * 
	 * @param cls
	 *            Ҫ���õ�JavaBean��Class
	 * @param fields
	 *            JavaBean���ֶ�
	 * @param rs
	 *            �����
	 * @return ���bean��List
	 * @throws SQLException
	 */
	private List populate(Class cls, String[] fields, ResultSet rs)
			throws SQLException {
		List list = new ArrayList();

		try {
			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 0; i < fields.length; i++) {
					map.put(fields[i], formatNull(rs.getString(i + 1)));
				}
				Object bean = cls.newInstance();
				BeanUtils.populate(bean, map);
				list.add(bean);
			}
		} catch (InstantiationException e) {
			throw new SQLException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage());
		}

		return list;
	}

	/**
	 * ִ��һ�θ����� INSERT��UPDATE or DELETE SQL ���
	 * 
	 * @param sql
	 *            SQL���
	 * @return ���롢���»�ɾ��������
	 * @throws SQLException
	 */
	public int update(String sql) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ������ʽִ��һ�θ����� INSERT��UPDATE or DELETE SQL ���
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            SQL ��������Ϊnull��
	 * @return ���롢���»�ɾ��������
	 * @throws SQLException
	 */
	public int update(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			return stmt.executeUpdate();
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬ִ��SQL�Ĵ������ݲ�������ȷ����
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            SQL ��������Ϊnull��
	 * @return int ���ظ��µ�������
	 * @throws SQLException
	 */
	public int updates(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			int rows = 0;
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(1, params[i]);
					rows += stmt.executeUpdate();
				}
			}
			return rows;
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬 ִ��SQL�Ĵ�������
	 * <code>params.length</code>ȷ����
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            ���� SQL ��������Ϊnull��
	 * @return ���ظ��µ�������
	 * @throws SQLException
	 */
	public int updates(String sql, Object[][] params) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			int rows = 0;
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					for (int j = 0; j < params[i].length; j++) {
						stmt.setObject(j + 1, params[i][j]);
					}
					rows += stmt.executeUpdate();
				}
			}
			return rows;
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬 ִ��SQL�Ĵ�������
	 * <code>paramList.size()</code>ȷ����
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param paramList
	 *            List(Object[]) SQL ��������Ϊnull��
	 * @return ���ظ��µ�������
	 * @throws SQLException
	 */
	public int updates(String sql, List paramList) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			int rows = 0;
			if (paramList != null) {
				for (int i = 0, ii = paramList.size(); i < ii; i++) {
					Object[] objs = (Object[]) paramList.get(i);
					for (int j = 0; j < objs.length; j++) {
						stmt.setObject(j + 1, objs[j]);
					}
					rows += stmt.executeUpdate();
				}
			}
			return rows;
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ������ʽִ��һ�θ����� INSERT��UPDATE or DELETE SQL ����飬 SQL ������Ӧͬһ�������
	 * 
	 * @param sqls
	 *            Ԥ����SQL�����
	 * @param params
	 *            SQL ��������Ϊnull��
	 * @return ����SQL�������µ�������
	 * @throws SQLException
	 */
	public int[] update(String[] sqls, Object[] params) throws SQLException {
		int[] updatedRows = new int[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			updatedRows[i] = update(sqls[i], params);
		}
		return updatedRows;
	}

	/**
	 * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬 ִ��SQL�����Ĵ�������
	 * <code>params.length</code>ȷ����
	 * 
	 * @param sqls
	 *            Ԥ����SQL�����
	 * @param params
	 *            SQL ��������Ϊnull��
	 * @return ����SQL�������µ�������
	 * @throws SQLException
	 */
	public int[] updates(String[] sqls, Object[] params) throws SQLException {
		int[] updatedRows = new int[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			updatedRows[i] = updates(sqls[i], params);
		}
		return updatedRows;
	}

	/**
	 * ����Ԥ������ʽִ��һ�θ����� INSERT��UPDATE or DELETE SQL ����飬 ÿ��SQL��Ӧ��ͬ�Ĳ����顣
	 * 
	 * @param sqls
	 *            Ԥ����SQL�����
	 * @param params
	 *            SQL������
	 * @return ����SQL�������µ�������
	 * @throws SQLException
	 */
	public int[] update(String[] sqls, Object[][] params) throws SQLException {
		int[] updatedRows = new int[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			updatedRows[i] = update(sqls[i], params[i]);
		}
		return updatedRows;
	}

	/**
	 * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ����飬
	 * ÿ��SQL��Ӧ��ͬ�Ĳ����飬ÿ��SQLִ�еĴ��������Ӧ�Ĳ�����ĳ���һ�¡�
	 * 
	 * @param sqls
	 *            Ԥ����SQL�����
	 * @param params
	 *            SQL������
	 * @return ����SQL�������µ�������
	 * @throws SQLException
	 */
	public int[] updates(String[] sqls, Object[][] params) throws SQLException {
		int[] updatedRows = new int[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			updatedRows[i] = updates(sqls[i], params[i]);
		}
		return updatedRows;
	}

	/**
	 * �Ƿ��ѯ������
	 * 
	 * @param sql
	 *            ��ѯSQL���
	 * @return boolean ��ѯ�Ľ�������������򷵻�true�����򷵻�false��
	 * @throws SQLException
	 */
	public boolean hasData(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs.next();
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ����Ƿ��ѯ������
	 * 
	 * @param sql
	 *            Ԥ�����ѯSQL���
	 * @param params
	 *            SQL��������Ϊnull��
	 * @return boolean ��ѯ�Ľ�������������򷵻�true�����򷵻�false��
	 * @throws SQLException
	 */
	public boolean hasData(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return rs.next();
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ����Ƿ��ѯ�����ݣ��÷�����ִ��<code>params.length</code>��SQL��䡣
	 * 
	 * @param sql
	 *            Ԥ����SQL��䣬��SQL��ֻ����һ������
	 * @param params
	 *            SQL��������Ϊnull��
	 * @return ��ѯ�������򷵻�true
	 * @throws SQLException
	 */
	public boolean hasDatas(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(1, params[i]);
					rs = stmt.executeQuery();
					if (rs.next()) {
						return true;
					}
				}
			}
			return false;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ����Ƿ��ѯ�����ݣ��÷�����ִ��<code>paramList.size()</code>��SQL��䡣
	 * 
	 * @param sql
	 *            Ԥ����SQL��䣬��SQL��ֻ����һ������
	 * @param paramList
	 *            List(Object[]) SQL�������б���ÿһ������Ԫ��Ϊһ���������Ϊnull��
	 * @return ��ѯ�������򷵻�true
	 * @throws SQLException
	 */
	public boolean hasDatas(String sql, List paramList) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (paramList != null) {
				for (int i = 0, ii = paramList.size(); i < ii; i++) {
					Object[] param = (Object[]) paramList.get(i);
					for (int j = 0; j < param.length; j++) {
						stmt.setObject(j + 1, param[j]);
					}
					rs = stmt.executeQuery();
					if (rs.next()) {
						return true;
					}
				}
			}
			return false;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���һ������
	 * 
	 * @param sql
	 *            ��ѯSQL���
	 * @return ����
	 * @throws SQLException
	 */
	public int getInt(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ���һ������
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return ����
	 * @throws SQLException
	 */
	public int getInt(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���һ��������
	 * 
	 * @param sql
	 *            ��ѯSQL���
	 * @return ������
	 * @throws SQLException
	 */
	public long getLong(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getLong(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ���һ��������
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return ������
	 * @throws SQLException
	 */
	public long getLong(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			rs.next();
			return rs.getLong(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���һ��������
	 * 
	 * @param sql
	 *            ��ѯSQL���
	 * @return ������
	 * @throws SQLException
	 */
	public float getFloat(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getFloat(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ���һ��������
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return ������
	 * @throws SQLException
	 */
	public float getFloat(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			rs.next();
			return rs.getFloat(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���һ��˫���ȸ�����
	 * 
	 * @param sql
	 *            ��ѯSQL���
	 * @return ˫���ȸ�����
	 * @throws SQLException
	 */
	public double getDouble(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getDouble(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ���һ��˫���ȸ�����
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return ˫���ȸ�����
	 * @throws SQLException
	 */
	public double getDouble(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			rs.next();
			return rs.getDouble(1);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ���һ���ַ���
	 * 
	 * @param sql
	 *            ��ѯSQL���
	 * @return �����ַ�������û�в�ѯ�������򷵻�null��
	 * @throws SQLException
	 */
	public String getString(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String str = null;
			if (rs.next()) {
				str = rs.getString(1);
			}
			return str;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL�����ʽ���һ���ַ���
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return �����ַ�������û�в�ѯ�������򷵻�null��
	 * @throws SQLException
	 */
	public String getString(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			String str = null;
			if (rs.next()) {
				str = rs.getString(1);
			}
			return str;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�int�����в����ء�
	 * 
	 * @param sql
	 *            SQL���
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public int[] getIntArray(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new int[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getInt(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�int�����в����ء�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public int[] getIntArray(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			int[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new int[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getInt(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�long�����в����ء�
	 * 
	 * @param sql
	 *            SQL���
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public long[] getLongArray(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			long[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new long[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getLong(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�long�����в����ء�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public long[] getLongArray(String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			long[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new long[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getInt(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�float�����в����ء�
	 * 
	 * @param sql
	 *            SQL���
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public float[] getFloatArray(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			float[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new float[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getFloat(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�float�����в����ء�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public float[] getFloatArray(String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			float[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new float[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getFloat(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�double�����в����ء�
	 * 
	 * @param sql
	 *            SQL���
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public double[] getDoubleArray(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			double[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new double[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getDouble(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�double�����в����ء�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public double[] getDoubleArray(String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			double[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new double[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getDouble(i + 1);
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�String�����в����ء�
	 * 
	 * @param sql
	 *            SQL���
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public String[] getStringArray(String sql) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new String[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = formatNull(rs.getString(i + 1));
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�String�����в����ء�
	 * 
	 * @param sql
	 *            Ԥ����SQL���
	 * @param params
	 *            Ԥ����SQL��������Ϊnull��
	 * @return �������һ����ɵ����飬��û�������򷵻�null��
	 * @throws SQLException
	 */
	public String[] getStringArray(String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			String[] array = null;
			if (rs.next()) {
				ResultSetMetaData rsmt = rs.getMetaData();
				array = new String[rsmt.getColumnCount()];
				for (int i = 0; i < array.length; i++) {
					array[i] = formatNull(rs.getString(i + 1));
				}
			}
			return array;
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}

	/**
	 * ��ʽ���ַ���
	 * 
	 * @param rsString
	 *            ����ʽ�����ַ���
	 * @return �ַ���Ϊnull�򷵻�""�����򷵻�ԭ�ַ�����
	 */
	private String formatNull(String rsString) {
		return rsString == null ? "" : rsString;
	}

	/**
	 * �رս�����������쳣��
	 * 
	 * @param rs
	 *            �����
	 */
	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�SQL�����󣨺����쳣��
	 * 
	 * @param stmt
	 *            SQL������
	 */
	public void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �������ݿ��Ƿ��Զ��ύ
	 * 
	 * @param autoCommit
	 *            true���Զ��ύ��
	 * @throws SQLException
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		conn.setAutoCommit(autoCommit);
	}

	/**
	 * �ύ���ݿ����
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		conn.commit();
	}

	/**
	 * �ع����ݿ�����������쳣��
	 */
	public void rollback() {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر����ݿ����ӣ������쳣��
	 */
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
