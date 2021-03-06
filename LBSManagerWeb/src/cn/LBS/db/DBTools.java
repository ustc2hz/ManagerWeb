package cn.LBS.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import cn.LBS.Constant.Constants;
import cn.LBS.utils.Pagination;


/**
 * 数据库操作工具类，具有连接、操作、关闭的一体
 *
 */
public class DBTools 
{
	
	
	/**
	 * 根据SQL查询数据库，获得一个结果集
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
	 * 根据SQL查询数据库，获得一个String元素的列表
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
	 * 利用预编译的SQL语句查询数据库，获得元素为String的列表
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
	 * 根据预编译的SQL语句查询数据库，并从结果集的起始列至终止列获得元素为String[]的列表
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
	 * 根据预编译的SQL语句查询数据库，并从结果集的指定起始列，至终止列获得元素为String[]的列表
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
	 * 根据预编译的SQL语句查询数据库，并从结果集的指定起始列至指定终止列获得元素为String[]的列表
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
	 * 利用预编译的SQL语句查询数据库，并从结果集的起始列至终止列获得元素为String[]的列表
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
	 * 利用预编译的SQL语句查询数据库，并从结果集的指定起始列，至终止列获得元素为String[]的列表
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
	 * 利用预编译的SQL语句查询数据库，并从结果集的起始列至终止列获得元素为String[]的列表
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
	 * 根据分页信息获得一页数据列表
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
	 * 根据分页信息，从某起始列获得一页数据列表
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
	 * 根据分页信息，从某起始列至某终止列得到一页数据列表
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
	 * 根据分页信息和SQL预编译语句查询数据库获得一页列表
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
	 * 根据分页信息和SQL预编译语句，从结果集指定的起始列，至终止列查询一页数据列表
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
	 * 根据分页信息和SQL预编译语句查询数据库，并从结果集的指定起始列至指定终止列获得一页数据列表
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
	 * 执行一条 INSERT SQL语句获得其中自增字段的值
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
	 * 采用预编译SQL语句形式执行插入语句并获得自增的值
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
	 * 根据SQL语句查询的一条结果填充Bean的对象
	 * @param bean
	 * @param fields JavaBean的字段
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
     * 根据SQL语句查询的结果集填充每个Bean的属性并存入List中
     * @param cls 要设置的Bean的Class
     * @param fields JavaBean的字段
     * @param sql SQL语句
     * @return 存放bean的List
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
     * 根据预编译SQL语句查询的一条结果集填充Bean的属性
     * @param bean 要设置的Bean对象
     * @param fields JavaBean的字段
     * @param sql SQL语句
     * @param params SQL参数，可为null。
     * @return true，查询到结果并填充bean成功；否则未查询到结果。
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
     * 根据预编译SQL语句查询的结果集填充每个Bean的属性并存入List中
     * @param cls 要设置的Bean的Class
     * @param fields JavaBean的字段
     * @param sql SQL语句
     * @param params SQL参数，可为null。
     * @return 存放bean的List
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
     * 执行一次给定的 INSERT、UPDATE or DELETE SQL 语句
     * @param sql SQL语句
     * @return 插入、更新或删除的行数
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
     * 采用预编译形式执行一次给定的 INSERT、UPDATE or DELETE SQL 语句
     * @param sql 预编译SQL语句
     * @param params SQL 参数，可为null。
     * @return 插入、更新或删除的行数
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
     * 采用预编译形式执行多次给定的 INSERT、UPDATE or DELETE SQL 语句，执行SQL的次数根据参数长度确定。
     * @param sql 预编译SQL语句
     * @param params SQL 参数，可为null。
     * @return int 返回更新的总行数
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
     * 采用预编译形式执行多次给定的 INSERT、UPDATE or DELETE SQL 语句，
     * 执行SQL的次数根据<code>params.length</code>确定。
     * @param sql 预编译SQL语句
     * @param params 多组 SQL 参数，可为null。
     * @return 返回更新的总行数
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
     * 采用预编译形式执行多次给定的 INSERT、UPDATE or DELETE SQL 语句，
     * 执行SQL的次数根据<code>paramList.size()</code>确定。
     * @param sql 预编译SQL语句
     * @param paramList List(Object[]) SQL 参数
     * @return 返回更新的总行数
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
     * 采用预编译形式执行一次给定的 INSERT、UPDATE or DELETE SQL 语句组，
     * SQL 语句组对应同一组参数。
     * @param sqls 预编译SQL语句组
     * @param params SQL 参数，可为null。
     * @return 返回SQL语句组更新的行数组
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
     * 采用预编译形式执行多次给定的 INSERT、UPDATE or DELETE SQL 语句，
     * 执行SQL语句组的次数根据<code>params.length</code>确定。
     * @param sqls 预编译SQL语句组
     * @param params SQL 参数，可为null。
     * @return 返回SQL语句组更新的行数组
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
     * 采用预编译形式执行一次给定的 INSERT、UPDATE or DELETE SQL 语句组，
     * 每个SQL对应不同的参数组。
     * @param sqls 预编译SQL语句组
     * @param params SQL参数组
     * @return 返回SQL语句组更新的行数组
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
     * 采用预编译形式执行多次给定的 INSERT、UPDATE or DELETE SQL 语句组，
     * 每个SQL对应不同的参数组，每个SQL执行的次数和其对应的参数组的长度一致。
     * @param sqls 预编译SQL语句组
     * @param params SQL参数组
     * @return 返回SQL语句组更新的行数组
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
     * 是否查询到数据
     * @param sql 查询SQL语句
     * @return boolean 查询的结果集中有数据则返回true，否则返回false。
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
     * 采用预编译SQL语句形式获得是否查询到数据
     * @param sql 预编译查询SQL语句
     * @param params 查询SQL语句参数，可为null。
     * @return boolean 查询的结果集中有数据则返回true，否则返回false。
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
     * 采用预编译SQL语句形式获得是否查询到数据，该方法将执行<code>params.length</code>次SQL语句。
     * @param sql 预编译SQL语句，该SQL中只允许一个参数
     * @param params SQL参数，可为null。
     * @return 查询到数据则返回true
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
     * 采用预编译SQL语句形式获得是否查询到数据，该方法将执行<code>paramList.size()</code>次SQL语句。
     * @param sql 预编译SQL语句，该SQL中只允许一个参数
     * @param paramList List(Object[]) SQL参数，列表中每一个数组元素为一组参数，可为null。
     * @return 查询到数据则返回true
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
     * 获得一个整数
     * @param sql 查询SQL语句
     * @return 整数
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
     * 采用预编译SQL语句形式获得一个整数
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 整数
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
     * 获得一个长整数
     * @param sql 查询SQL语句
     * @return 长整数
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
     * 采用预编译SQL语句形式获得一个长整数
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 长整数
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
     * 获得一个浮点数
     * @param sql 查询SQL语句
     * @return 浮点数
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
     * 采用预编译SQL语句形式获得一个浮点数
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 浮点数
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
     * 获得一个双精度浮点数
     * @param sql 查询SQL语句
     * @return 双精度浮点数
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
     * 采用预编译SQL语句形式获得一个双精度浮点数
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 双精度浮点数
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
     * 获得一个字符串
     * @param sql 查询SQL语句
     * @return 字符串
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
     * 采用预编译SQL语句形式获得一个字符串
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 字符串
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
     * 根据SQL语句查询数据库并将取得的第一行数据存放到int数组中并返回。
     * @param sql SQL语句
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据预编译SQL语句查询数据库并将取得的第一行数据存放到int数组中并返回。
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据SQL语句查询数据库并将取得的第一行数据存放到long数组中并返回。
     * @param sql SQL语句
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据预编译SQL语句查询数据库并将取得的第一行数据存放到long数组中并返回。
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据SQL语句查询数据库并将取得的第一行数据存放到float数组中并返回。
     * @param sql SQL语句
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据预编译SQL语句查询数据库并将取得的第一行数据存放到float数组中并返回。
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据SQL语句查询数据库并将取得的第一行数据存放到double数组中并返回。
     * @param sql SQL语句
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据预编译SQL语句查询数据库并将取得的第一行数据存放到double数组中并返回。
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据SQL语句查询数据库并将取得的第一行数据存放到String数组中并返回。
     * @param sql SQL语句
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
     * 根据预编译SQL语句查询数据库并将取得的第一行数据存放到String数组中并返回。
     * @param sql 预编译SQL语句
     * @param params 预编译SQL参数，可为null。
     * @return 结果集第一行组成的数组，若没有数据则返回null。
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
