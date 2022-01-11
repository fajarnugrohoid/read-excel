package com.poi.utility;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.Iterator;

public class ExcelParser {

    private String cellValue = null;

    public ExcelParser() {
    }

    public static String getCellValue(Cell cell){

        String cellValue = "";

        if (cell != null) {

            //Check the cell type and format accordingly
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cellValue = String.valueOf((long) cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
            }
        }
        return cellValue;
    }

    public static int getColumnName(XSSFSheet sheet, String columnName, int rowNumber){
        XSSFRow xssfRow = sheet.getRow(rowNumber); //default before 3

        int colIndex = 0;
        int i = 0;
        Iterator<Cell> cellIterator = xssfRow.cellIterator();

        while (cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            String cellValue = "";
            //Check the cell type and format accordingly
            switch (cell.getCellType())
            {
                case Cell.CELL_TYPE_NUMERIC:
                    //System.out.print(cell.getNumericCellValue() + "\t");
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    //System.out.print(cell.getStringCellValue() + "\t");
                    cellValue = cell.getStringCellValue();
                    break;
            }

            if ( cellValue.equalsIgnoreCase( columnName ) ){
                colIndex = i;
                return  colIndex;
            }

            i++;
        }
        System.out.println("");
        return -1;
    }

    public String readByColumnName( XSSFSheet sheet, String ColumnName, int rowNum)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        try {
            Row row = sheet.getRow(5);

            // it will give you count of row which is used or filled
            short lastcolumnused = row.getLastCellNum();

            int colnum = 0;

            for (int i = 0; i < lastcolumnused; i++) {
                System.out.println( i + " getStringCellValue: " + row.getCell(i).getStringCellValue());

                //Check the cell type and format accordingly
                switch (row.getCell(i).getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        //System.out.print(column.getNumericCellValue() + "\t");
                        cellValue = String.valueOf(row.getCell(i).getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        //System.out.print(column.getStringCellValue() + "\t");
                        cellValue = row.getCell(i).getStringCellValue();
                        break;
                }

                if (cellValue.equalsIgnoreCase(ColumnName)) {
                    colnum = i;
                    break;
                }
            }
            row = sheet.getRow(rowNum);
            Cell column = row.getCell(colnum);


            //Check the cell type and format accordingly
            switch (column.getCellType())
            {
                case Cell.CELL_TYPE_NUMERIC:
                    //System.out.print(column.getNumericCellValue() + "\t");
                    cellValue = String.valueOf(column.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    //System.out.print(column.getStringCellValue() + "\t");
                    cellValue = column.getStringCellValue();
                    break;
            }


        }catch(Exception e){
            System.out.println(e);
        }
        return cellValue;
    }

}
