package com.ef.utils;

import java.util.HashMap;
import java.util.Map;

import static com.ef.utils.Constants.*;

public class ArgumentParser {

    public static Map<String, String> parseArgument(String[] args) {
        Map<String, String> argMap=new HashMap<>();
        for (String param : args) {
            param = param.replace(DOUBLE_DASH, EMPTY_STRING);
            String[] paramArray = param.split(EQUAL);
            String value=paramArray[1];
            if(paramArray[0].equals("startDate")){
                value=value.replace(PERIOD,SPACE);
            }
            argMap.put(paramArray[0],value);
        }
        return argMap;
    }

}
