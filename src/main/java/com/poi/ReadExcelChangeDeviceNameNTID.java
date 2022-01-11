package com.poi;

import com.poi.utility.ExcelParser;
import com.poi.utility.FileProcessing;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadExcelChangeDeviceNameNTID
{

    private static FileProcessing fileProcessing;
    private static ExcelParser excelParser;
    private static String cellValue = null;
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static List<String> aMachines = new ArrayList<String>();
    private static List<String> updateMachines = new ArrayList<String>();
    private static List<String> selectWhereInMachines = new ArrayList<String>();
    private static List<String> updateAMachineDetails = new ArrayList<String>();
    private static List<String> updateASocketTerms = new ArrayList<String>();
    private static List<String> updateANdcCassettes = new ArrayList<String>();
    private static List<String> updateANdcSolunsol = new ArrayList<String>();
    private static List<String> mLookup = new ArrayList<String>();
    private static List<String> deleteAMachinesOld = new ArrayList<String>();
    private static List<String> requestConfigs = new ArrayList<String>();
    private static List<String> openPorts = new ArrayList<String>();

    public static void main(String[] args)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateNow = simpleDateFormat.format(new Date());

        fileProcessing = new FileProcessing();
        excelParser = new ExcelParser();

        int userId = 1;

        try
        {

            FileInputStream file = new FileInputStream(new File("PerubahanDeviceIDTIDSignOnNameKalsel.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(1);
            System.out.println("sheet:" + sheet.getSheetName());



            //Iterate through each rows one by one

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();

            int getColNo = excelParser.getColumnName( sheet, "NO", 2 );
            int getColDeviceNameOld = excelParser.getColumnName( sheet, "DEVICE NAME OLD", 2 );
            int getColDeviceNameNew = excelParser.getColumnName( sheet, "DEVICE NAME NEW", 2 );
            int getColTIDOld = excelParser.getColumnName( sheet, "TID OLD", 2 );
            int getColTIDNew = excelParser.getColumnName( sheet, "TID NEW", 2 );

            while ( rowIterator.hasNext() )
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns

                Cell cellNo =  row.getCell( getColNo );
                Cell cellDeviceNameOld =  row.getCell( getColDeviceNameOld );
                Cell cellDeviceNameNew =  row.getCell( getColDeviceNameNew );
                Cell cellTIDOld =  row.getCell( getColTIDOld );
                Cell cellTIDNew =  row.getCell( getColTIDNew );

                String valNo  = ExcelParser.getCellValue( cellNo );
                String valDeviceNameOld  = ExcelParser.getCellValue( cellDeviceNameOld );
                String valDeviceNameNew  = ExcelParser.getCellValue( cellDeviceNameNew );
                String valTIDOld  = ExcelParser.getCellValue( cellTIDOld );
                String valTIDNew  = ExcelParser.getCellValue( cellTIDNew );


                if ( valDeviceNameOld.length() > 0 ) {
                    if ( !valDeviceNameOld.equalsIgnoreCase("") && !valDeviceNameOld.equalsIgnoreCase("-") ) {
                        //System.out.print("\'" + terminalId + "\t");
                        //System.out.print("\'" + terminalId.substring(terminalId.length()-5) + "\t");

                        //System.out.print("ATM" + terminalId.substring(terminalId.length()-5) + "\t");
                        updateMachines.add("UPDATE a_machine SET " +
                                " device_name =  '" + valDeviceNameNew + "', " +
                                " tid =  '" + valTIDNew + "', " +
                                " luno =  '" + valTIDNew.substring(5) + "', " +
                                " cfgid =  '" + valTIDNew.substring(4) + "' " +
                                " WHERE device_name= '" + valDeviceNameOld + "' ;");

                        selectWhereInMachines.add(valDeviceNameNew);

                        updateAMachineDetails.add("UPDATE a_machine_detail SET " +
                                " device_name =  '" + valDeviceNameNew + "'" +
                                " WHERE device_name= '" + valDeviceNameOld + "' ;");

                        updateANdcCassettes.add("UPDATE a_ndc_cassette SET " +
                                " device_name =  '" + valDeviceNameNew + "'" +
                                " WHERE device_name= '" + valDeviceNameOld + "' ;");

                        updateASocketTerms.add("UPDATE a_socket_term SET " +
                                " device_name =  '" + valDeviceNameNew + "'" +
                                " WHERE device_name= '" + valDeviceNameOld + "' ;");

                        updateANdcSolunsol.add("UPDATE a_ndc_solunsol SET " +
                                " device_name =  '" + valDeviceNameNew + "'" +
                                " WHERE device_name= '" + valDeviceNameOld + "' ;");

                        mLookup.add("UPDATE m_lookup SET " +
                                " lookup_name =  '" + valDeviceNameNew + "'" +
                                " WHERE lookup_name= '" + valDeviceNameOld + "' ;");

                        requestConfigs.add("atm request config " + valDeviceNameNew + "");

                        deleteAMachinesOld.add("DELETE FROM a_machine " +
                                " WHERE device_name= '" + valDeviceNameOld + "' ;");

                    }
                }

            }
            file.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        System.out.println("\n");
        System.out.println("a_machine_update");
        fileProcessing.writeToFile( updateMachines,"migrasi-devicename-tid/a_machine_update.sql");

        System.out.println("\n");
        System.out.println("a_machine_detail_update");
        fileProcessing.writeToFile( updateAMachineDetails,"migrasi-devicename-tid/a_machine_detail_update.sql");

        System.out.println("\n");
        System.out.println("a_ndc_cassette_update");
        fileProcessing.writeToFile( updateANdcCassettes,"migrasi-devicename-tid/a_ndc_cassette_update.sql");

        System.out.println("\n");
        System.out.println("a_socket_term_update");
        fileProcessing.writeToFile( updateASocketTerms,"migrasi-devicename-tid/a_socket_term_update.sql");

        System.out.println("\n");
        System.out.println("a_ndc_solunsol_update");
        fileProcessing.writeToFile( updateANdcSolunsol,"migrasi-devicename-tid/a_ndc_solunsol_update.sql");


        System.out.println("\n");
        System.out.println("m_lookup_update");
        fileProcessing.writeToFile( mLookup,"migrasi-devicename-tid/m_lookup_update.sql");

        System.out.println("\n");
        System.out.println("a_machine_old_delete");
        fileProcessing.writeToFile( deleteAMachinesOld,"migrasi-devicename-tid/a_machine_old_delete.sql");


        System.out.println("\n");
        System.out.println("command_request_config");
        fileProcessing.writeToFile( requestConfigs,"migrasi-devicename-tid/command_request_config.sql");


        StringBuilder queryStr = new StringBuilder("");
        queryStr.append( "SELECT * FROM a_machine WHERE device_name IN ( " );
        for (String x : selectWhereInMachines) {
            queryStr.append( "'" + x + "'," );
        }
        String commaseparatedlist = queryStr.toString();
        // Condition check to remove the last comma
        if (commaseparatedlist.length() > 0)
            commaseparatedlist
                    = commaseparatedlist.substring(
                    0, commaseparatedlist.length() - 1);

        String finalQuery = commaseparatedlist + " ); ";
        fileProcessing.writeToFile( finalQuery,"migrasi-devicename-tid/a_machine_where_in.sql");

    }





}
