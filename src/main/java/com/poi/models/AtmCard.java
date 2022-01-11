package com.poi.models;

public class AtmCard {

    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;

    public AtmCard(String createdBy, String createdDate, String updatedBy, String updatedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    //CREATED_BY	CREATED_DATE	UPDATED_BY	UPDATED_DATE	CARD_NUMBER	CARD_HOLDER	CARD_TYPE	CUST_NUMBER	ISSUE_DATE	EXPIRED_DATE	LIMIT_PROFILE	LAST_USED_DATE	BRANCH_CODE	PIN_OFFSET	PIN_RETRY_DATE	PIN_RETRY_ATTEMPT	STATUS	CARD_HEADER_KEY	ATC	SMART_SYSTEM	DELIVERY	ALT_CIF

}
