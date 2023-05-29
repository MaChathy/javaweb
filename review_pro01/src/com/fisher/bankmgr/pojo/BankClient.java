package com.fisher.bankmgr.pojo;

/**
 *
 * @author fisher
 * @version 1.0.1 2023/5/29 - 14:31
 */
public class BankClient {
    private Integer cid;
    private String cname;
    private Integer hand_resA_count;
    private Integer hand_resB_count;
    private Integer hand_resC_count;
    private Integer hand_resD_count;
    private Integer max_resA_count;
    private Integer max_resB_count;
    private Integer max_resC_count;
    private Integer max_resD_count;

    public BankClient(){}

    public BankClient(Integer cid, String cname, Integer hand_resA_count, Integer hand_resB_count, Integer hand_resC_count, Integer hand_resD_count, Integer max_resA_count, Integer max_resB_count, Integer max_resC_count, Integer max_resD_count) {
        this.cid = cid;
        this.cname = cname;
        this.hand_resA_count = hand_resA_count;
        this.hand_resB_count = hand_resB_count;
        this.hand_resC_count = hand_resC_count;
        this.hand_resD_count = hand_resD_count;
        this.max_resA_count = max_resA_count;
        this.max_resB_count = max_resB_count;
        this.max_resC_count = max_resC_count;
        this.max_resD_count = max_resD_count;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getHand_resA_count() {
        return hand_resA_count;
    }

    public void setHand_resA_count(Integer hand_resA_count) {
        this.hand_resA_count = hand_resA_count;
    }

    public Integer getHand_resB_count() {
        return hand_resB_count;
    }

    public void setHand_resB_count(Integer hand_resB_count) {
        this.hand_resB_count = hand_resB_count;
    }

    public Integer getHand_resC_count() {
        return hand_resC_count;
    }

    public void setHand_resC_count(Integer hand_resC_count) {
        this.hand_resC_count = hand_resC_count;
    }

    public Integer getHand_resD_count() {
        return hand_resD_count;
    }

    public void setHand_resD_count(Integer hand_resD_count) {
        this.hand_resD_count = hand_resD_count;
    }

    public Integer getMax_resA_count() {
        return max_resA_count;
    }

    public void setMax_resA_count(Integer max_resA_count) {
        this.max_resA_count = max_resA_count;
    }

    public Integer getMax_resB_count() {
        return max_resB_count;
    }

    public void setMax_resB_count(Integer max_resB_count) {
        this.max_resB_count = max_resB_count;
    }

    public Integer getMax_resC_count() {
        return max_resC_count;
    }

    public void setMax_resC_count(Integer max_resC_count) {
        this.max_resC_count = max_resC_count;
    }

    public Integer getMax_resD_count() {
        return max_resD_count;
    }

    public void setMax_resD_count(Integer max_resD_count) {
        this.max_resD_count = max_resD_count;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", hand_resA_count=" + hand_resA_count +
                ", hand_resB_count=" + hand_resB_count +
                ", hand_resC_count=" + hand_resC_count +
                ", hand_resD_count=" + hand_resD_count +
                ", max_resA_count=" + max_resA_count +
                ", max_resB_count=" + max_resB_count +
                ", max_resC_count=" + max_resC_count +
                ", max_resD_count=" + max_resD_count +
                '}';
    }
}
