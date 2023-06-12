package com.solvd.hospitalpatientrecoverymodel.medicaldetails;

import com.solvd.hospitalpatientrecoverymodel.humans.Nurse;
import com.solvd.hospitalpatientrecoverymodel.humans.Pediatrician;

import java.util.Date;
public class Procedure {
    private ProcedureNames code;
    private String details;
    private Date date;
    private String result;
    private String doctorComments;

    private Pediatrician whoRx;

    private Nurse whoRN;


    public Procedure(ProcedureNames code, Date date, Pediatrician whoRx, Nurse whoRN) {
        this.code = code;
        this.date = date;
        this.whoRx = whoRx;
        this.whoRN = whoRN;
    }

    public ProcedureNames getCode() {
        return code;
    }

    public void setCode(ProcedureNames code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Pediatrician getWhoRx() {
        return whoRx;
    }

    public void setWhoRx(Pediatrician whoRx) {
        this.whoRx = whoRx;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "code=" + code +
                ", details='" + details + '\'' +
                ", date=" + date +
                ", result='" + result + '\'' +
                ", doctorComments='" + doctorComments + '\'' +
                ", whoRx=" + whoRx +
                ", whoRN=" + whoRN +
                '}';
    }
}

