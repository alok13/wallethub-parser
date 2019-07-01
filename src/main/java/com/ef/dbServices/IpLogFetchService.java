package com.ef.dbServices;

import com.ef.dto.BlockedIpDto;
import com.ef.utils.DateUtils;
import com.ef.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class IpLogFetchService {
    final String IP_TO_BLOCK = "SELECT IP, COUNT(IP) as TOTAL FROM IP_LOG WHERE (REQUEST_DATE BETWEEN ? AND ?) " +
            "GROUP BY IP HAVING TOTAL > ?";

    public List<BlockedIpDto> execute(Map<String, String> argMap) {
        String duration = argMap.get("duration");
        String startDate = argMap.get("startDate");
        String threshold = argMap.get("threshold");
        java.util.Date startingTime = DateUtils.parseDate(startDate);
        java.util.Date endDate = DateUtils.addHoursToDate(startingTime,duration);
        return getBlockedIpFromDatabase(duration, threshold, startingTime, endDate);

    }


    private List<BlockedIpDto> getBlockedIpFromDatabase(String duration, String threshold, java.util.Date startingTime, java.util.Date endDate) {
        List<BlockedIpDto> blockedList=new ArrayList<>();
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement stmt = getPreparedStatement(threshold, startingTime, endDate, conn, IP_TO_BLOCK);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ip = rs.getString(1);
                int count = rs.getInt(2);
                BlockedIpDto blockedIpDto=new BlockedIpDto(ip,startingTime,duration,count,endDate);
                blockedList.add(blockedIpDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            } catch (Exception se) {
                System.out.println(se.getMessage());

            }
        }
        return blockedList;
    }

    private PreparedStatement getPreparedStatement(String threshold, java.util.Date startingDate, java.util.Date endDate, Connection conn,
                                                   String find_ip_to_block) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement(find_ip_to_block);
        stmt.setTimestamp(1, new Timestamp(startingDate.getTime()));
        stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
        stmt.setLong(3, Long.parseLong(threshold));
        return stmt;
    }
}
