package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.drg.enums.WalletFlag;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletFlagTypeHandler extends EnumTypeHandler<WalletFlag> {
	public WalletFlagTypeHandler() {
		super(WalletFlag.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, WalletFlag parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getValue());
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
