package utils;

import TestAnnotation.TestInfo;
import framework_constants.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
public final class ExcelFileManager {

    private static String sheetName ;
    private ExcelFileManager() {}

//    @TestInfo(testcaseID = "Tc1", testCaseName = "test1")
//    @Test()
//    public void test1() {
//        /* String un, String pwd as parameter
//        System.out.println(un + " "+ pwd);*/
//        Map<String, String> map = setASheetName("LoginData").getData("TC_01");
//        System.out.println(map.get("UN")+ " " + map.get("PWD"));
//        System.out.println(map.get("TestCaseName"));
//        System.out.println(map.toString());
//    }
//
//    @Test()
//    public void test2() throws IOException {
//        /* String un, String pwd as parameter
//        System.out.println(un + " "+ pwd);*/
//        List<Map<String, String>>map = getDataList();
//        for (Map<String, String> mapped: map) {
//            System.out.println(mapped.get("TestCaseName"));
//        }
//    }

    public static ExcelFileManager setASheetName(String sheet) {
        sheetName = sheet;
        return new ExcelFileManager();
    }

//    @DataProvider
    public Object[][] getData1() throws IOException {
        /* String un, String pwd as parameter*/
        FileInputStream fileInputStream = new FileInputStream(new File(FilePath.EXCEL_DATA));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("LoginData");

        Object[][] arr = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i<sheet.getLastRowNum(); i++) {
            for(int j = 0; j< sheet.getRow(0).getLastCellNum();j++){
                arr[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
            }
        }
        return arr;
    }

    public Map<String, String> getData(String testcaseId) {
        XSSFSheet sheet = null;
        try(FileInputStream fileInputStream = new FileInputStream(new File(FilePath.EXCEL_DATA));
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            log.error("Faced an exception " + e.getMessage());
        }

        int rowNumber;
        int columnNumber;
        if (Objects.nonNull(sheet)) {
            rowNumber = sheet.getLastRowNum();
            columnNumber = sheet.getRow(0).getLastCellNum();
        } else {
            throw new RuntimeException("Sheet was null");
        }
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;

        for (int i = 0; i < rowNumber; i++) {
            map = new HashMap<>();
            for (int j = 0; j < columnNumber; j++) {
                String value = (null == sheet.getRow(i + 1).getCell(j).getStringCellValue()) ? "NullValue" : sheet.getRow(i + 1).getCell(j).getStringCellValue();
                String key = (null == sheet.getRow(0).getCell(j).getStringCellValue()) ? "NewColumnAsNullKey" + Math.random() : sheet.getRow(0).getCell(j).getStringCellValue();
                map.put(key, value);
            }
            list.add(map);
        }

        return findMapByKeyValue(list, testcaseId);
    }

//    @DataProvider
//    public Map<String, String> getData(String testcaseId) {
//        FileInputStream fileInputStream;
//        XSSFWorkbook workbook;
//        XSSFSheet sheet = null;
//        try {
//            fileInputStream = new FileInputStream(new File(FilePath.EXCEL_DATA));
//            workbook = new XSSFWorkbook(fileInputStream);
//            sheet = workbook.getSheet(sheetName);
//        } catch (Exception e) {
//            log.error("Faced an exception " + e);
//        }
//
//        int rowNumber;
//        int columnNumber;
//        if (Objects.nonNull(sheet)) {
//            rowNumber = sheet.getLastRowNum();
//            columnNumber = sheet.getRow(0).getLastCellNum();
//        } else {
//            throw new RuntimeException("Sheet was null");
//        }
////        Object[] arr = new Object[rowNumber];
//        List<Map<String, String>> list = new ArrayList<>();
//        Map<String, String> map = null;
//
//        for (int i = 0; i < rowNumber; i++) {
//            map = new HashMap<>();
//            for (int j = 0; j < columnNumber; j++) {
//                String value = (null == sheet.getRow(i + 1).getCell(j).getStringCellValue()) ? "NullValue" : sheet.getRow(i + 1).getCell(j).getStringCellValue();
//                String key = (null == sheet.getRow(0).getCell(j).getStringCellValue()) ? "NewColumnAsNullKey" + Math.random() : sheet.getRow(0).getCell(j).getStringCellValue();
//                map.put(key, value);
//            }
//            list.add(map);
//        }
//
//        return findMapByKeyValue(list, testcaseId);
//    }

    private static Map<String, String> findMapByKeyValue(List<Map<String, String>> mapList, String value) {
        for (Map<String, String> map : mapList) {
            if (map.containsKey("TC_ID") && map.get("TC_ID").equals(value)) {
                return map;
            }
        }
        return null; // Map not found
    }


    public  List<Map<String, String>> getDataList()  {
        FileInputStream fileInputStream;
        XSSFWorkbook workbook = null;
        try {
            fileInputStream = new FileInputStream(new File(FilePath.EXCEL_DATA));
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            log.info("The faced exception"+ e);
        }
        XSSFSheet sheet;
        if (Objects.nonNull(sheetName) | Objects.nonNull(workbook))
            sheet = workbook.getSheet(sheetName);
        else
            throw new RuntimeException("Sheet name or workbook is null");

        int rowNumber = sheet.getLastRowNum();
        int columnNumber = sheet.getRow(0).getLastCellNum();

//        Object[] arr = new Object[rowNumber];
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;

        for (int i = 0; i< rowNumber; i++){
            map = new HashMap<>();
            for (int j = 0; j< columnNumber; j++) {
                String key = (null == sheet.getRow(0).getCell(j).getStringCellValue()) ? "NewColumnAsNullKey"+Math.random():sheet.getRow(0).getCell(j).getStringCellValue();
                String value = (null == sheet.getRow(i+1).getCell(j).getStringCellValue())? "NullValue": sheet.getRow(i+1).getCell(j).getStringCellValue();
                map.put(key, value);
//                arr[i] = map;
//                list.add(map);
            }
            list.add(map);
        }
        return list;
    }

}
