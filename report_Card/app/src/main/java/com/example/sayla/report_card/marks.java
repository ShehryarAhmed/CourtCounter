package com.example.sayla.report_card;

/**
 * Created by sayla on 25/09/2016.
 */
public class marks {
    private String IslamiatMarks;
    private String MathMarks;
    private String UrduMarks;
    private String EnglishMarks;
    private String ScienceMarks;

    public marks(final String islamiatmarks,final String mathmarks,
                 final String urdumarks,final String englishmarks,
                 final String sciencemarks){
        this.IslamiatMarks = islamiatmarks;
        this.MathMarks = mathmarks;
        this.UrduMarks = urdumarks;
        this.EnglishMarks = englishmarks;
        this.ScienceMarks = sciencemarks;
    }

    public String getIslamiatMarks() {
        return IslamiatMarks;
    }

    public String getMathMarks() {
        return MathMarks;
    }

    public String getUrduMarks() {
        return UrduMarks;
    }

    public String getEnglishMarks() {
        return EnglishMarks;
    }

    public String getScienceMarks() {
        return ScienceMarks;
    }
}
