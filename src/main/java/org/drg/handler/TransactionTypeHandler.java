package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.drg.enums.TransactionType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTypeHandler extends EnumTypeHandler<TransactionType> {
	public TransactionTypeHandler() {
		super(TransactionType.class);
	}

	@Override
	public TransactionType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.wasNull() ? null : TransactionType.getByString(rs.getString(columnName));
	}

	@Override
	public TransactionType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.wasNull() ? null : TransactionType.getByString(rs.getString(columnIndex));
	}

	@Override
	public TransactionType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return TransactionType.getByString(cs.getString(columnIndex));
	}
}

