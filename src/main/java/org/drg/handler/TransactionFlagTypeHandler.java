package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.drg.enums.TransactionFlag;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionFlagTypeHandler extends EnumTypeHandler<TransactionFlag> {
	public TransactionFlagTypeHandler() {
		super(TransactionFlag.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, TransactionFlag parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getValue());
	}

	@Override
	public TransactionFlag getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.wasNull() ? null : TransactionFlag.getByString(rs.getString(columnName));
	}

	@Override
	public TransactionFlag getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.wasNull() ? null : TransactionFlag.getByString(rs.getString(columnIndex));
	}

	@Override
	public TransactionFlag getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return TransactionFlag.getByString(cs.getString(columnIndex));
	}
}
