package com.ef.dbServices.impl;

import com.ef.dbServices.DbService;
import com.ef.dto.IpLogDto;
import com.ef.utils.JdbcUtils;
import java.sql.*;
import java.util.List;




public class IpLogUpdateServiceImpl implements DbService<IpLogDto> {


    final String INSERT_LOGS = "INSERT INTO IP_LOG(REQUEST_DATE, IP, Request, Status,Agent) "
            + "VALUES(?,?,?,?,?)";

    public void execute(List<IpLogDto> list) {
        Connection conn = JdbcUtils.getConnection();
        try {

            PreparedStatement pstmt = conn.prepareStatement(INSERT_LOGS);
            for (IpLogDto logObjectDto : list) {
                prepareInputs(pstmt, logObjectDto);
                pstmt.addBatch();
            }
            pstmt.executeBatch();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception se) {
                System.out.println(se.getMessage());

            }
        }
    }


    private void prepareInputs(PreparedStatement pstmt, IpLogDto logObjectDto) throws SQLException {
        pstmt.setDate(1, new Date(logObjectDto.getDate().getTime()));
        pstmt.setString(2, logObjectDto.getIp());
        pstmt.setString(3, logObjectDto.getRequest());
        pstmt.setInt(4, logObjectDto.getStatus());
        pstmt.setString(5, logObjectDto.getAgent());
    }

}

