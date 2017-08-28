package com.grain.utils.report;

/**
 * Created by IntelliJ IDEA.
 * User: Anqi
 * 动态产生报表使用
 * Date: 2010-8-21
 * Time: 17:44:38
 * To change this template use File | Settings | File Templates.
 */

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author An
 * @time 2009-8-6 下午07:47:44
 */
public class ReportTemplate {
    private static final Logger logger = Logger.getLogger(ReportTemplate.class);
    // 文档元素
    private String title = "";
    private String pageHeader = "";
    private String pageFooter = "";
    private List<?> columnHeader;
    private List<?> columnFooter;
    private List<?> detail;
    private String lastPageFooter = "";
    private String summary;
    private float[] relativeWidths;
    private List<?> fieldList;

    public List<?> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<?> fieldList) {
        this.fieldList = fieldList;
    }

    // 输出流
    private OutputStream outputStream;

    // 构造函数

    public ReportTemplate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setPageFooter("制表人：____________" + "制表时间：" + df.format(new Date()));
    }

    public float[] getRelativeWidths() {
        return relativeWidths;
    }

    public void setRelativeWidths(float[] relativeWidths) {
        this.relativeWidths = relativeWidths;
    }

    // getter方法和setter方法

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageHeader() {
        return pageHeader;
    }

    public void setPageHeader(String pageHeader) {
        this.pageHeader = pageHeader;
    }

    public String getPageFooter() {
        return pageFooter;
    }

    public void setPageFooter(String pageFooter) {
        this.pageFooter = pageFooter;
    }

    public List<?> getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(List<?> columnHeader) {
        this.columnHeader = columnHeader;
    }

    public List<?> getColumnFooter() {
        return columnFooter;
    }

    public void setColumnFooter(List<String> columnFooter) {
        this.columnFooter = columnFooter;
    }

    public List<?> getDetail() {
        return detail;
    }

    public void setDetail(List<?> detail) {
        this.detail = detail;
    }

    public String getLastPageFooter() {
        return lastPageFooter;
    }

    public void setLastPageFooter(String lastPageFooter) {
        this.lastPageFooter = lastPageFooter;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream out) {
        this.outputStream = out;
    }

    /**
     * 生成Excel文件
     */
    public void generateExcel() {
        logger.debug("导出Excel报表=============");
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        try {
            // 创建Excel文件、工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = workbook.createSheet(getTitle(), 0);
            // 设置字体格式
            WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 20,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            WritableFont contentFont = new WritableFont(WritableFont.ARIAL, 10,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            WritableCellFormat titleWCF = new WritableCellFormat(titleFont);
            WritableCellFormat headerWCF = new WritableCellFormat(contentFont);
            WritableCellFormat defaultContentWCF = new WritableCellFormat(contentFont);
            defaultContentWCF.setBorder(Border.ALL, BorderLineStyle.THIN);
            // 标题、页眉（首页）
            Label label = new Label(2, 0, getTitle(), titleWCF);
            sheet.addCell(label);
            label = new Label(0, 1, getPageHeader(), headerWCF);
            sheet.addCell(label);
            label = new Label(0, 2, getPageFooter(), headerWCF);
            sheet.addCell(label);
            // 主体内容
            if (getDetail() != null && getColumnHeader() != null) {
                sheet.setRowView(3, 400);
                for (int i = 0; i < getColumnHeader().size(); i++) {
                    sheet.setColumnView(i, 16);
                    label = new Label(i, 3, (String) getColumnHeader().get(i),
                            defaultContentWCF);
                    sheet.addCell(label);
                }
                WritableCellFormat[] contentWCF = new WritableCellFormat[fieldList
                        .size()];
                for (int i = 0; i < fieldList.size(); i++) {
                    if (getDetail().size() > 0) {
                        Object obj = getDetail().get(0);
                        if (obj instanceof Map) {
                            Map map = (Map) obj;
                            TextField field = (TextField) fieldList.get(i);
                            String fieldType = map.get(field.getFieldName())
                                    .getClass().getSimpleName();
                            if (fieldType.equalsIgnoreCase("int")
                                    || fieldType.equalsIgnoreCase("integer")
                                    || fieldType.equalsIgnoreCase("float")
                                    || fieldType.equalsIgnoreCase("double")) {
                                if (field.getPattern() != null) {
                                    NumberFormat numberFormat = new NumberFormat(
                                            field.getPattern());
                                    WritableCellFormat numberCellFormat = new WritableCellFormat(
                                            numberFormat);
                                    numberCellFormat.setBorder(Border.ALL,
                                            BorderLineStyle.THIN);
                                    numberCellFormat.setFont(contentFont);
                                    contentWCF[i] = numberCellFormat;
                                } else {
                                    WritableCellFormat numberCellFormat = new WritableCellFormat();
                                    numberCellFormat.setBorder(Border.ALL,
                                            BorderLineStyle.THIN);
                                    numberCellFormat.setFont(contentFont);
                                    contentWCF[i] = numberCellFormat;
                                }
                            } else {
                                contentWCF[i] = defaultContentWCF;
                            }
                        } else {
                            Class<?> aClass = obj.getClass();
                            TextField field = (TextField) fieldList.get(i);
                            String fieldType = aClass
                                    .getDeclaredField(field.getFieldName())
                                    .getType().getSimpleName();
                            if (fieldType.equalsIgnoreCase("int")
                                    || fieldType.equalsIgnoreCase("integer")
                                    || fieldType.equalsIgnoreCase("float")
                                    || fieldType.equalsIgnoreCase("double")) {
                                if (field.getPattern() != null) {
                                    NumberFormat numberFormat = new NumberFormat(
                                            field.getPattern());
                                    WritableCellFormat numberCellFormat = new WritableCellFormat(
                                            numberFormat);
                                    numberCellFormat.setBorder(Border.ALL,
                                            BorderLineStyle.THIN);
                                    numberCellFormat.setFont(contentFont);
                                    contentWCF[i] = numberCellFormat;
                                } else {
                                    WritableCellFormat numberCellFormat = new WritableCellFormat();
                                    numberCellFormat.setBorder(Border.ALL,
                                            BorderLineStyle.THIN);
                                    numberCellFormat.setFont(contentFont);
                                    contentWCF[i] = numberCellFormat;
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < getDetail().size(); i++) {
                    sheet.setRowView(i + 4, 400);
                    Object obj = getDetail().get(i);
                    if (obj instanceof Map) {
                        Map map = (Map) obj;
                        for (int j = 0; j < fieldList.size(); j++) {
                            sheet.setColumnView(j, 16);
                            TextField field = (TextField) fieldList.get(j);
                            Object fieldObj = map.get(field.getFieldName());
                            String fieldType = fieldObj.getClass()
                                    .getSimpleName();
                            if ((fieldType.equalsIgnoreCase("int")
                                    || fieldType.equalsIgnoreCase("integer")
                                    || fieldType.equalsIgnoreCase("float") || fieldType
                                    .equalsIgnoreCase("double"))) {
                                if (!fieldObj.equals(0)) {
                                    double data = new BigDecimal(
                                            fieldObj.toString()).doubleValue();
                                    jxl.write.Number numberLabel = new jxl.write.Number(
                                            j, i + 4, data, contentWCF[j]);
                                    sheet.addCell(numberLabel);
                                } else {
                                    label = new Label(j, i + 4, null,
                                            contentWCF[j]);
                                    sheet.addCell(label);
                                }
                            } else {
                                String fieldValue = null;
                                if (fieldType.equalsIgnoreCase("Date")) {
                                    fieldValue = fieldObj == null ? null
                                            : dateFormat.format(fieldObj);
                                } else {
                                    fieldValue = fieldObj == null ? null
                                            : fieldObj.toString();
                                }
                                label = new Label(j, i + 4, fieldValue,
                                        contentWCF[j]);
                                sheet.addCell(label);
                            }
                        }
                    } else {
                        Class<?> aClass = obj.getClass();
                        for (int j = 0; j < fieldList.size(); j++) {
                            sheet.setColumnView(j, 16);
                            TextField field = (TextField) fieldList.get(j);
                            Object fieldObj = ReflectUtil.getFieldValue(
                                    field.getFieldName(), obj);
                            String fieldType = aClass
                                    .getDeclaredField(field.getFieldName())
                                    .getType().getSimpleName();
                            if (fieldType.equalsIgnoreCase("int")
                                    || fieldType.equalsIgnoreCase("integer")
                                    || fieldType.equalsIgnoreCase("float")
                                    || fieldType.equalsIgnoreCase("double")) {
                                double data = new BigDecimal(
                                        fieldObj.toString()).doubleValue();
                                jxl.write.Number numberLabel = new jxl.write.Number(
                                        j, i + 4, data, contentWCF[j]);
                                sheet.addCell(numberLabel);
                            } else {
                                String fieldValue = null;
                                if (fieldType.equalsIgnoreCase("Date")) {
                                    fieldValue = fieldObj == null ? null
                                            : dateFormat.format(fieldObj);
                                } else {
                                    fieldValue = fieldObj == null ? null
                                            : fieldObj.toString();
                                }
                                label = new Label(j, i + 4, fieldValue,
                                        defaultContentWCF);
                                sheet.addCell(label);
                            }
                        }
                    }
                }
            }
            // 汇总语句
            if (getSummary() != null) {
                label = new Label(0, sheet.getRows(), getSummary(), headerWCF);
                sheet.addCell(label);
            }
            for (int i = 0; i < fieldList.size(); i++) {
                TextField field = (TextField) fieldList.get(i);
                if (field.getXls_columnView() > 0) {
                    sheet.setColumnView(i, field.getXls_columnView());
                }
            }
            workbook.write(); // 写入文件
            workbook.close();
            outputStream.close(); // 关闭流
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 生成Pdf文件
     */

    public void generatePdf() {
        logger.debug("导出PDF报表=============");
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Document document = new Document(PageSize.A3, 10, 10, 20, 170);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            // 中文支持
            BaseFont baseFont = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // 字体格式
            Font contentFont = new Font(baseFont, 10, Font.NORMAL);
            Font titleFont = new Font(baseFont, 16, Font.BOLD);
            Font headerFont = new Font(baseFont, 12, Font.BOLD);
            // 设置每页显示的页眉页脚,页码
            HeaderFooter footer = new HeaderFooter(new Phrase("页码： ",
                    contentFont), true);
            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(1);
            document.setFooter(footer);
            document.open();
            // 设置标题
            Paragraph titleParagragh = new Paragraph(getTitle(), titleFont);
            titleParagragh.setAlignment(1);
            document.add(titleParagragh);
            // 设置页眉（首页）
            Paragraph headerParagraph = new Paragraph(getPageHeader(),
                    headerFont);
            headerParagraph.setAlignment(1);
            document.add(headerParagraph);
            // 主体内容
            if (getDetail() != null && getColumnHeader() != null) {
                int size = fieldList.size();
                PdfPTable table = new PdfPTable(size);
                // 设置 table 单元格宽度，好像不起作用
                if (relativeWidths != null && relativeWidths.length == size)
                    table.setWidths(relativeWidths);
                // 生成表格内容
                table.setWidthPercentage(90);
                // 设置列名占1行
                table.setHeaderRows(1);
                for (int i = 0; i < getColumnHeader().size(); i++) {
                    Paragraph para = new Paragraph((String) getColumnHeader()
                            .get(i), contentFont);
                    para.setAlignment(PdfPCell.ALIGN_CENTER);
                    PdfPCell cell = new PdfPCell();
                    cell.addElement(para);
                    table.addCell(cell);
                }
                DecimalFormat[] decimalFormat = new DecimalFormat[fieldList
                        .size()];
                for (int i = 0; i < fieldList.size(); i++) {
                    if (getDetail().size() > 0) {
                        Object obj = getDetail().get(0);
                        Class<?> aClass = obj.getClass();
                        TextField field = (TextField) fieldList.get(i);
                        String fieldType = aClass
                                .getDeclaredField(field.getFieldName())
                                .getType().getSimpleName();
                        if (fieldType.equalsIgnoreCase("int")
                                || fieldType.equalsIgnoreCase("float")
                                || fieldType.equalsIgnoreCase("double")) {
                            if (field.getPattern() != null) {
                                decimalFormat[i] = new DecimalFormat(
                                        field.getPattern());
                            } else {
                                decimalFormat[i] = new DecimalFormat();
                            }
                        }
                    }
                }
                for (int i = 0; i < getDetail().size(); i++) {
                    Object obj = getDetail().get(i);
                    Class<?> aClass = obj.getClass();
                    for (int j = 0; j < fieldList.size(); j++) {
                        TextField field = (TextField) fieldList.get(j);
                        Object fieldObj = ReflectUtil.getFieldValue(
                                field.getFieldName(), obj);
                        String fieldType = aClass
                                .getDeclaredField(field.getFieldName())
                                .getType().getSimpleName();
                        if (fieldType.equalsIgnoreCase("int")
                                || fieldType.equalsIgnoreCase("float")
                                || fieldType.equalsIgnoreCase("double")) {
                            double data = new BigDecimal(fieldObj.toString())
                                    .doubleValue();
                            Paragraph para = new Paragraph(
                                    decimalFormat[j].format(data), contentFont);
                            para.setAlignment(PdfPCell.ALIGN_CENTER);
                            PdfPCell cell = new PdfPCell();
                            cell.addElement(para);
                            table.addCell(cell);
                        } else {
                            String fieldValue = null;
                            if (fieldType.equalsIgnoreCase("Date")) {
                                fieldValue = fieldObj == null ? null
                                        : dateFormat.format(fieldObj);
                            } else {
                                fieldValue = fieldObj == null ? null : fieldObj
                                        .toString();
                            }
                            Paragraph para = new Paragraph(fieldValue,
                                    contentFont);
                            para.setAlignment(PdfPCell.ALIGN_CENTER);
                            PdfPCell cell = new PdfPCell();
                            cell.addElement(para);
                            table.addCell(cell);
                        }
                    }
                }
                // 汇总语句放到table末行
                if (getSummary() != null) {
                    PdfPCell sumCell = new PdfPCell();
                    sumCell.setColspan(size);
                    Paragraph sumParagraph = new Paragraph(getSummary(),
                            headerFont);
                    sumParagraph.setAlignment(1);
                    sumCell.addElement(sumParagraph);
                    table.addCell(sumCell);
                }
                document.add(table);
                // 设置页脚（末页）
                Paragraph footerParagraph = new Paragraph(getPageFooter(),
                        headerFont);
                footerParagraph.setAlignment(1);
                document.add(footerParagraph);
                document.close();
                byteArrayOutputStream.writeTo(outputStream);
                outputStream.flush();
            }
        } catch (DocumentException de) {
            logger.error(de.getMessage(), de);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}