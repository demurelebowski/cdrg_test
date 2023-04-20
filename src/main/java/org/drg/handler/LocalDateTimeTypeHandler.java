package org.drg.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, LocalDateTime inputObject, JdbcType jdbcType) throws SQLException {
		preparedStatement.setTimestamp(columnIndex, Timestamp.valueOf(inputObject));
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
		final Timestamp value = resultSet.getTimestamp(columnName);
		if (value == null) {
			return null;
		}
		return value.toLocalDateTime();
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return LocalDateTime.parse(resultSet.getString(i));
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
		final Timestamp value = callableStatement.getTimestamp(columnIndex);
		if (value == null) {
			return null;
		}
		return value.toLocalDateTime();
	}
}
