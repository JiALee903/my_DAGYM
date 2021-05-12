package org.techtown.dagym.entity.dto;

public class AndInBodyDto {
    private Long id;
    private String inBody_user_id;
    private String inBody_weight;
    private String inBody_rmr;
    private String inBody_bfp;
    private String inBody_smm;
    private String inBody_date;

    public AndInBodyDto(Long id, String inBody_user_id, String inBody_weight, String inBody_rmr, String inBody_bfp, String inBody_smm, String inBody_date) {
        this.id = id;
        this.inBody_user_id = inBody_user_id;
        this.inBody_weight = inBody_weight;
        this.inBody_rmr = inBody_rmr;
        this.inBody_bfp = inBody_bfp;
        this.inBody_smm = inBody_smm;
        this.inBody_date = inBody_date;
    }

    public AndInBodyDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInBody_user_id() {
        return inBody_user_id;
    }

    public void setInBody_user_id(String inBody_user_id) {
        this.inBody_user_id = inBody_user_id;
    }

    public String getInBody_weight() {
        return inBody_weight;
    }

    public void setInBody_weight(String inBody_weight) {
        this.inBody_weight = inBody_weight;
    }

    public String getInBody_rmr() {
        return inBody_rmr;
    }

    public void setInBody_rmr(String inBody_rmr) {
        this.inBody_rmr = inBody_rmr;
    }

    public String getInBody_bfp() {
        return inBody_bfp;
    }

    public void setInBody_bfp(String inBody_bfp) {
        this.inBody_bfp = inBody_bfp;
    }

    public String getInBody_smm() {
        return inBody_smm;
    }

    public void setInBody_smm(String inBody_smm) {
        this.inBody_smm = inBody_smm;
    }

    public String getInBody_date() {
        return inBody_date;
    }

    public void setInBody_date(String inBody_date) {
        this.inBody_date = inBody_date;
    }
}
