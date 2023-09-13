package sew.tarnoczi.rechner;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateRadioButtons();
        TextView output = findViewById(R.id.output);
        output.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                output.setText("0");
                output.setTextColor(Color.WHITE);
                return true;
            }
        });
    }
    public void berechnen (View V) {
        EditText wert1 = findViewById(R.id.input1);
        EditText wert2 = findViewById(R.id.input2);
        RadioGroup rechenart = findViewById(R.id.radioGroup);
        int idCheckedBox = rechenart.getCheckedRadioButtonId();
        RadioButton rb = findViewById(idCheckedBox);
        char c = rb.getText().charAt(0);
        TextView output = findViewById(R.id.output);
        double zahl1 = Double.parseDouble(wert1.getText().toString());
        double zahl2 = Double.parseDouble(wert2.getText().toString());
        double result;
        switch(c) {
            case '+':
                result = zahl1+zahl2;
                break;
            case '-':
                result = zahl1 - zahl2;
                break;
            case '*':
                result = zahl1 * zahl2;
                break;
            case '/':
                result = zahl1 / zahl2;
                break;
            default:
                result = 0;
        }
        if(result > 0) {
            output.setTextColor(Color.BLACK);
        }
        else if(result == 0){
            output.setTextColor(Color.WHITE);
        }
        else {
            output.setTextColor(Color.RED);
        }
        String text = String.valueOf(result);
        output.setText(text);
    }
    public void activateRadioButtons() {
        findViewById(R.id.radioButton1).setEnabled(true);
        findViewById(R.id.radioButton2).setEnabled(true);
        findViewById(R.id.radioButton3).setEnabled(true);
        findViewById(R.id.radioButton4).setEnabled(true);
    }
}