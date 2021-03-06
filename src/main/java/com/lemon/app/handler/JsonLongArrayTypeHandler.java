package com.lemon.app.handler;

import com.lemon.framework.util.JacksonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Json转Long[]
 * <p>
 *
 * @author hai-zhang
 * @since 2021-05-11
 */
public class JsonLongArrayTypeHandler extends BaseTypeHandler<Long[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter));
    }

    @Override
    public Long[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return this.toObject(rs.getString(columnName));
    }

    @Override
    public Long[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return this.toObject(rs.getString(columnIndex));
    }

    @Override
    public Long[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return this.toObject(cs.getString(columnIndex));
    }

    private String toJson(Long[] params) {
        return JacksonUtils.toJson(params);
    }

    private Long[] toObject(String content) {
        if (null != content && !content.isEmpty()) {
            return JacksonUtils.parseObject(content, Long[].class);
        } else {
            return null;
        }
    }
}
