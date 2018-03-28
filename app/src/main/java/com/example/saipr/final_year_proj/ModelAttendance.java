package com.example.saipr.final_year_proj;

/**
 * Created by saipr on 3/27/2018.
 */

public class ModelAttendance {
    private String usn;
    private String name;
    private boolean status;


    public ModelAttendance(String usn,String name,boolean status) {
        this.usn = usn;
        this.name = name;
        this.status=status;
    }
    public String getUsn() {
        return usn;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
