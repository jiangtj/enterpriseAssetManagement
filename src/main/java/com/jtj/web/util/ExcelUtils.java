package com.jtj.web.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ExcelUtils {

    public static Workbook export2Excel(List<Map<String, Object>> dataList,
                                        Map<String, String> columnNameMap) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("导出信息");

        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        short width = (short) (35.7 * 150);
        for (int i = 0; i < columnNameMap.size(); i++) {
            sheet.setColumnWidth(i, width);
        }
        // 创建第一行
        Row row = sheet.createRow(0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        // DataFormat df = wb.createDataFormat();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.RED.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        // cs.setDataFormat(df.getFormat("#,##0.0"));

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        // cs2.setDataFormat(df.getFormat("text"));

        // 创建列（每行里的单元格）
        Set<Entry<String, String>> set = columnNameMap.entrySet();
        Iterator<Entry<String, String>> ite = set.iterator();
        Cell cell = null;
        int j = 0;// 列序号，从0开始
        while (ite.hasNext()) {
            Entry<String, String> entry = ite.next();
            String key = entry.getKey();
            cell = row.createCell(j++);
            cell.setCellValue(columnNameMap.get(key));// 列名
            cell.setCellStyle(cs);
        }
        for (short i = 0; i < dataList.size(); i++) {

            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            row = sheet.createRow((short) i + 1);

            //原
            // 在row行上创建一个方格
            /*Map<String, Object> dataMap = dataList.get(i);
            Set<Entry<String, Object>> dataSet = dataMap.entrySet();
            Iterator<Entry<String, Object>> dataIte = dataSet.iterator();
            short k = 0;
            while (dataIte.hasNext()) {
                Entry<String, Object> dateEntry = dataIte.next();
                String dateKey = dateEntry.getKey();
                cell = row.createCell(k++);
                cell.setCellValue(dataMap.get(dateKey) == null ? "" : dataMap.get(dateKey)
                    .toString());
                cell.setCellStyle(cs2);
            }*/

            //修改
            Map<String, Object> dataMap = dataList.get(i);
            Set<Entry<String, String>> dataSet = columnNameMap.entrySet();
            Iterator<Entry<String, String>> dataIte = dataSet.iterator();
            short k = 0;
            while (dataIte.hasNext()) {
                Entry<String, String> dateEntry = dataIte.next();
                String dateKey = dateEntry.getKey();
                cell = row.createCell(k++);
                cell.setCellValue(dataMap.get(dateKey) == null ? "" : dataMap.get(dateKey)
                        .toString());
                cell.setCellStyle(cs2);
            }

        }
        return wb;
    }
}
