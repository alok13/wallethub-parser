package com.ef;

import com.ef.dbServices.IpLogFetchService;
import com.ef.dbServices.impl.IpLogUpdateServiceImpl;
import com.ef.dbServices.impl.BlockedIpUpdateServiceImpl;
import com.ef.dto.BlockedIpDto;
import com.ef.dto.IpLogDto;
import com.ef.filehandler.LogReader;

import java.util.List;
import java.util.Map;

import static com.ef.utils.ArgumentParser.parseArgument;


public class Application {

    public void start(String[] args) {
        Map<String, String> argMap=parseArgument(args);
        LogReader logReader=new LogReader();
        System.out.println("Reading the log file");
        List<IpLogDto> logList=logReader.parseLog(argMap);

        System.out.println("Updating IpLog table in Db");
        IpLogUpdateServiceImpl ipLogUpdateService=new IpLogUpdateServiceImpl();
        ipLogUpdateService.execute(logList);

        System.out.println("Fetching blocked IPs from the Db");

        IpLogFetchService ipLogFetchService=new IpLogFetchService();
        List<BlockedIpDto> blockedList=ipLogFetchService.execute(argMap);
        for(BlockedIpDto dto:blockedList){
            System.out.println(dto.getIp());
        }

        System.out.println("Updating blocked IP into Db");
        BlockedIpUpdateServiceImpl blockedIpUpdateService=new BlockedIpUpdateServiceImpl();
        blockedIpUpdateService.execute(blockedList);
    }


}

