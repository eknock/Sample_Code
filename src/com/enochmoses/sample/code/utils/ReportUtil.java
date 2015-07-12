/*
 * Created on Sep 2, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.enochmoses.sample.code.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * @author ENOCH MOSES
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ReportUtil implements Constants {
    private static Log log = LogFactory.getLog(ReportUtil.class);

    /**
     * Adds the formatting to an Excel spreadsheet cell with the supplied
     * cellColor and HSSFCellStyle. It also creates a thin black border around
     * the cell.
     * 
     * @param style
     *            HSSFCellStyle object needed for cell formating
     * @param cellColor
     *            The desired color of the cell which can be hexidecimal or the
     *            color name
     * @return HSSFCellStyle object which is applied to the cell
     */
    public static HSSFCellStyle cellStyle(HSSFCellStyle style, short cellColor) {

        style.setFillBackgroundColor(cellColor);
        style.setFillForegroundColor(cellColor);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);

        return style;
    }

    /**
     * Creates Excel spreadsheet cell formatting with the supplied information
     * 
     * @param style
     *            HSSFCellStyle object needed for cell formating
     * @param cellColor
     *            The desired color of the cell which can be hexidecimal or the
     *            color name
     * @param borderColor
     *            The desired color of the cell border which can be hexidecimal
     *            or the color name
     * @param borderStyle
     *            short value needed for cell border formatting
     * 
     * @return HSSFCellStyle object which is applied to the cell
     */
    public static HSSFCellStyle cellStyle2(HSSFCellStyle style,
            short cellColor, short borderColor, short borderStyle) {

        style.setFillBackgroundColor(cellColor);
        style.setFillForegroundColor(cellColor);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(borderStyle);
        style.setBottomBorderColor(borderColor);
        style.setBorderLeft(borderStyle);
        style.setLeftBorderColor(borderColor);
        style.setBorderRight(borderStyle);
        style.setRightBorderColor(borderColor);
        style.setBorderTop(borderStyle);
        style.setTopBorderColor(borderColor);

        return style;
    }

    /**
     * Creates the cell font formatting
     * 
     * @param workBook
     *            HSSFWorkbook object needed for cell font formating
     * @param color
     *            desired color of the font
     * @param font
     *            desired font
     * @return HSSFCellStyle object which is applied to the cell
     */
    public static HSSFCellStyle cellFormatStyle(HSSFWorkbook workBook,
            short color, HSSFFont font) {
        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle = ReportUtil.cellStyle(cellStyle, color);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * Creates cell formatting with includes font formatting
     * 
     * @param workBook
     *            HSSFWorkbook object needed for cell formating
     * @param color
     *            desired font color
     * @param font
     *            desired font
     * @param borderColor
     *            desired border color
     * @param borderStyle
     *            short value needed for cell border formatting
     * @return HSSFCellStyle object which is applied to the cell
     */
    public static HSSFCellStyle cellFormatStyle2(HSSFWorkbook workBook,
            short color, HSSFFont font, short borderColor, short borderStyle) {
        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle = ReportUtil.cellStyle2(cellStyle, color, borderColor,
                borderStyle);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * Creates a font descripter which includes what type of font, font size,
     * etc.
     * 
     * @param book
     *            HSSFWorkbook object needed for font formatting
     * @param fontHeight
     *            The desired font size. Normal font is usally 10 or 12
     * @param fontWeight
     *            An int value for the desired font weight which is usually
     *            "700" for bold and "100" for normal
     * @param fontName
     *            The desired font style like "Arial", "Roman Times", etc.
     * @return HSSFCellStyle object which is applied to the cell
     */
    public static HSSFFont fontDescripter(HSSFWorkbook book, int fontHeight,
            int fontWeight, String fontName) {
        HSSFFont newFont = book.createFont();
        newFont.setFontHeightInPoints((short) fontHeight);
        newFont.setBoldweight((short) fontWeight);
        newFont.setFontName(fontName);
        return newFont;
    }

    /**
     * Creates a font descripter with underline information
     * 
     * @param book
     *            HSSFWorkbook object needed for font formatting
     * @param fontHeight
     *            The desired font size. Normal font is usally 10 or 12
     * @param fontWeight
     *            An int value for the desired font weight which is usually
     *            "700" for bold and "100" for normal
     * @param fontName
     *            The desired font style like "Arial", "Roman Times", etc.
     * @param underlineSize
     *            The thickness of the underline. It is a byte value
     * @return HSSFCellStyle object which is applied to the cell
     */
    public static HSSFFont fontDescripterUnderline(HSSFWorkbook book,
            int fontHeight, int fontWeight, String fontName, byte underlineSize) {
        HSSFFont newFont = fontDescripter(book, fontHeight, fontWeight,
                fontName);
        newFont.setUnderline(underlineSize);
        return newFont;
    }

    /**
     * Creates the new string date from the supplied date and how many days need
     * added on that date
     * 
     * @param string
     *            The supplied date
     * @param x
     *            The number of days need to be added to the date
     * @return new string date
     * @throws ParseException
     */
    public static String stringDateString(String string, int x)
            throws ParseException {
        Date date = stringDate(string);
        date = DateUtil.addDays(date, x);
        SimpleDateFormat outFormatter = new SimpleDateFormat("MM/dd/yyyy");
        String outDate = outFormatter.format(date);
        log.debug("String out value: " + outDate);
        return outDate;
    }

    /**
     * Creates a date object for the supplied string date
     * 
     * @param string
     *            the supplied string date
     * @return the date object
     * @throws ParseException
     */
    public static Date stringDate(String string) throws ParseException {
        SimpleDateFormat inFormatter = new SimpleDateFormat(
                "EEE MMM dd hh:mm:ss z yyyy");
        Date date = inFormatter.parse(string);

        return date;
    }

    /**
     * Places a Double value in an Excel spreadsheet
     * 
     * @param row
     *            The row on which the target cell is
     * @param x
     *            The column on which the target cell is
     * @param value
     *            The Double value
     * @param style
     *            HSSFCellStyle which is applied to the cell
     * @return HSSFCell
     */

    public static HSSFCell cellFormatterDouble(HSSFRow row, int x,
            Double value, HSSFCellStyle style) {
        if (value == null || "".equals(value.toString())) {
            value = Double.valueOf("0");
        }
        row.createCell((short) x).setCellValue(value.doubleValue());
        HSSFCell cell = row.getCell((short) x);
        cell.setCellStyle(style);

        return cell;

    }

    /**
     * Places a Integer value in an Excel spreadsheet
     * 
     * @param row
     *            The row on which the target cell is
     * @param x
     *            The column on which the target cell is
     * @param value
     *            The Integer value
     * @param style
     *            HSSFCellStyle which is applied to the cell
     * @return HSSFCell
     */
    public static HSSFCell cellFormatterInteger(HSSFRow row, int x,
            Integer value, HSSFCellStyle style) {
        if (value == null || "".equals(value.toString())) {
            value = Integer.valueOf("0");
        }
        row.createCell((short) x).setCellValue(value.doubleValue());
        HSSFCell cell = row.getCell((short) x);
        cell.setCellStyle(style);

        return cell;

    }

    /**
     * Places a Long value in an Excel spreadsheet
     * 
     * @param row
     *            The row on which the target cell is
     * @param x
     *            The column on which the target cell is
     * @param value
     *            The Long value
     * @param style
     *            HSSFCellStyle which is applied to the cell
     * @return HSSFCell
     */
    public static HSSFCell cellFormatterLong(HSSFRow row, int x, Long value,
            HSSFCellStyle style) {
        if (value == null || "".equals(value.toString())) {
            value = Long.valueOf("0");
        }
        row.createCell((short) x).setCellValue(value.doubleValue());
        HSSFCell cell = row.getCell((short) x);
        cell.setCellStyle(style);

        return cell;

    }

    /**
     * Places a string in an Excel spreadsheet
     * 
     * @param row
     *            The row on which the target cell is
     * @param x
     *            The column on which the target cell is
     * @param value
     *            The string
     * @param style
     *            HSSFCellStyle which is applied to the cell
     * @return HSSFCell
     */
    public static HSSFCell cellFormatter(HSSFRow row, int x, String value,
            HSSFCellStyle style) {

        row.createCell((short) x).setCellValue(value);
        HSSFCell cell = row.getCell((short) x);
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * Creates a row an Excel worksheet
     * 
     * @param ws
     *            The supplied worksheet
     * @param x
     *            At what row number should the row be created
     * @return the row
     */
    public static HSSFRow rowCreate(HSSFSheet ws, int x) {
        HSSFRow row = ws.createRow((short) x);
        return row;
    }

    /**
     * Places a cell formula in a cell
     * 
     * @param sheet
     *            The worksheet where the cell is
     * @param x
     *            The row where the cell is
     * @param y
     *            The column where the cell is
     * @param formula
     *            The formula
     * @return the cell
     */
    public static HSSFCell cellFormula(HSSFSheet sheet, int x, int y,
            String formula, HSSFCellStyle cellStyle) {
        
    	HSSFRow row = sheet.getRow((short) x);
      
        HSSFCell cell = row.getCell((short) y);
        if(null == cell){
        	cell = row.createCell((short)y);
        	cell.setCellStyle(cellStyle);
        }
        cell.setCellFormula(formula);
        return cell;

    }

    /**
     * Streams the Excel file to the user's computer
     * 
     * @param res
     *            The supplied HttpServletResponse
     * @param file
     *            The location of the file
     * @throws IOException
     */
    public static void doPost(HttpServletResponse res,
            File file) throws IOException {
        String file_name = file.getName();
        res.setContentType(EXCEL_MIME_TYPE);
        res.setHeader("Content-Disposition", "attachment;filename=\""
                + file_name + "\"");
        log.debug("in doPost file_name: " + file_name);
        ServletOutputStream sos = null;
        BufferedInputStream bis = null;
        try {
            sos = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            byte buffer[] = new byte[2048];
            int c;
            while ((c = bis.read(buffer)) != -1) {
                sos.write(buffer, 0, c);
            }
        } catch (IOException ioe) {
            log.error(ioe);
            throw (ioe);
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (sos != null) {
                sos.close();
            }
        }
    }

}