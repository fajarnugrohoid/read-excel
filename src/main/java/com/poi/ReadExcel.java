package com.poi;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.poi.utility.ExcelParser;
import com.poi.utility.FileProcessing;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel
{

    private static FileProcessing fileProcessing;
    private static ExcelParser excelParser;
    private static String cellValue = null;
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static List<String> aMachines = new ArrayList<String>();
    private static List<String> updateMachines = new ArrayList<String>();
    private static List<String> aMachineDetails = new ArrayList<String>();
    private static List<String> aSocketTerms = new ArrayList<String>();
    private static List<String> aNdcCassettes = new ArrayList<String>();
    private static List<String> mLookUp = new ArrayList<String>();
    private static List<String> openPorts = new ArrayList<String>();

    private static String tmkSingleDes = "8C3935B8714CB36F";
    private static String tmkTripleDesA = "U0679ADA5AD9F9C13EF744EDFF005D54F";
    private static String tmkTripleDesB = "U62C654B2FEFAEE044FCIF05BE0ACEDD8";
    private static String kcvSingleDes = "8235DA";
    private static String kcvTripleDesA = "E0EA1B";
    private static String kcvTripleDesB = "F9FCFB";


    public static void main(String[] args)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateNow = simpleDateFormat.format(new Date());

        fileProcessing = new FileProcessing();
        excelParser = new ExcelParser();

        int userId = 1;

        try
        {

            FileInputStream file = new FileInputStream(new File("Book1.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            int getColTerminalIdIndexOld = excelParser.getColumnName(sheet, "ID TERMINAL ATM", 3);
            int getColTerminalIdIndexNew = excelParser.getColumnName(sheet, "ID TERMINAL BARU", 3);
            int getColTerminalNameIndex = excelParser.getColumnName(sheet, "NAMA ATM", 3);
            int getColBranchCode = excelParser.getColumnName(sheet, "CABANG", 3);
            int getColAddress = excelParser.getColumnName(sheet, "ALAMAT LENGKAP", 3);
            int getColCity = excelParser.getColumnName(sheet, "KOTA/KABUPATEN", 3);
            int getColBranchMachine = excelParser.getColumnName(sheet, "JENIS MESIN", 3);
            int getColDenom = excelParser.getColumnName(sheet, "DENOM", 3);
            int getColIsChip = excelParser.getColumnName(sheet, "CHIP", 3);
            int getColLatitude = excelParser.getColumnName(sheet, "LATITUDE", 3);
            int getColLongitude = excelParser.getColumnName(sheet, "LONGITUDE", 3);
            int getColKonvenSyariah = excelParser.getColumnName(sheet, "KONVEN / SYARIAH", 3);
            int getColKeyCheckValue = excelParser.getColumnName(sheet, "KEY CHECK VALUE", 3);
            int getColSingleDes = excelParser.getColumnName(sheet, "SINGLEDES", 3);
            int getColTripleDes = excelParser.getColumnName(sheet, "TRIPLEDES", 3);
            int getColTID = excelParser.getColumnName(sheet, "TID",3 );
            int getColIpAddress = excelParser.getColumnName(sheet, "IP ADDRESS", 3 );
            int getColPortATM = excelParser.getColumnName(sheet, "PORT ATM", 3);
            int getColZonaPrioritas = excelParser.getColumnName(sheet, "ZONA PRIORITAS", 3);

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

                Cell cellTerminalIdOld =  row.getCell(getColTerminalIdIndexOld);
                String terminalIdOld  = ExcelParser.getCellValue(cellTerminalIdOld);
                if ( terminalIdOld.length() > 0 ) {
                    //System.out.print("\'" + terminalId + "\t");
                    //System.out.print("\'" + terminalId.substring(terminalId.length()-5) + "\t");

                    //System.out.print("ATM" + terminalId.substring(terminalId.length()-5) + "\t");
                    Cell cellTerminalIdNew =  row.getCell(getColTerminalIdIndexNew);
                    Cell cellTerminalName =  row.getCell(getColTerminalNameIndex);
                    Cell cellBranchCode =  row.getCell(getColBranchCode);
                    Cell cellAddress =  row.getCell(getColAddress);
                    Cell cellCity =  row.getCell(getColCity);
                    Cell cellBranchMachine =  row.getCell(getColBranchMachine);
                    Cell cellDenom =  row.getCell(getColDenom);
                    Cell cellIsChip =  row.getCell(getColIsChip);
                    Cell cellLatitude =  row.getCell(getColLatitude);
                    Cell cellLongitude =  row.getCell(getColLongitude);
                    Cell cellKonvenSyariah =  row.getCell(getColKonvenSyariah);
                    Cell cellKeyCheckValue =  row.getCell(getColKeyCheckValue);
                    Cell cellSingleDes =  row.getCell(getColSingleDes);
                    Cell cellTripleDes =  row.getCell(getColTripleDes);
                    Cell cellTID =  row.getCell(getColTID);
                    Cell cellIpAdress =  row.getCell(getColIpAddress);
                    Cell cellPortATM =  row.getCell(getColPortATM);
                    Cell cellZonaPrioritas =  row.getCell(getColZonaPrioritas);

                    String valTerminalName  =excelParser.getCellValue(cellTerminalName);
                    String[] arrTerminalName = valTerminalName.split("\\s+");
                    String valMachineType = "";
                    if (arrTerminalName[0].equalsIgnoreCase("ADM")){
                        valMachineType = "ADM";
                    }else {
                        valMachineType = "ATM";
                    }
                    String valDeviceNameNew = valMachineType + cellTerminalIdNew;
                    String valDeviceNameOld  = valMachineType + cellTerminalIdOld;

                    String valAddress =excelParser.getCellValue(cellAddress);
                    //String[] arrAddress = valAddress.split("\\s+");
                    String valCity =excelParser.getCellValue(cellCity);

                    String valBranchMachine =excelParser.getCellValue(cellBranchMachine);
                    String[] arrBranchName = valBranchMachine.split("\\s+");
                    String valMachineModel = "";
                    if (arrBranchName[0].equalsIgnoreCase("WINCOR")){
                        valMachineModel = "WDC";
                    }else if (arrBranchName[0].equalsIgnoreCase("HYOSUNG")){
                        valMachineModel = "HDC";
                    }else if (arrBranchName[0].equalsIgnoreCase("NCR")){
                        valMachineModel = "NDC";
                    }

                    String valDenom =excelParser.getCellValue(cellDenom);
                    String valIsChip =excelParser.getCellValue(cellIsChip);
                    String isChip = "";
                    if (valIsChip.equalsIgnoreCase("Yes")){
                        isChip = "Y";
                    }else{
                        isChip = "N";
                    }

                    String valGroupCode = "";
                    //System.out.println( valDeviceNameNew + "-valDenom:" + valDenom);
                    if ( (valDenom.equalsIgnoreCase("50")) && (isChip.equalsIgnoreCase("Y")) && (!arrBranchName[0].equalsIgnoreCase("NCR")) ){
                        valGroupCode = "11";
                    }else if ( (valDenom.equalsIgnoreCase("100")) && (isChip.equalsIgnoreCase("Y")) && (!arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "21";
                    }else if ( (valDenom.equalsIgnoreCase("50")) && (isChip.equalsIgnoreCase("N")) && (arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "10";
                    }else if ( (valDenom.equalsIgnoreCase("100")) && (isChip.equalsIgnoreCase("N")) && (arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "20";
                    }else if ( (valDenom.equalsIgnoreCase("50")) && (isChip.equalsIgnoreCase("N")) && (!arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "12";
                    }else if ( (valDenom.equalsIgnoreCase("100")) && (isChip.equalsIgnoreCase("N")) && (!arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "22";
                    }else if ( (valDenom.equalsIgnoreCase("50")) && (isChip.equalsIgnoreCase("Y")) && (arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "13";
                    }
                    else if ( (valDenom.equalsIgnoreCase("100")) && (isChip.equalsIgnoreCase("Y")) && (arrBranchName[0].equalsIgnoreCase("NCR"))  ){
                        valGroupCode = "23";
                    }
                    else{
                        System.out.println("masuk group code else " + valDeviceNameNew);
                    }

                    String valKonvenSyariah =excelParser.getCellValue(cellKonvenSyariah);
                    String valTID = "";
                    if (valKonvenSyariah.equalsIgnoreCase("KONVEN")){
                        valTID = "K" +excelParser.getCellValue(cellTID);
                    }else if ( valKonvenSyariah.equalsIgnoreCase("SYARIAH") ) {
                        valTID = "S" +excelParser.getCellValue(cellTID);
                    }

                    String valKeyCheckValue =excelParser.getCellValue( cellKeyCheckValue );
                    String valSingleDes =excelParser.getCellValue( cellSingleDes );
                    String valTripleDes =excelParser.getCellValue( cellTripleDes );
                    String valTmkLmk = "";
                    if ( !valSingleDes.equalsIgnoreCase("") ){
                        valTmkLmk = tmkSingleDes;
                    }else{
                        if ( valKeyCheckValue.contains("E0EA") ){
                            valTmkLmk = tmkTripleDesA;
                        }else{
                            valTmkLmk = tmkTripleDesB;
                        }
                    }



                    String luno = terminalIdOld.substring(terminalIdOld.length()-3);
                    String cfgid = terminalIdOld.substring(terminalIdOld.length()-4);
                    String tpk_tmk = "";
                    String tpk_tmk_spec = "*thales";
                    String tpk_lmk = "";
                    String tpk_lmk_spec = "*thales";
                    String tmk_lmk = valTmkLmk;
                    String tmk_lmk_spec = "*thales";
                    String tpk_date = dateNow;
                    String currency_code = "360";
                    String branch_code =excelParser.getCellValue(cellBranchCode);
                    String address = valAddress;
                    String city = valCity;
                    String language_code = "02";
                    String emulation = "NDC";
                    String machine_type = valMachineType;
                    String machine_model = valMachineModel;
                    String machine_serial = "machine_serial";
                    int stan = 0;
                    String group_code = valGroupCode;
                    String icc_support = isChip;
                    String locations = valTerminalName;
                    String tid = valTID;
                    String status="O";
                    String spv_mode="N";
                    int tx_total = 0;
                    String card_captured = "NULL";
                    String latitude = excelParser.getCellValue(cellLatitude);
                    String longitude =excelParser.getCellValue(cellLongitude);
                    String[] arrLat =   latitude.split("\\.");
                    String[] arrLon =   longitude.split("\\.");
                    //System.out.println("arrLat:" + arrLat.length + "-arrLon:" + arrLon.length);
                    if ((arrLat.length > 2) || (arrLon.length > 2) ){
                        System.out.println("arrLat:" + latitude + "-arrLon:" + longitude);

                    }
                    String machine_datetime = "";
                    String used_flag = "Y";
                    String description = valTerminalName;

                    String valZonaPrioritas = excelParser.getCellValue(cellZonaPrioritas);
                    String valZoneCode = "";
                    if ( valZonaPrioritas.equalsIgnoreCase("ZONA SATELIT") ){
                        valZoneCode = "ZS";
                    }
                    else if ( valZonaPrioritas.equalsIgnoreCase("PRIORITAS I") ){
                        valZoneCode = "P1";
                    }
                    else if ( valZonaPrioritas.equalsIgnoreCase("PRIORITAS II") ){
                        valZoneCode = "P2";
                    }
                    else if ( valZonaPrioritas.equalsIgnoreCase("PRIORITAS III") ){
                        valZoneCode = "P3";
                    }

                    aMachines.add("INSERT INTO a_machine" +
                            " ( " +
                            " created_by, " + " created_date, " +
                            " updated_by, " + " updated_date, " +
                            " device_name, " + " luno, " + " cfgid, " +
                            " tpk_tmk, " + " tpk_tmk_spec, " +
                            " tpk_lmk, " + " tpk_lmk_spec, " +
                            " tmk_lmk, " + " tmk_lmk_spec, " +
                            " tpk_date, " + " currency_code, " +
                            " branch_code, " + " address, " + " city, " +
                            " language_code, " + " emulation, " +
                            " machine_type, " + " machine_model, " + " machine_serial, " + " stan, " +
                            " group_code, " + " icc_support, " +
                            " locations, " + " tid, " + " status, " + " spv_mode, " +
                            " tx_total, " + " card_captured, " +
                            " latitude, " + " longitude, " +
                            " machine_datetime, " + " used_flag, " + " description " +
                            " ) " +
                            " VALUES (" +
                            userId + ",'" + dateNow + "'," +
                            userId + ",'" + dateNow + "'," +
                            "'" + valDeviceNameNew + "', " + "'" + luno + "', " + "'" + cfgid + "', " +
                            "'" + tpk_tmk + "', " + "'" + tpk_tmk_spec + "', " +
                            "'" + tpk_lmk + "', " + "'" + tpk_lmk_spec + "', " +
                            "'" + tmk_lmk + "', " + "'" + tmk_lmk_spec + "', " +
                            "'" + tpk_date + "', " + "'" + currency_code + "', " +
                            "'" + branch_code + "', " + "'" + address + "', " + "'" + city + "', " +
                            "'" + language_code + "', " + "'" + emulation + "', " +
                            "'" + machine_type + "', " + "'" + machine_model + "', " + "'" + machine_serial + "', " + "" + stan + ", " +
                            "'" + group_code + "', " + "'" + icc_support + "', " +
                            "'" + locations + "', " + "'" + tid + "', " + "'" + status + "', " + "'" + spv_mode + "', " +
                            "" + tx_total + ", " + "" + card_captured + ", " +
                            "" + latitude + ", " + "" + longitude + ", " +
                            "'" + machine_datetime + "', " + "'" + used_flag + "', " + "'" + description + "'" +
                            ");");

                    /*
                    updateMachines.add("UPDATE a_machine SET " +
                            " latitude =  " + latitude + "," +
                            " longitude =  " + longitude + "," +
                            " group_code =  '" + group_code + "'," +
                            " icc_support =  '" + icc_support + "'" +
                            " zone_code =  '" + valZoneCode + "'" +
                            " WHERE device_name= '" + valDeviceNameNew + "' ;"); */

                    updateMachines.add("UPDATE a_machine SET " +
                            " zone_code =  '" + valZoneCode + "'" +
                            " WHERE device_name= '" + valDeviceNameNew + "' ;");

                    String moduleName = "atm.module.bcd.multiport";
                    String ip =excelParser.getCellValue(cellIpAdress);
                    String port =excelParser.getCellValue(cellPortATM);
                    String statusSocketTerm = "N";
                    aSocketTerms.add("INSERT INTO a_socket_term" +
                            " ( " +
                            " created_by, " + " created_date, " +
                            " updated_by, " + " updated_date, " +
                            " module_name, " + " device_name, " +
                            " ip, " + " port, " +
                            " status, " + " description " +
                            " ) " +
                            " VALUES (" +
                            userId + ",'" + dateNow + "'," +
                            userId + ",'" + dateNow + "'," +
                            "'" + moduleName + "', " + "'" + valDeviceNameNew + "', " +
                            "'" + ip + "', " + "'" + port + "', " +
                            "'" + statusSocketTerm + "', " + "'" + description + "'" +
                            ");");


                    String catridge1Used = "Y";
                    String catridge1Denom = valDenom + "000";
                    String catridge1Curr = "360";
                    String catridge1MaxDispense = "25";
                    String catridge1NumDispense = "0";
                    String catridge1TotalCash = "0";
                    String catridge1TotalRejected = "0";

                    String catridge2Used = "Y";
                    String catridge2Denom = valDenom + "000";
                    String catridge2Curr = "360";
                    String catridge2MaxDispense = "25";
                    String catridge2NumDispense = "0";
                    String catridge2TotalCash = "0";
                    String catridge2TotalRejected = "0";

                    String catridge3Used = "Y";
                    String catridge3Denom = valDenom + "000";
                    String catridge3Curr = "360";
                    String catridge3MaxDispense = "25";
                    String catridge3NumDispense = "0";
                    String catridge3TotalCash = "0";
                    String catridge3TotalRejected = "0";

                    String catridge4Used = "Y";
                    String catridge4Denom = valDenom + "000";
                    String catridge4Curr = "360";
                    String catridge4MaxDispense = "25";
                    String catridge4NumDispense = "0";
                    String catridge4TotalCash = "0";
                    String catridge4TotalRejected = "0";

                    aNdcCassettes.add("INSERT INTO a_ndc_cassette" +
                            " ( " +
                            " created_by, " + " created_date, " +
                            " updated_by, " + " updated_date, " +
                            " device_name, " +
                            " catridge1_used, " + " catridge1_denom, " + " catridge1_curr, " +
                            " catridge1_max_dispense, " + " catridge1_num_dispense, " +
                            " catridge1_total_cash, " + " catridge1_total_rejected, " +
                            " catridge2_used, " + " catridge2_denom, " + " catridge2_curr, " +
                            " catridge2_max_dispense, " + " catridge2_num_dispense, " +
                            " catridge2_total_cash, " + " catridge2_total_rejected, " +
                            " catridge3_used, " + " catridge3_denom, " + " catridge3_curr, " +
                            " catridge3_max_dispense, " + " catridge3_num_dispense, " +
                            " catridge3_total_cash, " + " catridge3_total_rejected, " +
                            " catridge4_used, " + " catridge4_denom, " + " catridge4_curr, " +
                            " catridge4_max_dispense, " + " catridge4_num_dispense, " +
                            " catridge4_total_cash, " + " catridge4_total_rejected, " +
                            " description " +
                            " ) " +
                            " VALUES (" +
                            userId + ",'" + dateNow + "'," +
                            userId + ",'" + dateNow + "'," +
                            "'" + valDeviceNameNew + "', " +
                            "'" + catridge1Used + "', " + "'" + catridge1Denom + "', " + "'" + catridge1Curr + "', " +
                            "" + catridge1MaxDispense + ", " + "" + catridge1NumDispense + ", " +
                            "" + catridge1TotalCash + ", " + "" + catridge1TotalRejected + ", " +
                            "'" + catridge2Used + "', " + "'" + catridge2Denom + "', " + "'" + catridge2Curr + "', " +
                            "" + catridge2MaxDispense + ", " + "" + catridge2NumDispense + ", " +
                            "" + catridge2TotalCash + ", " + "" + catridge2TotalRejected + ", " +
                            "'" + catridge3Used + "', " + "'" + catridge3Denom + "', " + "'" + catridge3Curr + "', " +
                            "" + catridge3MaxDispense + ", " + "" + catridge3NumDispense + ", " +
                            "" + catridge3TotalCash + ", " + "" + catridge3TotalRejected + ", " +
                            "'" + catridge4Used + "', " + "'" + catridge4Denom + "', " + "'" + catridge4Curr + "', " +
                            "" + catridge4MaxDispense + ", " + "" + catridge4NumDispense + ", " +
                            "" + catridge4TotalCash + ", " + "" + catridge4TotalRejected + ", " +
                            "'" + description + "'" +
                            ");");

                    aMachineDetails.add("INSERT INTO a_machine_detail" +
                            " ( " +
                            " created_by, " + " created_date, " +
                            " updated_by, " + " updated_date, " +
                            " device_name, " +
                            " hf_clock, " + " hf_communication, " + " hf_system_disk, " +
                            " hf_msre, " + " hf_cash_handler, " + " hf_depository, " +
                            " hf_receipt_printer, " + " hf_journal_printer, " + " hf_thermal_printer, " +
                            " hf_nightsave_depository, " + " hf_encryptor, " + " hf_camera, " +
                            " hf_door_access, " + " hf_flex_disk, " + " hf_cassette_type1, " +
                            " hf_cassette_type2, " + " hf_cassette_type3, " + " hf_cassette_type4, " +
                            " hf_statement_printer, " + " hf_signage_display, " + " hf_system_display, " +
                            " hf_media_entry, " + " hf_envelope_dispenser, " + " hf_coins_dispense_module, " +
                            " hf_document_process_module, " + " hf_voice_guidane, " + " hf_bna, " +
                            " hf_cheque_processor, " + " hc_product, " + " hc_system_disk, " +
                            " hc_msre, " + " hc_cash_handler, " + " hc_envelope_depository, " +
                            " hc_receipt_printer, " + " hc_journal_printer, " + " hc_nightsave_depository, " +
                            " hc_encryptor, " + " hc_camera, " + " hc_flex_disk, " +
                            " hc_tamper_bin_indicator, " + " hc_cardholder_keyboard, " + " hc_operator_keyboard, " +
                            " hc_cardholder_display, " + " hc_statement_printer, " + " hc_coin_dispenser, " +
                            " hc_system_display, " + " hc_media_entry_indicator, " + " hc_envelope_dispenser, " +
                            " hc_coins_dispense_module, " + " hc_voice_guidance, " + " hc_bna, " +
                            " hc_cheque_processor, " + " ss_card_capture_bin, " + " ss_cash_handler_reject_bin, " +
                            " ss_deposit_bin, " + " ss_receipt_paper, " + " ss_journal_paper, " +
                            " ss_nightsafe, " + " ss_cassette_type1, " + " ss_cassette_type2, " +
                            " ss_cassette_type3, " + " ss_cassette_type4, " + " ss_statement_paper, " +
                            " ss_statement_ribbon, " + " ss_envelope_dispenser" +
                            " ) " +
                            " VALUES (" +
                            userId + ",'" + dateNow + "'," +
                            userId + ",'" + dateNow + "'," +
                            "'" + valDeviceNameNew + "', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0', " + "'0', " +
                            "'0', " + "'0' " +
                            ");");


                    mLookUp.add("INSERT INTO m_lookup" +
                            " ( " +
                            " created_by, " + " created_date, " +
                            " updated_by, " + " updated_date, " +
                            " lookup_group, " +
                            " lookup_name, " + " lookup_value " +
                            " ) " +
                            " VALUES (" +
                            userId + ",'" + dateNow + "'," +
                            userId + ",'" + dateNow + "'," +
                            "'device', " +
                            "'" + valDeviceNameNew + "', " + "'" + valDeviceNameOld + "'" +
                            ");");

                    openPorts.add("firewall-cmd --zone=public --add-port=" + port + "/tcp --permanent");

                }

            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        System.out.println("\n");
        System.out.println("a_machine");
        fileProcessing.writeToFile( aMachines,"a_machine.sql");


        System.out.println("a_machine_detail");
        fileProcessing.writeToFile( aMachineDetails,"a_machine_detail.sql");
        System.out.println("\n");

        System.out.println("a_socket_term");
        fileProcessing.writeToFile( aSocketTerms,"a_socket_term.sql");
        System.out.println("\n");

        System.out.println("a_ndc_cassette");
        fileProcessing.writeToFile( aNdcCassettes,"a_ndc_cassette.sql");
        System.out.println("\n");
        /*for (int i = 0; i <aNdcCassettes.size() ; i++) {

            System.out.println(aNdcCassettes.get(i));
        }*/

        System.out.println("m_lookup");
        fileProcessing.writeToFile( mLookUp,"m_lookup.sql");
        System.out.println("\n");

        System.out.println("a_machine_update");
        fileProcessing.writeToFile( updateMachines,"a_machine_update.sql");
        System.out.println("\n");



        /*
        System.out.println("\n");
        for (int i = 0; i <openPorts.size() ; i++) {
            System.out.println(openPorts.get(i));
        }*/

    }





}
