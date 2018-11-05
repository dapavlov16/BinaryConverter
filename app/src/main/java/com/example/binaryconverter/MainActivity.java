package com.example.binaryconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int getInteger(TextView v){
        return Integer.valueOf(v.getText().toString());
    }
    /*public int rand(){
        Random r = new Random();
        return r.nextInt(256);
    }*/
    public int rand(Button plus, Button minus) {
        Random r = new Random();
        int i = r.nextInt(256);
        if(i == 0){
            minus.setEnabled(false);
        }
        else if(i == 255){
            plus.setEnabled(false);
        }
        else {
            minus.setEnabled(true);
            plus.setEnabled(true);
        }
        return i;
    }
    EditText decimal;
    TextView binary1, binary2, binary3, binary4, binary5, binary6, binary7, binary8, binaryTotal;
    Button plus, minus, roll;
    Switch randomMode, b2d;
    int temp;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:   //plus
                    if(getInteger(decimal) < 255) {
                        decimal.setText(Integer.toString(getInteger(decimal) + 1));
                        minus.setEnabled(true);
                    }
                    if(getInteger(decimal) == 255) {
                        plus.setEnabled(false);
                    }
                    break;
                case R.id.button2:  //minus
                    if(getInteger(decimal) > 0){
                        decimal.setText(Integer.toString(getInteger(decimal) - 1) );
                        plus.setEnabled(true);
                    }
                    if(getInteger(decimal) == 0) {
                        minus.setEnabled(false);
                    }
                    break;
                case R.id.button3:  //roll
                    decimal.setText(Integer.toString(rand(plus, minus)));
                    break;
            }
        }
    };
    final CompoundButton.OnCheckedChangeListener switchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.switch1:  //random mode
                    roll.setVisibility(randomMode.isChecked() ? View.VISIBLE : View.GONE);
                    minus.setVisibility(randomMode.isChecked() || !b2d.isChecked() ? View.GONE : View.VISIBLE);
                    plus.setVisibility(minus.getVisibility());
                    b2d.setEnabled(!randomMode.isChecked());
                    decimal.setClickable(!randomMode.isChecked() && b2d.isChecked());
                    decimal.setFocusable(!randomMode.isChecked() && b2d.isChecked());
                    decimal.setFocusableInTouchMode(!randomMode.isChecked() && b2d.isChecked());
                    if(randomMode.isChecked() || b2d.isChecked()){
                        decimal.addTextChangedListener(watcher);
                    }
                    else {
                        decimal.removeTextChangedListener(watcher);
                    }
                    binary1.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary2.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary3.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary4.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary5.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary6.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary7.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    binary8.setOnTouchListener(randomMode.isChecked() || b2d.isChecked() ? null : touchListener);
                    break;
                case R.id.switch2:  //changer (b2d)
                    if (b2d.isChecked()) {
                        minus.setVisibility(randomMode.isChecked() ? View.GONE : View.VISIBLE);
                        plus.setVisibility(randomMode.isChecked() ? View.GONE : View.VISIBLE);
                        decimal.setClickable(!randomMode.isChecked());
                        decimal.setFocusable(!randomMode.isChecked());
                        decimal.setFocusableInTouchMode(!randomMode.isChecked());
                        decimal.addTextChangedListener(watcher);
                        binary1.setOnTouchListener(null);
                        binary2.setOnTouchListener(null);
                        binary3.setOnTouchListener(null);
                        binary4.setOnTouchListener(null);
                        binary5.setOnTouchListener(null);
                        binary6.setOnTouchListener(null);
                        binary7.setOnTouchListener(null);
                        binary8.setOnTouchListener(null);
                    } else {
                        minus.setVisibility(View.GONE);
                        plus.setVisibility(View.GONE);
                        decimal.setClickable(false);
                        decimal.setFocusable(false);
                        decimal.setFocusableInTouchMode(false);
                        decimal.removeTextChangedListener(watcher);
                        binary1.setOnTouchListener(touchListener);
                        binary2.setOnTouchListener(touchListener);
                        binary3.setOnTouchListener(touchListener);
                        binary4.setOnTouchListener(touchListener);
                        binary5.setOnTouchListener(touchListener);
                        binary6.setOnTouchListener(touchListener);
                        binary7.setOnTouchListener(touchListener);
                        binary8.setOnTouchListener(touchListener);
                    }
                    break;
            }
        }
    };
    final View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.textView:
                    if (binary1.getText().toString().equals("0")) {
                        binary1.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 128));
                    } else {
                        binary1.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 128));
                    }
                    break;
                case R.id.textView2:
                    if (binary2.getText().toString().equals("0")) {
                        binary2.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 64));
                    } else {
                        binary2.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 64));
                    }
                    break;
                case R.id.textView3:
                    if (binary3.getText().toString().equals("0")) {
                        binary3.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 32));
                    } else {
                        binary3.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 32));
                    }
                    break;
                case R.id.textView4:
                    if (binary4.getText().toString().equals("0")) {
                        binary4.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 16));
                    } else {
                        binary4.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 16));
                    }
                    break;
                case R.id.textView5:
                    if (binary5.getText().toString().equals("0")) {
                        binary5.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 8));
                    } else {
                        binary5.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 8));
                    }
                    break;
                case R.id.textView6:
                    if (binary6.getText().toString().equals("0")) {
                        binary6.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 4));
                    } else {
                        binary6.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 4));
                    }
                    break;
                case R.id.textView7:
                    if (binary7.getText().toString().equals("0")) {
                        binary7.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 2));
                    } else {
                        binary7.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) - 2));
                    }
                    break;
                case R.id.textView8:
                    if (binary8.getText().toString().equals("0")) {
                        binary8.setText("1");
                        decimal.setText(Integer.toString(getInteger(decimal) + 1));
                    } else {
                        binary8.setText("0");
                        decimal.setText(Integer.toString(getInteger(decimal) -1));
                    }
                    break;
            }
            plus.setEnabled(getInteger(decimal) != 255);
            minus.setEnabled(getInteger(decimal) != 0);
            return false;
        }
    };

    TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals("")) {
                temp = Integer.valueOf(s.toString());
                if (temp > 255) {
                    temp = 255;
                    s.clear();
                    s.append("255");
                }
                plus.setEnabled(temp != 255);
                minus.setEnabled(temp != 0);
                binary1.setText(Integer.toString(temp / 128));
                binary2.setText(Integer.toString((temp % 128) / 64));
                binary3.setText(Integer.toString((temp % 64) / 32));
                binary4.setText(Integer.toString((temp % 32) / 16));
                binary5.setText(Integer.toString((temp % 16) / 8));
                binary6.setText(Integer.toString((temp % 8) / 4));
                binary7.setText(Integer.toString((temp % 4) / 2));
                binary8.setText(Integer.toString(temp % 2));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimal = findViewById(R.id.textView9);
        decimal.setTransformationMethod(null);
        binaryTotal = findViewById(R.id.textView10);
        binary1 = findViewById(R.id.textView);
        binary2 = findViewById(R.id.textView2);
        binary3 = findViewById(R.id.textView3);
        binary4 = findViewById(R.id.textView4);
        binary5 = findViewById(R.id.textView5);
        binary6 = findViewById(R.id.textView6);
        binary7 = findViewById(R.id.textView7);
        binary8 = findViewById(R.id.textView8);
        plus = findViewById(R.id.button);
        minus = findViewById(R.id.button2);
        roll = findViewById(R.id.button3);
        randomMode = findViewById(R.id.switch1);
        b2d = findViewById(R.id.switch2);
        plus.setOnClickListener(clickListener);
        minus.setOnClickListener(clickListener);
        roll.setOnClickListener(clickListener);
        randomMode.setOnCheckedChangeListener(switchListener);
        b2d.setOnCheckedChangeListener(switchListener);
        binary1.setOnTouchListener(touchListener);
        binary2.setOnTouchListener(touchListener);
        binary3.setOnTouchListener(touchListener);
        binary4.setOnTouchListener(touchListener);
        binary5.setOnTouchListener(touchListener);
        binary6.setOnTouchListener(touchListener);
        binary7.setOnTouchListener(touchListener);
        binary8.setOnTouchListener(touchListener);
    }
}
