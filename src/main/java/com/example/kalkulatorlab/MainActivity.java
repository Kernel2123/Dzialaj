package com.example.kalkulatorlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.sql.Struct;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result_textView, solution_textView;
    MaterialButton buttonC, buttonAC, button_CBracket, button_OBracket;
    MaterialButton button_div, button_mult, button_plus, button_minus, button_eq;
    MaterialButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, b_dot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_textView = findViewById(R.id.result_textView);
        solution_textView = findViewById(R.id.solution_textView);

        assignId(buttonC, R.id.button_c);
        assignId(button_CBracket, R.id.button_close_bracket);
        assignId(button_OBracket, R.id.button_open_bracket);
        assignId(buttonAC, R.id.button_ac);
        assignId(button_div, R.id.button_divide);
        assignId(button_mult, R.id.button_multiplicate);
        assignId(button_plus, R.id.button_plus);
        assignId(button_minus, R.id.button_minus);
        assignId(button_eq, R.id.button_equals);
        assignId(b_dot, R.id.button_dot);
        assignId(b1, R.id.button_1);
        assignId(b2, R.id.button_2);
        assignId(b3, R.id.button_3);
        assignId(b4, R.id.button_4);
        assignId(b5, R.id.button_5);
        assignId(b6, R.id.button_6);
        assignId(b7, R.id.button_7);
        assignId(b8, R.id.button_8);
        assignId(b9, R.id.button_9);
        assignId(b0, R.id.button_0);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_textView.getText().toString();
        if(buttonText.equals("AC")){
            solution_textView.setText("");
            result_textView.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution_textView.setText(result_textView.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution_textView.setText(dataToCalculate);
        //q+tas

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("error")){
            result_textView.setText(finalResult);
        }
    }
    String getResult(String data){

        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finalResult;
        }catch (Exception e){
            return "error";
        }
    }
}