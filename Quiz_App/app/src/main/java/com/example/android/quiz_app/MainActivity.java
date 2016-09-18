package com.example.android.quiz_app;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButton ;

    final RadioGroup radioGroups[] = new RadioGroup[7];

    final String answers[] = new String[7];

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> questions = new ArrayList<>();
        questions.add("Q 1 - XML Stand For?");
        questions.add("Q 2 - What is the difference between margin and padding in android layout?");
        questions.add("Q 3 - What is JSON in android?");
        questions.add("Q 4 - What is log message in android?");
        questions.add("Q 5 - What is ADB in android?");
        questions.add("Q 6 - What is APK in android?");
        questions.add("Q 7 - What is the life cycle of services in android?");

        //Ans1
        String ansarray1[] = {" Extensible Markup Language"," Extra ensible Markup Language"," Extensible model Line"};
        //ans2
        String ansarray2[] = {"Padding is used to offset the content of a view by specific px or dp"
             ,"Margin is specifying the extra space left on all four sides in layout","Both A and B are correct"};
        //ans3
        String ansarray3[] = {"Java Script Object Notation","Java Script Oriented Notation","Java Script Object Native"};
        //ans4
        String ansarray4[] = {"Log message is used to debug a program.","Same as Printf()","Same  as Toast()"};
        //ans5
        String ansarray5[] = {"Image tool","Android Debug Bridge","Development tool"};
        //ans6
        String ansarray6[] = {"Android packages","Android pack","Android packaging kit"};
        //ans7
        String ansarray7[] = {"onCreate()−>onStartCommand()−>onDestory()","onRecieve()"
                ,"Service life cycle is same as activity life cycle"};
        //Correct all Answer
         final String answers[] = {ansarray1[0],ansarray2[2],ansarray3[0],ansarray4[0],ansarray5[1],ansarray6[2],ansarray7[0]};

         final TextView textViewq1 = (TextView) findViewById(R.id.q1);

         TextView textViewq2 = (TextView) findViewById(R.id.q2);

         TextView textViewq3 = (TextView) findViewById(R.id.q3);

         TextView textViewq4 = (TextView) findViewById(R.id.q4);

         TextView textViewq5 = (TextView) findViewById(R.id.q5);

         TextView textViewq6 = (TextView) findViewById(R.id.q6);

         TextView textViewq7 = (TextView) findViewById(R.id.q7);

         final RadioButton radioButtonans1_1 = (RadioButton) findViewById(R.id.ans1_1);
         final RadioButton radioButtonans1_2 = (RadioButton) findViewById(R.id.ans1_2);
         final RadioButton radioButtonans1_3 = (RadioButton) findViewById(R.id.ans1_3);


         final RadioButton radioButtonans2_1 = (RadioButton) findViewById(R.id.ans2_1);
         final RadioButton radioButtonans2_2 = (RadioButton) findViewById(R.id.ans2_2);
         final RadioButton radioButtonans2_3 = (RadioButton) findViewById(R.id.ans2_3);

         final RadioButton radioButtonans3_1 = (RadioButton) findViewById(R.id.ans3_1);
         final RadioButton radioButtonans3_2 = (RadioButton) findViewById(R.id.ans3_2);
         final RadioButton radioButtonans3_3 = (RadioButton) findViewById(R.id.ans3_3);

         final RadioButton radioButtonans4_1 = (RadioButton) findViewById(R.id.ans4_1);
         final RadioButton radioButtonans4_2 = (RadioButton) findViewById(R.id.ans4_2);
         final RadioButton radioButtonans4_3 = (RadioButton) findViewById(R.id.ans4_3);

         final RadioButton radioButtonans5_1 = (RadioButton) findViewById(R.id.ans5_1);
         final RadioButton radioButtonans5_2 = (RadioButton) findViewById(R.id.ans5_2);
         final RadioButton radioButtonans5_3 = (RadioButton) findViewById(R.id.ans5_3);

         final RadioButton radioButtonans6_1 = (RadioButton) findViewById(R.id.ans6_1);
         final RadioButton radioButtonans6_2 = (RadioButton) findViewById(R.id.ans6_2);
         final RadioButton radioButtonans6_3 = (RadioButton) findViewById(R.id.ans6_3);

         final RadioButton radioButtonans7_1 = (RadioButton) findViewById(R.id.ans7_1);
         final RadioButton radioButtonans7_2 = (RadioButton) findViewById(R.id.ans7_2);
         final RadioButton radioButtonans7_3 = (RadioButton) findViewById(R.id.ans7_3);



         radioGroups[0] = (RadioGroup) findViewById(R.id.ans1);
         radioGroups[1] = (RadioGroup) findViewById(R.id.ans2);
         radioGroups[2] = (RadioGroup) findViewById(R.id.ans3);
         radioGroups[3] = (RadioGroup) findViewById(R.id.ans4);
         radioGroups[4] = (RadioGroup) findViewById(R.id.ans5);
         radioGroups[5] = (RadioGroup) findViewById(R.id.ans6);
         radioGroups[6] = (RadioGroup) findViewById(R.id.ans7);

         textViewq1.setText(String.valueOf(questions.get(0)));
         textViewq2.setText(String.valueOf(questions.get(1)));
         textViewq3.setText(String.valueOf(questions.get(2)));
         textViewq4.setText(String.valueOf(questions.get(3)));
         textViewq5.setText(String.valueOf(questions.get(4)));
         textViewq6.setText(String.valueOf(questions.get(5)));
         textViewq7.setText(String.valueOf(questions.get(6)));


         radioButtonans1_1.setText(ansarray1[0]);
         radioButtonans1_2.setText(ansarray1[1]);
         radioButtonans1_3.setText(ansarray1[2]);

         radioButtonans2_1.setText(ansarray2[0]);
         radioButtonans2_2.setText(ansarray2[1]);
         radioButtonans2_3.setText(ansarray2[2]);

         radioButtonans3_1.setText(ansarray3[0]);
         radioButtonans3_2.setText(ansarray3[1]);
         radioButtonans3_3.setText(ansarray3[2]);

         radioButtonans4_1.setText(ansarray4[0]);
         radioButtonans4_2.setText(ansarray4[1]);
         radioButtonans4_3.setText(ansarray4[2]);

         radioButtonans5_1.setText(ansarray5[0]);
         radioButtonans5_2.setText(ansarray5[1]);
         radioButtonans5_3.setText(ansarray5[2]);

         radioButtonans6_1.setText(ansarray6[0]);
         radioButtonans6_2.setText(ansarray6[1]);
         radioButtonans6_3.setText(ansarray6[2]);

         radioButtonans7_1.setText(ansarray7[0]);
         radioButtonans7_2.setText(ansarray7[1]);
         radioButtonans7_3.setText(ansarray7[2]);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.ans1);
        final Button button = (Button) findViewById(R.id.submitbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                int getscore = calculation(answers);
                Toast.makeText(MainActivity.this,""+getscore,Toast.LENGTH_SHORT).show();
            }
        });

    }
    private int calculation(String ans[]){
        score = 0;
        for (int i =0 ; i<radioGroups.length; i++){
            int selectedid = radioGroups[i].getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedid);
            String tempdata = String.valueOf(radioButton.getText());
            if(ans[i] == tempdata){
                score += 10;
            }
        }
return score;
    }

}
