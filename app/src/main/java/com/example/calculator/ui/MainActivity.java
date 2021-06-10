package com.example.calculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.R;
import com.example.calculator.domain.Calculation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView enteredText;
    private String strEnterText = "";

    private Calculation calculation;

    private static final String RESULT = "RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            calculation = new Calculation("0");
        } else {
            calculation = savedInstanceState.getParcelable(RESULT);
        }

        initView();
    }

    private void initView() {
        initTextView();
        initButtuns();
    }

    private void initTextView() {
        enteredText = findViewById(R.id.enteredText);
    }


    public void initButtuns() {
        Button btn0 = findViewById(R.id.key_0);
        Button btn1 = findViewById(R.id.key_1);
        Button btn2 = findViewById(R.id.key_2);
        Button btn3 = findViewById(R.id.key_3);
        Button btn4 = findViewById(R.id.key_4);
        Button btn5 = findViewById(R.id.key_5);
        Button btn6 = findViewById(R.id.key_6);
        Button btn7 = findViewById(R.id.key_7);
        Button btn8 = findViewById(R.id.key_8);
        Button btn9 = findViewById(R.id.key_9);
        Button btnDot = findViewById(R.id.key_dot);
        Button btnClean = findViewById(R.id.key_clean);
        Button btnChSign = findViewById(R.id.key_change_sign);
        Button btnPercent = findViewById(R.id.key_percent);
        Button btnSplit = findViewById(R.id.key_split);
        Button btnMultiplu = findViewById(R.id.key_multiply);
        Button btnMinus = findViewById(R.id.key_minus);
        Button btnPlus = findViewById(R.id.key_plus);
        Button btnResult = findViewById(R.id.key_result);
        Button[] buttons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnClean, btnChSign, btnPercent, btnSplit, btnMultiplu, btnMinus, btnPlus, btnResult};
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.key_9) {
            enterTextAdd('9');
        }
        if (v.getId() == R.id.key_8) {
            enterTextAdd('8');
        }
        if (v.getId() == R.id.key_7) {
            enterTextAdd('7');
        }
        if (v.getId() == R.id.key_6) {
            enterTextAdd('6');
        }
        if (v.getId() == R.id.key_5) {
            enterTextAdd('5');
        }
        if (v.getId() == R.id.key_4) {
            enterTextAdd('4');
        }
        if (v.getId() == R.id.key_3) {
            enterTextAdd('3');
        }
        if (v.getId() == R.id.key_2) {
            enterTextAdd('2');
        }
        if (v.getId() == R.id.key_1) {
            enterTextAdd('1');
        }
        if (v.getId() == R.id.key_0) {
            enterTextAdd('0');
        }
        if (v.getId() == R.id.key_plus) {

            enterTextAdd('\u002B');
        }
        if (v.getId() == R.id.key_minus) {
            enterTextAdd('\u002D');
        }
        if (v.getId() == R.id.key_split) {
            enterTextAdd('\u00F7');
        }
        if (v.getId() == R.id.key_multiply) {
            enterTextAdd('\u00D7');
        }
        if (v.getId() == R.id.key_dot) {
            enterTextAdd('\u002E');
        }
        if (v.getId() == R.id.key_clean) {
            clearView();
        }
        if (v.getId() == R.id.key_result) {

            result();
        }

    }

    private void enterTextAdd(char c) {
        strEnterText = String.format("%s%c", strEnterText, c);
        enteredText.setText(strEnterText);
    }

    private void clearView() {
        strEnterText = "";
        enteredText.setText(strEnterText);
        calculation.clearData();
    }

    private void result() {
        calculation.stringToChar(strEnterText);
        strEnterText = calculation.counter();
        calculation.clearData();
        enteredText.setText(strEnterText);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(RESULT, calculation);
        super.onSaveInstanceState(outState);
    }
}
