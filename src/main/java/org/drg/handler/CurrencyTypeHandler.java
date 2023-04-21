package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.drg.enums.Currency;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyTypeHandler extends EnumTypeHandler<Currency> {
	public CurrencyTypeHandler() {
		super(Currency.class);
	}

	@Override
	public Currency getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.wasNull() ? null : Currency.getByString(rs.getString(columnName));
	}

	@Override
	public Currency getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.wasNull() ? null : Currency.getByString(rs.getString(columnIndex));
	}

	@Override
	public Currency getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return Currency.getByString(cs.getString(columnIndex));
	}
}

