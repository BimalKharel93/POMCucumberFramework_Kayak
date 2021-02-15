package src.main.java.com.kayak.utilities;

import org.apache.log4j.Logger;

public class Constants {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    public static final String sConstEnvironment = "PROD", sAutomationAPI = "API", sAutomationWeb ="Web";

    public static final int iHTTPCode201 = 201, iHTTPCode400 =400, iHTTPCode200 = 200, iHTTPCode203 = 203, iHTTPCode = 404,
    iHTTPCode401 = 401, iHTTPCode405 = 405, iHTTPCode418 = 418, iHTTPCode403 = 403;

    public static final String sContentType = "application/json";
    public static String sToken = "";
    public static String sApiURL = "";
    public String getKayakUrl() throws Exception{
        if(System.getProperty("Environment").toLowerCase().trim().startsWith("qa"))
            return "https://www.kayak.com/";
            log.info("wrong URL for \'Plase set environment correctly in property file.");
            return "Set environment correctly...";
    }
}
