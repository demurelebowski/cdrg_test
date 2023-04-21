package org.drg.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.drg.enums.WalletEventType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletEventTypeHandler extends EnumTypeHandler<WalletEventType> {
	public WalletEventTypeHandler() {
		super(WalletEventType.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, WalletEventType parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getValue());
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
