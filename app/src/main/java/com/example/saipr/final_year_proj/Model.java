package com.example.saipr.final_year_proj;

/**
 * Created by saipr on 3/19/2018.
 */

public class Model {
    private String usn;
    private String marks;


    public Model(String usn,String marks) {
        this.usn = usn;
        this.marks = marks;
    }
    public String getUsn() {
        return usn;
    }

    public String getMarks() {
        return marks;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
