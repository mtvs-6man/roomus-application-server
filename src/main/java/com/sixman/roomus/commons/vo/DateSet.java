package com.sixman.roomus.commons.vo;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.sql.Date;
@Embeddable
@Access(AccessType.FIELD)
public class DateSet {

    private Date date;

    public DateSet() {
    }

    public DateSet(String date) {
        setDate(date);
    }

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DateSet{" +
                "date=" + date +
                '}';
    }
}
