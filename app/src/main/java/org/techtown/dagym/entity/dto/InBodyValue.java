package org.techtown.dagym.entity.dto;

public class InBodyValue {
    private Long id;
    private String value;
    private String inBody_date;

    public InBodyValue() {

    }

    public InBodyValue(String value, String inBody_date) {
        this.value = value;
        this.inBody_date = inBody_date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInBody_date() {
        return inBody_date;
    }

    public void setInBody_date(String inBody_date) {
        this.inBody_date = inBody_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
