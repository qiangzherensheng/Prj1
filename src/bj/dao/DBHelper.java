package bj.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import bj.dao.mapper.IRowMapper;
import bj.entity.KnowledgeEntity;
/**
 * 该类的作用是执行sql语句
 */
public final class DBHelper {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private static  String URL="";
	private static ThreadLocal<Connection> t1=new ThreadLocal<Connection>();
	static{
		try {
			File file=new File("f:/db.properties");
			//定义Map接口的实现类Properties
			Properties p=new Properties();
			FileInputStream fis =new FileInputStream(file);
			p.load(fis);
			String server=p.getProperty("server");
			//System.out.println("server:"+server);
			String port=p.getProperty("port");
			String user=p.getProperty("user");
			String password=p.getProperty("password");
			String database=p.getProperty("database");
			fis.close();
			URL="jdbc:mysql://"+server+":"+port+"/"+database+"?user="+user+"&password="+password+"&characterEncoding=utf-8";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getConnection()throws Exception{
		//从t1中获取conn
		conn=t1.get();
		if(conn==null){
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.sql.DataSource source = (javax.sql.DataSource) ctx.lookup("java:comp/env/bj");
			conn = source.getConnection();
//			Class.forName("org.gjt.mm.mysql.Driver");
//			conn = DriverManager.getConnection(URL);
			t1.set(conn);
		}
		
	}
	public int executeUpdate(String sql,Object ...values) throws Exception{
		getConnection();
		pstmt = conn.prepareStatement(sql);
		setParameter(pstmt,values);
		return pstmt.executeUpdate();
	}
	public <T> List<T> executeQuery(IRowMapper<T> mapper,String sql,Object...values)throws Exception{
		List<T> list=new ArrayList<T>();
		getConnection();
		pstmt = conn.prepareStatement(sql);
		setParameter(pstmt,values);
		rs=pstmt.executeQuery();
		while(rs.next()){
			T  t=mapper.rowMapper(rs);
			list.add(t);
		}
		return list;
	}
	
	/*public ResultSet executeQuery(String sql,Object ...values) throws Exception{
		getConnection();
		pstmt = conn.prepareStatement(sql);
		setParameter(pstmt, values);
		rs= pstmt.executeQuery();
		return rs;
	}*/

	/***
	 * 执行聚合函数存查询
	 * @param sql 例如:select count(*) from userinfo
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public int executeScalar(String sql,Object ...values) throws Exception{
		int result = 0;//select max(*) from tt 
		getConnection();
		pstmt = conn.prepareStatement(sql);
		setParameter(pstmt, values);
		rs = pstmt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);
		}
		return result;
	}
	
	private void setParameter(PreparedStatement stmt, Object[] values) throws SQLException {
		if(values!=null && values.length>0){
			for (int i = 0; i < values.length; i++) {
				stmt.setObject(i+1, values[i]);
			}
		}
	}
	
	public void close()throws Exception{
		if(rs!=null){
			rs.close();
			rs = null;
		}
		if(pstmt!=null){
			pstmt.close();
			pstmt = null;
		}
		if(conn!=null){
			conn.close();
			conn = null;
			t1.remove();//从t1中移除conn
		}
	}
	/**
	 * 开始事务
	 * @throws Exception
	 */
	public void beginTrans() throws Exception{
		getConnection();//获取连接，并把连接放到t1中
		t1.get().setAutoCommit(false);//开始事务
	}
	/**
	 * 提交事务
	 */
	public void commit() throws Exception{
		t1.get().commit();//提交事务
	}
	/**
	 * 回滚事务
	 */
	public void rollback() throws Exception{
		t1.get().rollback();//回滚事务
	}
}

