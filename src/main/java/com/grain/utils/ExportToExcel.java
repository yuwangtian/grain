package com.grain.utils;


import com.grain.sysconfig.sys.bo.ArchDictionary;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class ExportToExcel<T> {

    CellStyle titleStyle, headStyle, dataStyle;


    public void export(HttpServletResponse response, String title, Map headers, Collection datas, List<ArchDictionary> archDictionaryList) {
        exportExcel(response, title, headers, datas, archDictionaryList);
    }


    private void exportExcel(HttpServletResponse response, String tile, Map<String, Object> headMap,
                             Collection<T> dataset, List<ArchDictionary> archDictionaryList) {

        Workbook workBook = new HSSFWorkbook();

        CellStyle headStyle = getHeadStyle(workBook);
        Sheet sheet = workBook.createSheet();
        // 默认列宽15
        sheet.setDefaultColumnWidth(15);
        int rowCnt = sheet.getLastRowNum();
        // 标题行
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(24);
        int colCnt = headMap.keySet().size();
        for (int i = 0; i < colCnt; i++) {
            Cell titleCell = titleRow.createCell(0);
        }
        titleRow.getCell(0).setCellStyle(headStyle);
        titleRow.getCell(0).setCellValue(tile);

        // 创建表头行
        Row headRow = sheet.createRow(rowCnt + 1);// 标题行
        int columnIndex = 0;
        for (String headCode : headMap.keySet()) {
            Object head = headMap.get(headCode);
            String headerName;
            if (head instanceof String) {
                headerName = (String) head;
            } else {
                throw new RuntimeException("无法处理的表头类型：" + head.getClass().getName());
            }
            Cell cell = headRow.createCell(columnIndex);
            cell.setCellValue(headerName);
            cell.setCellStyle(headStyle);
            columnIndex++;
        }
        // 创建数据行
        Iterator<T> iterator = dataset.iterator();
        int index = sheet.getLastRowNum();
        while (iterator.hasNext()) {
            index++;
            T t = iterator.next();
            Row row = sheet.createRow(index);
            if (t instanceof Map) {
                Map tMap = (Map) t;
//                setRowValue(row, headMap, tMap, archDictionaryList);
            } else {
                setRowValue(row, headMap, t);
            }
        }
        // 设置自动列宽
        for (int i = 0; i < colCnt; i++) {
            sheet.autoSizeColumn(i);
            int width = sheet.getColumnWidth(i);
//            double width = SheetUtil.getColumnWidth(sheet, i, false);
            width *= 2;
            int maxColumnWidth = 255 * 256;
            if (width > maxColumnWidth) {
                width = maxColumnWidth;
            }
            sheet.setColumnWidth(i, width);
        }

        // 合并标题行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colCnt - 1));
        OutputStream out;
        try {
            out = response.getOutputStream();
            workBook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 将javabean 设置到行
     *
     * @param row
     * @param bean
     */
    private void setRowValue(Row row, Map<String, Object> headMap, T bean) {
        Class<? extends Object> clazz = bean.getClass();
        int columnIndex = 0;
        CellStyle cellStyle = getDataCellStyle(row.getSheet().getWorkbook());
        for (String field : headMap.keySet()) {

            Cell cell = row.createCell(columnIndex);
            cell.setCellStyle(cellStyle);
            String getMethod = "get" + field.substring(0, 1).toUpperCase()
                    + field.substring(1);
            try {
                Method method = clazz.getMethod(getMethod);
                Object value = method.invoke(bean); // 获取值
                if ("serviced_money".equals(field)) {
                    if (null != value) {
                        value = "外单";
                    } else {
                        value = "内单";
                    }
                }
                if ("report_day".equals(field)) {
                    Date date = (Date) value;
                    value = CalendarUtil.dateToString(date);
                }

                if ("service_day_flag".equals(field)) {
                    if (null != value && new Integer(1).equals(value)) {
                        value = "否";
                    } else {
                        value = "是";
                    }
                }
                if ("product_id".equals(field)) {
                    if (new Integer(6194).equals(value)) {
                        value = "钻展产品";
                    } else if (new Integer(6193).equals(value)) {
                        value = "ued产品";
                    } else if (new Integer(6195).equals(value)) {
                        value = "运营产品";
                    } else if (new Integer(6201).equals(value)) {
                        value = "SEM产品";
                    } else if (new Integer(6202).equals(value)) {
                        value = "客服产品";
                    } else if (new Integer(6203).equals(value)) {
                        value = "摄影产品";
                    } else if (new Integer(6210).equals(value)) {
                        value = "培训产品";
                    } else if (new Integer(6211).equals(value)) {
                        value = "设计产品";
                    }
                }

                if (null == value) {
                    continue;
                }
                String dataValue = getColumValue(field, headMap, value);
                cell.setCellValue(dataValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                String msg = e.getTargetException().getMessage();
                throw new RuntimeException(msg);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                continue;
                //throw new RuntimeException(e.getMessage());
            } finally {
                columnIndex++;
            }
        }
    }

    private String matchDictionarys(List<ArchDictionary> allArchDictionaryList, String enName, Object value) {
        if (enName == null || "".equals(value)) {
            return "";
        }
        for (ArchDictionary dicItem : allArchDictionaryList) {
            if ("INNER_FLAG".equals(enName)) {
                enName = "ORDER_" + enName;
            }
            if ("SHOP_SOURCE".equals(enName)) {
                enName = "ORDER_SERVICE_PLAT";
            }
            if ("SHOP_RANK".equals(enName)) {
                enName = "ORFER_TAOBAO_RANK";
            }
            if (enName.equals(dicItem.getEn_name())) {
                if ((Integer.parseInt(value.toString()) == (Integer.parseInt(dicItem.getCode_value())))) {
                    return dicItem.getCode_name();
                }
            }
        }
        return value.toString();
    }


    /**
     * 获取表头样式
     *
     * @param workBook
     * @return
     */
    private CellStyle getHeadStyle(Workbook workBook) {
        headStyle = workBook.createCellStyle();

        headStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        headStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headStyle.setBorderRight(CellStyle.BORDER_THIN);
        headStyle.setBorderTop(CellStyle.BORDER_THIN);
        headStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // 表头字体
        Font headFont = workBook.createFont();
        headFont.setFontName("黑体"); //字体
        headFont.setColor(HSSFColor.WHITE.index);
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBoldweight(Font.BOLDWEIGHT_BOLD); //宽度
        headStyle.setFont(headFont);

        return headStyle;
    }

    /**
     * 获取数据样式
     *
     * @param workBook
     * @return
     */
    private CellStyle getDataCellStyle(Workbook workBook) {
        if (null != dataStyle) {
            return dataStyle;
        }
        CellStyle style = workBook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_LEFT);

        Font dataFont = workBook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataFont.setCharSet(XSSFFont.SYMBOL_CHARSET);
        dataFont.setFontName("宋体"); //字体

        dataFont.setBoldweight(Font.BOLDWEIGHT_BOLD); //宽度
        headStyle.setFont(dataFont);
        dataStyle = style;
        return style;
    }

    /**
     * 根据列配置获取列值
     *
     * @param field
     * @param headMap
     * @param value
     * @return
     */
    private String getColumValue(String field, Map headMap, Object value) {
        String dataValue = "";
        if (null == value) {
            return dataValue;
        }

        if (value instanceof byte[]) {
            //TODO 图片的处理
            // value = (byte[]) value;
            // row.setHeightInPoints(60);
            // cell.setCellValue(value);
        } else {
            dataValue = String.valueOf(value);
        }
        //表头信息
        /*Object head = headMap.get(field);
        if (head instanceof ExpColumn) {
            //若表头是列信息，根据该字段详细信息获取该字段导出值
            ExpColumn column = (ExpColumn) head;
            dataValue = getValueFromDict(column, dataValue);
        }*/
        return dataValue;
    }


    public static void main(String args[]) {
        ExportToExcel<Map<String, String>> exp = new ExportToExcel<Map<String, String>>();


//        WorkOrderBo workOrderBo = new WorkOrderBo();
//        workOrderBo.setCompany_id(23);
//        workOrderBo.setOrder_id("11111");
//        workOrderBo.setCurr_pro_short_name("ceshi");
//        workOrderBo.setCompany_name("洪海");

        List<Object> workOrderBos = new ArrayList<Object>();
        workOrderBos.add(new Object());
        Map headers = new HashMap();
        headers.put("company_id", "公司ID");
        headers.put("order_id", "订单编号");
        headers.put("curr_pro_short_name", "流程名称");
        headers.put("company_name", "公司名称");
        // new String[]{"supId", "supName", "supCode", "address"};
        String title = "订单列表";

        try {
            OutputStream out = new FileOutputStream("E:\\a.xls");
//            exp.export(title, headers, workOrderBos, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("导出完成");
    }


}
