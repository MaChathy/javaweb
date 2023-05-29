package com.fisher.bankmgr.pojo;

/**
 * 银行资源类
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:38
 */
public class BankResource {
    private String res_name;
    private Integer res_count;

    public BankResource(){ }

    public BankResource(String res_name, Integer res_count){
        this.res_name = res_name;
        this.res_count = res_count;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public Integer getRes_count() {
        return res_count;
    }

    public void setRes_count(Integer res_count) {
        this.res_count = res_count;
    }

    @Override
    public String toString() {
        return "res_name=" + res_name + ", res_count=" + res_count ;
    }
}
