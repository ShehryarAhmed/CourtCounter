package com.example.sayla.report_card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by sayla on 25/09/2016.
 */
public class studentdetail extends AppCompatActivity {
    private String schoolname;

    private String studentname;

    private coursedetail coursedetail;

    private marks marksdetail;

    public studentdetail(final String Schoolname,final String Studentname,
                         final coursedetail coursedetails, final marks marksdetails){
        this.schoolname = Schoolname;
        this.studentname = Studentname;
        this.coursedetail = coursedetails;
        this.marksdetail = marksdetails;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public String getStudentname() {
        return studentname;
    }

    public com.example.sayla.report_card.coursedetail getCoursedetail() {
        return coursedetail;
    }

    public marks getMarksdetail() {
        return marksdetail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
    }
}
