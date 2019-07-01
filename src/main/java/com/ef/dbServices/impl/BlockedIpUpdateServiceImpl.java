package com.ef.dbServices.impl;

import com.ef.dbServices.DbService;
import com.ef.dto.BlockedIpDto;
import com.ef.utils.JdbcUtils;

import java.sql.*;
import java.util.List;

import static com.ef.utils.Constants.*;

public class BlockedIpUpdateServiceImpl implements DbService<BlockedIpDto> {
    public static final String INSERT_LOGS = "INSERT INTO BLOCKED_IP(IP, FREQUENCY, START_TIME, END_TIME) "
            + "VALUES(?,?,?,?)";

    public void execute(List<BlockedIpDto> list) {
        Connection conn = JdbcUtils.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_LOGS);
            for (BlockedIpDto blockedIpDto : list) {
                prepareInputs(pstmt, blockedIpDto);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception se) {
            }
        }
    }

    private void prepareInputs(PreparedStatement pstmt, BlockedIpDto blockedIpDto) throws SQLException {
        pstmt.setString(1, blockedIpDto.getIp());
        pstmt.setInt(2, blockedIpDto.getFrequency());
        pstmt.setDate(3, new Date(blockedIpDto.getStartTime().getTime()));
        pstmt.setDate(4, new Date(blockedIpDto.getEndTime().getTime()));

    }
}
