package com.poi;

import com.poi.models.Issuer;
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

public class ReadExcelBinAtmb
{

    private static FileProcessing fileProcessing;
    private static ExcelParser excelParser;
    private static String cellValue = null;
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static List<String> mIssuer = new ArrayList<String>();
    private static List<String> mIssuerNpg = new ArrayList<String>();


    public static void main(String[] args)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateNow = simpleDateFormat.format(new Date());

        fileProcessing = new FileProcessing();
        excelParser = new ExcelParser();

        int userId = 1;

        String network = "200";
        String paticipantCode = "200";
        String defaultNetwork = "N";

        String networkNpg = "250";
        String paticipantCodeNpg = "250";
        String defaultNetworkNpg = "N";

        String combineBIN = "";
        String tempBankCode = "";
        String tempBankName = "";

        List<String> binList = new ArrayList<String>();
        Issuer issuer = new Issuer();
        List<Issuer> issuerList = new ArrayList<Issuer>();

        String valBin  = "";
        String valPan  = "";
        String valTrack  = "";
        String valBankCode  = "";
        String valBankName  = "";

        try
        {

            FileInputStream file = new FileInputStream(new File("BinJalinAtmb.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(1);
            System.out.println("sheet:" + sheet);
           // int getColTerminalIdIndexOld = excelParser.getColumnName(sheet, "ID TERMINAL ATM");
            int getColBin = excelParser.getColumnName(sheet, "BIN", 3);
            int getColPan = excelParser.getColumnName(sheet, "PAN", 3);
            int getColBankName = excelParser.getColumnName(sheet, "Bank", 3);
            int getColBankCode = excelParser.getColumnName(sheet, "Kode", 3);

            //Iterate through each rows one by one

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();



            while (rowIterator.hasNext())
            {

                Row row = rowIterator.next();
                //For each row, iterate through all the columns

                Cell cellBin =  row.getCell(getColBin);
                Cell cellPan =  row.getCell(getColPan);
                Cell cellBankName =  row.getCell(getColBankName);
                Cell cellBankCode =  row.getCell(getColBankCode);

                valBin  = ExcelParser.getCellValue(cellBin).replaceAll("\\s+","");
                valPan  = ExcelParser.getCellValue(cellPan);
                valBankCode  = ExcelParser.getCellValue(cellBankCode);
                valBankName  = ExcelParser.getCellValue(cellBankName);


                if (tempBankCode.equalsIgnoreCase("")){
                    tempBankCode = valBankCode;
                    tempBankName = valBankName;
                }
                System.out.println(tempBankCode + "==" + valBankCode);

                if (tempBankCode.equalsIgnoreCase(valBankCode)){
                    binList.add(valBin);

                }else{


                    String issurtCodeList = binList.toString();
                    String binIssuerData = issurtCodeList.substring(1, issurtCodeList.length() - 1).replace(", ", ",");

                    issuer.setListBin(binList);
                    issuer.setValIssuerCode(binIssuerData);
                    issuer.setValBankCode(tempBankCode);
                    issuer.setValBankName(tempBankName);
                    issuer.setValModuleName("igate.core");
                    issuer.setValDescription(tempBankName);
                    issuerList.add(issuer);




                    issuer = new Issuer();
                    binList = new ArrayList<String>();
                    tempBankCode = valBankCode;
                    tempBankName = valBankName;
                    binList.add(valBin);


                }

            }

            String issurtCodeList = binList.toString();
            String binIssuerData = issurtCodeList.substring(1, issurtCodeList.length() - 1).replace(", ", ",");
            issuer.setListBin(binList);
            issuer.setValIssuerCode(binIssuerData);
            issuer.setValBankCode(tempBankCode);
            issuer.setValBankName(tempBankName);
            issuer.setValModuleName("igate.core");
            issuer.setValDescription(tempBankName);
            issuerList.add(issuer);


            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        System.out.println("\n");
        System.out.println("m_issuer");
        for (int i = 0; i <issuerList.size() ; i++) {

            mIssuer.add("INSERT INTO m_issuer" +
                    " ( " +
                    " created_by, " + " created_date, " +
                    " updated_by, " + " updated_date, " +
                    " module_name, "  +
                    " participant_code, " + " issuer_code, " +
                    " network_default, " + " network, " +
                    " bank_code, " + " bank_name, " +
                    " description " +
                    " ) " +
                    " VALUES (" +
                    userId + ",'" + dateNow + "'," +
                    userId + ",'" + dateNow + "'," +
                    "'" + issuerList.get(i).getValModuleName() + "', " +
                    "'" + paticipantCode + "', " + "'" + issuerList.get(i).getValIssuerCode() + "', " +
                    "'" + defaultNetwork + "', " + "'" + network + "', " +
                    "'" + issuerList.get(i).getValBankCode() + "', " + "'" + issuerList.get(i).getValBankName() + "', " +
                    "" + "'" + issuerList.get(i).getValDescription() + "'" +
                    ");");
        }

        for (int i = 0; i <issuerList.size() ; i++) {

            mIssuerNpg.add("INSERT INTO m_issuer" +
                    " ( " +
                    " created_by, " + " created_date, " +
                    " updated_by, " + " updated_date, " +
                    " module_name, "  +
                    " participant_code, " + " issuer_code, " +
                    " network_default, " + " network, " +
                    " bank_code, " + " bank_name, " +
                    " description " +
                    " ) " +
                    " VALUES (" +
                    userId + ",'" + dateNow + "'," +
                    userId + ",'" + dateNow + "'," +
                    "'" + issuerList.get(i).getValModuleName() + "', " +
                    "'" + paticipantCodeNpg + "', " + "'" + issuerList.get(i).getValIssuerCode() + "', " +
                    "'" + defaultNetworkNpg + "', " + "'" + networkNpg + "', " +
                    "'" + issuerList.get(i).getValBankCode() + "', " + "'" + issuerList.get(i).getValBankName() + "', " +
                    "" + "'" + issuerList.get(i).getValDescription() + "'" +
                    ");");
        }

        fileProcessing.writeToFile( mIssuer,"m_issuer_atmb.sql");
        fileProcessing.writeToFile( mIssuerNpg,"m_issuer_atmb_npg.sql");


    }





}
