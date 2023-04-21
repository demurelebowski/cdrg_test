package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.drg.enums.WalletEventType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletEventTypeHandler extends EnumTypeHandler<WalletEventType> {
	public WalletEventTypeHandler() {
		super(WalletEventType.class);
	}

	@Override
	public WalletEventType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.wasNull() ? null : WalletEventType.getByString(rs.getString(columnName));
	}

	@Override
	public WalletEventType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.wasNull() ? null : WalletEventType.getByString(rs.getString(columnIndex));
	}

	@Override
	public WalletEventType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return WalletEventType.getByString(cs.getString(columnIndex));
	}
}
