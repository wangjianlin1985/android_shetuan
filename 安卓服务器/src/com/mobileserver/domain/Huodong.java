package com.mobileserver.domain;

public class Huodong {
    /*�id*/
    private int huodongId;
    public int getHuodongId() {
        return huodongId;
    }
    public void setHuodongId(int huodongId) {
        this.huodongId = huodongId;
    }

    /*�����*/
    private String huodongName;
    public String getHuodongName() {
        return huodongName;
    }
    public void setHuodongName(String huodongName) {
        this.huodongName = huodongName;
    }

    /*�����*/
    private String huodongDesc;
    public String getHuodongDesc() {
        return huodongDesc;
    }
    public void setHuodongDesc(String huodongDesc) {
        this.huodongDesc = huodongDesc;
    }

    /*�ʱ��*/
    private java.sql.Timestamp huodongTime;
    public java.sql.Timestamp getHuodongTime() {
        return huodongTime;
    }
    public void setHuodongTime(java.sql.Timestamp huodongTime) {
        this.huodongTime = huodongTime;
    }

    /*�����*/
    private String shetuanObj;
    public String getShetuanObj() {
        return shetuanObj;
    }
    public void setShetuanObj(String shetuanObj) {
        this.shetuanObj = shetuanObj;
    }

    /*���ע*/
    private String huodongMemo;
    public String getHuodongMemo() {
        return huodongMemo;
    }
    public void setHuodongMemo(String huodongMemo) {
        this.huodongMemo = huodongMemo;
    }

}