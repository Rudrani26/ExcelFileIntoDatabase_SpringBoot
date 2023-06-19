package com.example.ExcelFile.Helper;
import com.example.ExcelFile.Entity.Excel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ExcelHelper {

    static List<Excel> excelList = new ArrayList<>();
    static List<Excel> excelErrorList = new ArrayList<>();
    static List<String> valErrors = new ArrayList<>();

    //checks whether file is of Excel type
    public static boolean checkExcelFormat(MultipartFile file){

        String contentType = file.getContentType();

        if (contentType != null) {
            return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }
        return false;
    }

    //convert excel to list
    public static List<Excel> exceltoList(InputStream inputStream){

//        List<Excel> excelList = new ArrayList<>();
//        List<Excel> excelErrorList = new ArrayList<>();
//        List<String> valErrors = new ArrayList<>();


        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(3);

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {

                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cellID = 0;

                Excel excel = new Excel();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    //excel.setMobile(cell.getStringCellValue());
                    switch (cellID) {
                        case 0:
                            //excel.setId(String.valueOf((int) cell.getNumericCellValue()));
                            if (cell.getCellType() == CellType.STRING) {
                                excel.setId(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC){
                                excel.setId(String.valueOf((int) cell.getNumericCellValue()));
                            }
                            break;
                        case 1:
                            excel.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            excel.setPosition(cell.getStringCellValue());
                            break;
                        case 3:
                            excel.setBranch(cell.getStringCellValue());
                            break;
                        case 4:
                            excel.setYearClass(cell.getStringCellValue());
                            break;
                        case 5:
                            excel.setEmail(cell.getStringCellValue());
                            break;
                        case 6:
                            if (cell.getCellType() == CellType.STRING) {
                                excel.setMobile(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                excel.setMobile(String.valueOf((long) cell.getNumericCellValue()));
                            }
                            break;
                        default:
                            break;
                    }
                    cellID++;
                }
                // Performs manual validation on the Excel object
                List<String> validationErrors = validateExcelObject(excel);

                if (validationErrors.isEmpty()) {
                    excelList.add(excel);
                } else {
                    excelErrorList.add(excel);
                    valErrors.add("Row " + (rowNumber) + ": " + validationErrors.toString());
                }
                rowNumber++;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        getExcelErrorList();
        getValidationErrors();
        getTotalFails();

        return excelList;
    }

    public static Integer getTotalFails(){
        return excelErrorList.size();
    }

    public static List<String> getValidationErrors(){
        System.out.println("from get val " + valErrors);
        int valErrorslength = valErrors.size();
        System.out.println(valErrorslength);
        return valErrors;
    }

    public static List<Excel> getExcelErrorList() {
        System.out.println("from get error list" + excelErrorList);
        return excelErrorList;
    }

    public static List<String> validateExcelObject(Excel excel) {
        // ...

        List<String> validationErrors = new ArrayList<>();

        String Id = excel.getId();
        String name = excel.getName();
        String position = excel.getPosition();
        String branch = excel.getBranch();
        String mobile = excel.getMobile();
        String email = excel.getEmail();

        if (StringUtils.isNotEmpty(Id) && !Pattern.matches("\\d+", Id)) {
            validationErrors.add("Invalid format for Id"); // Include the row number in the error message
        }
        if (StringUtils.isNotEmpty(name) && !Pattern.matches("^[a-zA-Z\\s]+", name)) {
            validationErrors.add("Invalid format for Name"); // Include the row number in the error message
        }
        if (StringUtils.isNotEmpty(position) && !Pattern.matches("^[a-zA-Z\\s]+", position)) {
            validationErrors.add("Invalid format for Position"); // Include the row number in the error message
        }
        if (StringUtils.isNotEmpty(branch) && !Pattern.matches("^[a-zA-Z\\s]+", branch)) {
            validationErrors.add("Invalid format for Branch"); // Include the row number in the error message
        }
        if (StringUtils.isNotEmpty(mobile) && !Pattern.matches("^[7-9]\\d{9}$", mobile)) {
            validationErrors.add("Invalid format for Mobile Number"); // Include the row number in the error message
        }
        if (StringUtils.isNotEmpty(email) && !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            validationErrors.add("Invalid format for Email"); // Include the row number in the error message
        }

        return validationErrors;
    }

}


//without validation
//package com.example.ExcelFile.Helper;
//
//import com.example.ExcelFile.Entity.Excel;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class ExcelHelper {
//
//    //checks whether file is of Excel type
//    public static boolean checkExcelFormat(MultipartFile file){
//
//        String contentType = file.getContentType();
//
//        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    //convert excel to list
//    public static List<Excel> exceltoList(InputStream inputStream){
//
//        List<Excel> excelList = new ArrayList<>();
//
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//            XSSFSheet sheet = workbook.getSheet("Sheet1");
//
//            int rowNumber = 0;
//            Iterator<Row> iterator = sheet.iterator();
//            while(iterator.hasNext()){
//
//                Row row = iterator.next();
//                if(rowNumber==0){
//                    rowNumber++;
//                    continue;
//                }
//
//                Excel excel = new Excel();
//
//                int cellID=0;
//                Iterator<Cell> cells = row.iterator();
//
//                while(cells.hasNext()){
//                    Cell cell = cells.next();
//
//                    switch (cellID){
//                        case 0:
//                            excel.setId((int)cell.getNumericCellValue());
//                            break;
//                        case 1:
//                            excel.setName(cell.getStringCellValue());
//                            break;
//                        case 2:
//                            excel.setPosition(cell.getStringCellValue());
//                            break;
//                        case 3:
//                            excel.setBranch(cell.getStringCellValue());
//                            break;
//                        case 4:
//                            excel.setYearClass(cell.getStringCellValue());
//                            break;
//                        case 5:
//                            excel.setEmail(cell.getStringCellValue());
//                            break;
//                        case 6:
//                            excel.setMobile(Long.valueOf(cell.getStringCellValue()));
//                            break;
//                        default:
//                            break;
//                    }
//                    cellID++;
//                }
//
//                excelList.add(excel);
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return excelList;
//    }
//}
