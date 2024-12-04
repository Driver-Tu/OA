package wh.fcfz.officecontroller.all.handler;

import cn.hutool.json.JSONArray;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@ExcelIgnoreUnannotated
public class JsonArrayTypeHandler extends BaseTypeHandler<JSONArray> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONArray parameter, JdbcType jdbcType) throws SQLException {
        // 将 JSONObject 转换为字符串存储到数据库
        try {
            ps.setString(i, parameter.toString());
        } catch (Exception e) {
            throw new SQLException("Failed to set parameter as JSON", e);
        }
    }

    @Override
    public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        if (json != null) {
            try {
                return new JSONArray(objectMapper.readTree(json));
            } catch (JsonProcessingException e) {
                throw new SQLException("Failed to parse JSON", e);
            }
        }
        return null;
    }

    @Override
    public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        if (json != null) {
            try {
                return new JSONArray(objectMapper.readTree(json));
            } catch (JsonProcessingException e) {
                throw new SQLException("Failed to parse JSON", e);
            }
        }
        return null;
    }

    @Override
    public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        if (json != null) {
            try {
                return new JSONArray(objectMapper.readTree(json));
            } catch (JsonProcessingException e) {
                throw new SQLException("Failed to parse JSON", e);
            }
        }
        return null;
    }
}

