package bj.dao.mapper;

import java.sql.ResultSet;
/**
 * °ïÖúHelperÊµÏÖrs -> model
 * */
public interface IRowMapper<T> {
	public abstract T rowMapper(ResultSet rs);
}
