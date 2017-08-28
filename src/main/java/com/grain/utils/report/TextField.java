package com.grain.utils.report;

/**
 * Created by IntelliJ IDEA.
 * 动态导出pdf或者excel调用
 * User: Anqi
 * Date: 2010-5-25
 * Time: 9:00:52
 * To change this template use File | Settings | File Templates.
 */
public class TextField {
    //common properties
    private String columnName;
    private String fieldName;
    private String pattern;//显示格式

    //excel properties
    private int xls_columnView;
    //pdf properties
    private float width;
    private float height;
    private float horizontalAlignment; //水平对齐方式
    private float verticalAlignment;   //垂直对齐方式
    private float pdf_columnWidth = 0.2f;

    private Object data;

    public TextField() {
    }

    public TextField(String fieldName) {
        this.fieldName = fieldName;
    }

    public TextField(String columnName, String fieldName) {
        this.columnName = columnName;
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(float horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public float getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(float verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public int getXls_columnView() {
        return xls_columnView;
    }

    public void setXls_columnView(int xls_columnView) {
        this.xls_columnView = xls_columnView;
    }

    public float getPdf_columnWidth() {
        return pdf_columnWidth;
    }

    public void setPdf_columnWidth(float pdf_columnWidth) {
        this.pdf_columnWidth = pdf_columnWidth;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

