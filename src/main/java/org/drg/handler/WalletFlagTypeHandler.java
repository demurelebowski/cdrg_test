package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.drg.enums.WalletFlag;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletFlagTypeHandler extends EnumTypeHandler<WalletFlag> {
	public WalletFlagTypeHandler() {
		super(WalletFlag.class);
	}

	@Override
	public WalletFlag getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.wasNull() ? null : WalletFlag.getByString(rs.getString(columnName));
	}

	@Override
	public WalletFlag getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.wasNull() ? null : WalletFlag.getByString(rs.getString(columnIndex));
	}

	@Override
	public WalletFlag getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return WalletFlag.getByString(cs.getString(columnIndex));
	}
}
