package com.ef.filehandler;

import com.ef.dto.IpLogDto;
import com.ef.utils.Constants;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ef.utils.Constants.EMPTY_STRING;
import static com.ef.utils.Constants.QUOTES;

@Repository
public class LogReader {
    public List<IpLogDto> parseLog(Map<String, String> argMap) {
        String thisLine = null;
        List<IpLogDto> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(argMap.get("accesslog"));
            BufferedReader br = new BufferedReader(fr);
            while ((thisLine = br.readLine()) != null) {
                IpLogDto newLogObject = getLogObjectDto(thisLine);

                list.add(newLogObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private IpLogDto getLogObjectDto(String thisLine) throws ParseException {
        String[] parsedLine = thisLine.split(Constants.DELIMITER);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        int status = Integer.parseInt(parsedLine[3].replace(QUOTES, EMPTY_STRING));
        return new IpLogDto(formatter.parse(parsedLine[0]), parsedLine[1], parsedLine[2],
                status, parsedLine[4]);
    }
}
