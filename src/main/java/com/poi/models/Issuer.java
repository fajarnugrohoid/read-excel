package com.poi.models;

import java.util.List;

public class Issuer {

    private String valModuleName;
    private String valParticipanCode; //jalin 800, atmb 200
    private List<String> listBin;
    private String valIssuerCode;
    private String valNetworkDefault;
    private String valNetwork;
    private String valBankCode;
    private String valBankName;
    private String valFwdBankCode;
    private String valFwdBankName;
    private String valDescription;

    public Issuer() {
    }

    public String getValModuleName() {
        return valModuleName;
    }

    public void setValModuleName(String valModuleName) {
        this.valModuleName = valModuleName;
    }

    public String getValParticipanCode() {
        return valParticipanCode;
    }

    public void setValParticipanCode(String valParticipanCode) {
        this.valParticipanCode = valParticipanCode;
    }

    public List<String> getListBin() {
        return listBin;
    }

    public void setListBin(List<String> listBin) {
        this.listBin = listBin;
    }

    public String getValIssuerCode() {
        return valIssuerCode;
    }

    public void setValIssuerCode(String valIssuerCode) {
        this.valIssuerCode = valIssuerCode;
    }

    public String getValNetworkDefault() {
        return valNetworkDefault;
    }

    public void setValNetworkDefault(String valNetworkDefault) {
        this.valNetworkDefault = valNetworkDefault;
    }

    public String getValNetwork() {
        return valNetwork;
    }

    public void setValNetwork(String valNetwork) {
        this.valNetwork = valNetwork;
    }

    public String getValBankCode() {
        return valBankCode;
    }

    public void setValBankCode(String valBankCode) {
        this.valBankCode = valBankCode;
    }

    public String getValBankName() {
        return valBankName;
    }

    public void setValBankName(String valBankName) {
        this.valBankName = valBankName;
    }

    public String getValFwdBankCode() {
        return valFwdBankCode;
    }

    public void setValFwdBankCode(String valFwdBankCode) {
        this.valFwdBankCode = valFwdBankCode;
    }

    public String getValFwdBankName() {
        return valFwdBankName;
    }

    public void setValFwdBankName(String valFwdBankName) {
        this.valFwdBankName = valFwdBankName;
    }

    public String getValDescription() {
        return valDescription;
    }

    public void setValDescription(String valDescription) {
        this.valDescription = valDescription;
    }
}
