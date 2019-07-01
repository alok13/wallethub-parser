--1 Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.


"SELECT IP, COUNT(IP) as TOTAL FROM IP_LOG WHERE (REQUEST_DATE BETWEEN ? AND ?) " +
            "GROUP BY IP HAVING TOTAL > ?";

--  For Example:   SELECT IP, COUNT(IP) as TOTAL FROM IP_LOG WHERE (REQUEST_DATE BETWEEN '2017-01-01 00:00:00' AND '2019-07-02 02:52:37.221') GROUP BY IP HAVING TOTAL > 200;


--2.  Write MySQL query to find requests made by a given IP.

"SELECT * FROM IP_LOGLOG WHERE IP = ?"

