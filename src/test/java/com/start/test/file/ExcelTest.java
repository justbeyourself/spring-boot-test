package com.start.test.file;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * @description: excel操作类
 * @author: zhanghuiyong
 * @create: 2019-12-23 15:13
 */
public class ExcelTest {

    private static Workbook wb;        //工作谱
    private static Sheet sheet;        //工作表
    private static Row row;            //工作行

    //读取的Excel文件路径
    private final static String path = "/Users/zhanghuiyong/Documents/上海电信-1小时必达/客户资料/1226/门店营维数据---宝山.xlsx";

    //读取Excels表格
    public static void readExcels(String filepath) {
        if (filepath == null) {
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException" + e);
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }


    /**
     * 读取Excel表格表头的内容
     *
     * @return String 表头内容的数组
     */
    public static String[] readExcelTitle() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);

        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = row.getCell(i).getStringCellValue();
        }
        return title;
    }

    public static List<String> getExcel() {
        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        System.out.println("rowNum:" + rowNum);
        List<String> addressList = new ArrayList<String>();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            Row r = sheet.getRow(i);
            if (!"宝山局".equals(r.getCell(0).getStringCellValue())) {
                break;
            }
            int cellNum = r.getLastCellNum();
            String cellParam = "";
            for (int j = 0; j < cellNum; j++) {

                cellParam += getCellValueByCell(r.getCell(j));
                if (j < cellNum - 1) {
                    cellParam += ",";
                }
            }
            System.out.println(cellParam);
        }
        return addressList;
    }


    public static void main(String[] args) {
        readExcels(path);
        try {
            getExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取单元格各类型值，返回字符串类型
    private static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        CellType cellType = cell.getCellType();

        switch (cellType) {
            case STRING: //字符串类型
                cellValue = cell.getStringCellValue().trim();
                cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case NUMERIC: //数值类型
                cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                break;
            default: //其它类型，取空串吧
                cellValue = "";
                break;
        }
        return cellValue;
    }
}
