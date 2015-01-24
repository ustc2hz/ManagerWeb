package cn.LBS.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import cn.LBS.Constant.Constants;
import cn.LBS.utils.Pagination;


/**
 * ���ݿ���������࣬�������ӡ��������رյ�һ��
 *
 */
public class DBTools 
{
	
	
	/**
	 * ����SQL��ѯ���ݿ⣬���һ�������
	 */
	public static ResultSet getResultset(String sql)throws SQLException
	{

		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getResultSet(sql);
		}
		finally
		{
			db.close();
		}
	}
	
	
	
	/**
	 * ����SQL��ѯ���ݿ⣬���һ��StringԪ�ص��б�
	 */
	public static List getStringList(String sql)throws SQLException
	{

		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getStringList(sql);
		}
		finally
		{
			db.close();
		}
	}
	
	

	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���Ԫ��ΪString���б�
	 */
	public static List getStringList(String sql, Object[] params) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getStringList(sql,params);
		}
		finally
		{
			db.close();
		}
		
	}
	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���ӽ��������ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 */
	public static List getList(String sql) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{	
			return db.getList(sql);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ�У�����ֹ�л��Ԫ��ΪString[]���б�
	 */
	public static List getList(String sql, int startCol) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getList(sql,startCol);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ����ָ����ֹ�л��Ԫ��ΪString[]���б�
	 */
	public static List getList(String sql, int startCol, int endCol) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try{
			return db.getList(sql, startCol, endCol);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���ӽ��������ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 */
	public static List getList(String sql, Object[] params) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getList(sql, params);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���ӽ������ָ����ʼ�У�����ֹ�л��Ԫ��ΪString[]���б�
	 */
	public static List getList(String sql, Object[] params, int startCol) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getList(sql, params, startCol);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ����Ԥ�����SQL����ѯ���ݿ⣬���ӽ��������ʼ������ֹ�л��Ԫ��ΪString[]���б�
	 */
	public static List getList(String sql, Object[] params, int startCol, int endCol) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getList(sql, params, startCol, endCol);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ���ݷ�ҳ��Ϣ���һҳ�����б�
	 */
	public static List getPageList(String sql, Pagination pagination) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getPageList(sql, pagination);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ���ݷ�ҳ��Ϣ����ĳ��ʼ�л��һҳ�����б�
	 */
	public static List getPageList(String sql, int startCol, Pagination pagination) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getPageList(sql, startCol, pagination);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ���ݷ�ҳ��Ϣ����ĳ��ʼ����ĳ��ֹ�еõ�һҳ�����б�
	 */
	public static List gegPageList(String sql, int startCol, int endCol, Pagination pagination) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getPageList(sql, startCol, endCol, pagination);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ���ݷ�ҳ��Ϣ��SQLԤ��������ѯ���ݿ���һҳ�б�
	 */
	public static List getPageList(String sql, Object[] params, Pagination pagination) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getPageList(sql, params, pagination);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ���ݷ�ҳ��Ϣ��SQLԤ������䣬�ӽ����ָ������ʼ�У�����ֹ�в�ѯһҳ�����б�
	 */
	public static List getPageList(String sql, Object[] params, int startCol, Pagination pagination) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getPageList(sql, params, startCol, pagination);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ���ݷ�ҳ��Ϣ��SQLԤ��������ѯ���ݿ⣬���ӽ������ָ����ʼ����ָ����ֹ�л��һҳ�����б�
	 */
	public static List getPageList(String sql, Object[] params, int startCol, int endCol, Pagination pagination) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getPageList(sql, params, startCol, endCol, pagination);
		}
		finally
		{
			db.close();
		}
	}
	
	/**
	 * ִ��һ�� INSERT SQL��������������ֶε�ֵ
	 */
	public static long getGneratedkKey(String sql) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try{
			return db.getGeneratedKey(sql);
		}
		finally
		{
			db.close();
		}
	}
	/**
	 * ����Ԥ����SQL�����ʽִ�в�����䲢���������ֵ
	 */
	public static long getGeneratedKey(String sql, Object[] params) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.getGeneratedKey(sql, params);
		}
		finally
		{
			db.close();
		}
		
	}
	
	/**
	 * ����SQL����ѯ��һ��������Bean�Ķ���
	 * @param bean
	 * @param fields JavaBean���ֶ�
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static boolean populate(Object bean, String[] fields, String sql) throws SQLException
	{
		DBService db = new DBService(Constants.DBNAME_LBS);
		try
		{
			return db.populate(bean, fields, sql);
		}
		finally
		{
			db.close();
		}
	}
	
	   /**
     * ����SQL����ѯ�Ľ�������ÿ��Bean�����Բ�����List��
     * @param cls Ҫ���õ�Bean��Class
     * @param fields JavaBean���ֶ�
     * @param sql SQL���
     * @return ���bean��List
     * @throws SQLException
     */
    public static List populate(Class cls, String[] fields, String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.populate(cls, fields, sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ��һ����������Bean������
     * @param bean Ҫ���õ�Bean����
     * @param fields JavaBean���ֶ�
     * @param sql SQL���
     * @param params SQL��������Ϊnull��
     * @return true����ѯ����������bean�ɹ�������δ��ѯ�������
     * @throws SQLException
     */
    public static boolean populate(Object bean, String[] fields, String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.populate(bean, fields, sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ�Ľ�������ÿ��Bean�����Բ�����List��
     * @param cls Ҫ���õ�Bean��Class
     * @param fields JavaBean���ֶ�
     * @param sql SQL���
     * @param params SQL��������Ϊnull��
     * @return ���bean��List
     * @throws SQLException
     */
    public static List populate(Class cls, String[] fields, String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.populate(cls, fields, sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ִ��һ�θ����� INSERT��UPDATE or DELETE SQL ���
     * @param sql SQL���
     * @return ���롢���»�ɾ��������
     * @throws SQLException
     */
    public static int update(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.update(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ��һ�θ����� INSERT��UPDATE or DELETE SQL ���
     * @param sql Ԥ����SQL���
     * @param params SQL ��������Ϊnull��
     * @return ���롢���»�ɾ��������
     * @throws SQLException
     */
    public static int update(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.update(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬ִ��SQL�Ĵ������ݲ�������ȷ����
     * @param sql Ԥ����SQL���
     * @param params SQL ��������Ϊnull��
     * @return int ���ظ��µ�������
     * @throws SQLException
     */
    public static int updates(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int i = db.updates(sql, params);
            db.commit();
            return i;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬
     * ִ��SQL�Ĵ�������<code>params.length</code>ȷ����
     * @param sql Ԥ����SQL���
     * @param params ���� SQL ��������Ϊnull��
     * @return ���ظ��µ�������
     * @throws SQLException
     */
    public static int updates(String sql, Object[][] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int i = db.updates(sql, params);
            db.commit();
            return i;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬
     * ִ��SQL�Ĵ�������<code>paramList.size()</code>ȷ����
     * @param sql Ԥ����SQL���
     * @param paramList List(Object[]) SQL ����
     * @return ���ظ��µ�������
     * @throws SQLException
     */
    public static int updates(String sql, List paramList) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int i = db.updates(sql, paramList);
            db.commit();
            return i;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ��һ�θ����� INSERT��UPDATE or DELETE SQL ����飬
     * SQL ������Ӧͬһ�������
     * @param sqls Ԥ����SQL�����
     * @param params SQL ��������Ϊnull��
     * @return ����SQL�������µ�������
     * @throws SQLException
     */
    public static int[] update(String[] sqls, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int[] rows = db.update(sqls, params);
            db.commit();
            return rows;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ��䣬
     * ִ��SQL�����Ĵ�������<code>params.length</code>ȷ����
     * @param sqls Ԥ����SQL�����
     * @param params SQL ��������Ϊnull��
     * @return ����SQL�������µ�������
     * @throws SQLException
     */
    public static int[] updates(String[] sqls, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int[] rows = db.updates(sqls, params);
            db.commit();
            return rows;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ��һ�θ����� INSERT��UPDATE or DELETE SQL ����飬
     * ÿ��SQL��Ӧ��ͬ�Ĳ����顣
     * @param sqls Ԥ����SQL�����
     * @param params SQL������
     * @return ����SQL�������µ�������
     * @throws SQLException
     */
    public static int[] update(String[] sqls, Object[][] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int[] rows = db.update(sqls, params);
            db.commit();
            return rows;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ������ʽִ�ж�θ����� INSERT��UPDATE or DELETE SQL ����飬
     * ÿ��SQL��Ӧ��ͬ�Ĳ����飬ÿ��SQLִ�еĴ��������Ӧ�Ĳ�����ĳ���һ�¡�
     * @param sqls Ԥ����SQL�����
     * @param params SQL������
     * @return ����SQL�������µ�������
     * @throws SQLException
     */
    public static int[] updates(String[] sqls, Object[][] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            db.setAutoCommit(false);
            int[] rows = db.updates(sqls, params);
            db.commit();
            return rows;
        }
        catch (SQLException e)
        {
            db.rollback();
            throw e;
        }
        finally
        {
            db.close();
        }
    }

    /**
     * �Ƿ��ѯ������
     * @param sql ��ѯSQL���
     * @return boolean ��ѯ�Ľ�������������򷵻�true�����򷵻�false��
     * @throws SQLException
     */
    public static boolean hasData(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.hasData(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ����Ƿ��ѯ������
     * @param sql Ԥ�����ѯSQL���
     * @param params ��ѯSQL����������Ϊnull��
     * @return boolean ��ѯ�Ľ�������������򷵻�true�����򷵻�false��
     * @throws SQLException
     */
    public static boolean hasData(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.hasData(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ����Ƿ��ѯ�����ݣ��÷�����ִ��<code>params.length</code>��SQL��䡣
     * @param sql Ԥ����SQL��䣬��SQL��ֻ����һ������
     * @param params SQL��������Ϊnull��
     * @return ��ѯ�������򷵻�true
     * @throws SQLException
     */
    public static boolean hasDatas(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.hasDatas(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ����Ƿ��ѯ�����ݣ��÷�����ִ��<code>paramList.size()</code>��SQL��䡣
     * @param sql Ԥ����SQL��䣬��SQL��ֻ����һ������
     * @param paramList List(Object[]) SQL�������б���ÿһ������Ԫ��Ϊһ���������Ϊnull��
     * @return ��ѯ�������򷵻�true
     * @throws SQLException
     */
    public static boolean hasDatas(String sql, List paramList) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.hasDatas(sql, paramList);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ���һ������
     * @param sql ��ѯSQL���
     * @return ����
     * @throws SQLException
     */
    public static int getInt(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getInt(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ���һ������
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return ����
     * @throws SQLException
     */
    public static  int getInt(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getInt(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ���һ��������
     * @param sql ��ѯSQL���
     * @return ������
     * @throws SQLException
     */
    public static long getLong(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getLong(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ���һ��������
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return ������
     * @throws SQLException
     */
    public static long getLong(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getLong(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ���һ��������
     * @param sql ��ѯSQL���
     * @return ������
     * @throws SQLException
     */
    public static float getFloat(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getFloat(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ���һ��������
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return ������
     * @throws SQLException
     */
    public static float getFloat(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getFloat(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ���һ��˫���ȸ�����
     * @param sql ��ѯSQL���
     * @return ˫���ȸ�����
     * @throws SQLException
     */
    public static double getDouble(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getDouble(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ���һ��˫���ȸ�����
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return ˫���ȸ�����
     * @throws SQLException
     */
    public static double getDouble(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getDouble(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ���һ���ַ���
     * @param sql ��ѯSQL���
     * @return �ַ���
     * @throws SQLException
     */
    public static String getString(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getString(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL�����ʽ���һ���ַ���
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return �ַ���
     * @throws SQLException
     */
    public static  String getString(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getString(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�int�����в����ء�
     * @param sql SQL���
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static int[] getIntArray(String sql) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getIntArray(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�int�����в����ء�
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static int[] getIntArray(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getIntArray(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�long�����в����ء�
     * @param sql SQL���
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static long[] getLontArray(String sql) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getLongArray(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�long�����в����ء�
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static long[] getLongArray(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getLongArray(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�float�����в����ء�
     * @param sql SQL���
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static float[] getFloatArray(String sql) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getFloatArray(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�float�����в����ء�
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static float[] getFloatArray(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getFloatArray(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�double�����в����ء�
     * @param sql SQL���
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static double[] getDoubleArray(String sql) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getDoubleArray(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�double�����в����ء�
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static double[] getDoubleArray(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService();
        try
        {
            return db.getDoubleArray(sql, params);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�String�����в����ء�
     * @param sql SQL���
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static String[] getStringArray(String sql) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getStringArray(sql);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * ����Ԥ����SQL����ѯ���ݿⲢ��ȡ�õĵ�һ�����ݴ�ŵ�String�����в����ء�
     * @param sql Ԥ����SQL���
     * @param params Ԥ����SQL��������Ϊnull��
     * @return �������һ����ɵ����飬��û�������򷵻�null��
     * @throws SQLException
     */
    public static  String[] getStringArray(String sql, Object[] params) throws SQLException
    {
        DBService db = new DBService(Constants.DBNAME_LBS);
        try
        {
            return db.getStringArray(sql, params);
        }
        finally
        {
            db.close();
        }
    }
}
