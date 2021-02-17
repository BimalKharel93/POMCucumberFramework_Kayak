package src.main.java.com.kayak.utilities;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import src.main.java.com.kayak.factory.DriverManager;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class CommonUtilities {
    private DriverManager driverManager = new DriverManager();
    Logger log = Logger.getLogger(getClass().getSimpleName());
    static Properties props = new Properties();
    static FileInputStream fileIn = null;

    public void loadPropertyFiles(String propertiesFilePath) throws Exception {
        log.info("current dir using system " + propertiesFilePath);
        fileIn = new FileInputStream(propertiesFilePath);
        props.load(fileIn);
        System.getProperties().putAll(props);
    }

    public void loadLog4jProperty(String propertiesFilePath) throws Exception {
        log.info("log4j property file path: ");
        fileIn = new FileInputStream(propertiesFilePath);
        props.load(fileIn);
        PropertyConfigurator.configure(props);
    }

    public void ufUserException(boolean bExpectedBoolean, boolean ActualBoolean, String strException) throws Exception {
        if (bExpectedBoolean != ActualBoolean)
            throw new Exception(strException);
    }

    public String takeScreenShotWebReturnPath(WebDriver driver, String className) throws IOException {
        String sDestDir = "\\screenshots";
        String sImageName = System.getProperty("user.dir") + sDestDir + className + ".jpg";
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "jpg", new File(sImageName));
        return sImageName;
    }

    public void deleteScreenShotDirectory() {
        String destDir = "\\screenshots";
        String SRC_FOLDER = System.getProperty("user.dir") + destDir;
        File directory = new File(SRC_FOLDER);
        if (directory.exists()) {
            try {
                delete(directory);

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public void deleteCreateScreenShotDirectorySureFireReports() {
        String destDir = "\\screenshots";
        String SRC_FOLDER = System.getProperty("user.dir") + "\\target\\surefire-reports" + destDir;
        File directory = new File(SRC_FOLDER);
        if (directory.exists()) {
            try {
                delete(directory);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public void delete(File file) throws Exception {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
                log.info("Directory is deleted: " + file.getAbsolutePath());
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    delete(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                    log.info("Directory is deleted: " + file.getAbsolutePath());
                }
            }
        } else {
            file.delete();
            log.info("File is deleted " + file.getAbsolutePath());
        }
    }

    public String convertToSeconds(String sActualTime) {
        String sTotalSec = null;
        String[] strSplit = sActualTime.split(":");
        if (strSplit.length == 3) {
            int iHourToSec = Integer.parseInt(strSplit[0].toString()) * 60 * 60;
            int iMinuteToSec = Integer.parseInt(strSplit[1].toString()) * 60;
            double iSeconds = Double.parseDouble(strSplit[2].toString());
            double iTotalSec = iHourToSec + iMinuteToSec + iSeconds;
            sTotalSec = Double.toString(iTotalSec);
        }
        return sTotalSec;
    }

    public JSONObject readJsonFileGEtJsonObject(String sPathOfJson) throws IOException {
        String sJsonContent = readFileReturnInString(sPathOfJson);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(sJsonContent.trim());
        } catch (Exception pj) {
            try {
                jsonObject = new JSONObject(sPathOfJson.substring(sPathOfJson.indexOf("{")));
            } catch (Exception pja) {
                log.error("unable to parse " + pj);
            }
        }
        return jsonObject;
    }

    public JSONObject convertStringToJsonObject(String sJsonContent) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(sJsonContent.substring(sJsonContent.indexOf("{")));
        } catch (Exception pj) {
            try {
                jsonObject = new JSONObject(sJsonContent.trim());
            } catch (Exception pja) {
                log.error("Unable to parse " + pj);
            }
        }
        return jsonObject;
    }

    public String readFileReturnInString(String sPathOfJson) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(sPathOfJson));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public String unixTimeStampString() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

    public String unixToNormalTime(String unixTime) {
        long unixTime1 = Long.parseLong(unixTime);

        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM dd, yyyy");
        final String formattedDtm = Instant.ofEpochMilli(unixTime1).atZone(ZoneId.of("GMT+05:30")).format(formatter);
        return String.valueOf(formattedDtm);
    }

    public String generateDate(int dayCount) {
        log.info(LocalDate.now().plusDays(dayCount).toString());
        return LocalDate.now().plusDays(dayCount).toString();
    }

    public String getSystemP() {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
            log.info("System IP Address" + (localhost.getHostAddress().trim()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return (localhost.getHostAddress().trim());
    }

    public String cyWareDate(int days) {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, +days);
        Date s = c.getTime();
        String dateString = new SimpleDateFormat("yyyy-mm-dd").format(s);
        return dateString;
    }

    public String[] splitParamGetInArray(String property) {
        return property.split(",");
    }

    public Set<String> splitParamGetInSet(String property) {
        Set<String> set = new HashSet<>();
        for (String s : property.split(",")) {
            set.add(s);
        }
        return set;
    }

    public List<String> splitParamGetInList(String property) {
        List<String> list = new ArrayList<>();
        for (String s : property.split(",")) {
            list.add(s);
        }
        return list;
    }

    public String getCurrentDate() {
        Format formatter = new SimpleDateFormat("MMM dd, yyyy");
        String today = formatter.format(new Date());
        return today;
    }

    public void moveToElement(WebElement element) throws Exception {
        Actions act = new Actions(driverManager.getDriver());
        act.moveToElement(element);
        act.build().perform();

    }

    public boolean isExist(WebElement ele) {
        boolean present;
        try {
            ele.isEnabled();
            present = true;
        } catch (Exception e) {
            present = false;
        }
        return present;
    }

    public boolean isDisplayed(WebElement ele) {
        boolean displayed;
        try {
            ele.isDisplayed();
            displayed = true;
        } catch (Exception e) {
            displayed = false;
        }
        return displayed;
    }

    public int ConventMontToInteger(String MonthInWords) throws Exception {
        int month;
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);

        TemporalAccessor accessor = parser.parse(MonthInWords);
        month = accessor.get(ChronoField.MONTH_OF_YEAR);
        return month;
    }

    public Date Convert_String_To_Date(String sDate) throws Exception {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MMMM/dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MMM dd, yyyy");

        Date d1 = format1.parse(sDate);
        String d2 = format2.format(d1);
        Date d3 = format2.parse(d2);

        return d3;
    }

    public String getTheHost(String discoveryURL) throws Exception {
        log.info("Actual Discovery URL: " + discoveryURL);
        URL url = new URL(discoveryURL);
        discoveryURL = url.getProtocol() + "://" + url.getHost();
        log.info("After Trim Discovery URL: " + discoveryURL);
        return discoveryURL;
    }

    public String getOnlyHost(String discoveryURL) throws Exception {
        log.info("Actual discovery URL: " + discoveryURL);
        URL url = new URL(discoveryURL);
        log.info("After Trim Discovery URL for on ly host name: " + discoveryURL);
        return discoveryURL;
    }

    public Object[][] getTestData(String sheetName){
        FileInputStream fls = null;
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet;
        try {
            fls = new FileInputStream(new File(System.getProperty("user.dir")+"\\testdata\\DataForKayak.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = XSSFWorkbookFactory.createWorkbook(fls);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }
        return data;
    }
}
