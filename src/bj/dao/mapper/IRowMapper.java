package bj.dao.mapper;

import java.sql.ResultSet;
/**
 * ����Helperʵ��rs -> model
 * */
public interface IRowMapper<T> {
	public abstract T rowMapper(ResultSet rs);
}
