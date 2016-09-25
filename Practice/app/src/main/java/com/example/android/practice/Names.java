package com.example.android.practice;

/**
 * Created by android on 9/3/2016.
 */
public class Names {
    private String namesInEng;
    private String namesInArabic;

    Names(String namesInEng, String namesInArabic){
        this.namesInEng = namesInEng;
        this.namesInArabic = namesInArabic;
    }
    public String getNamesInEng() {
        return namesInEng;
    }

    public String getNamesInArabic() {
        return namesInArabic;
    }
}
