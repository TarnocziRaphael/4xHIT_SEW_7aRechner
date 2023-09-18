/**
 * Rechner Aufgabe 4xHIT
 * @author Raphael Tarnoczi
 * @version 13-09-2023
 */
package sew.tarnoczi.rechner;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.output).setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        activateButtons();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public void berechnen (View V) {
        EditText wert1 = findViewById(R.id.input1);
        EditText wert2 = findViewById(R.id.input2);
        RadioGroup rechenart = findViewById(R.id.radioGroup);
        int idCheckedBox = rechenart.getCheckedRadioButtonId();
        RadioButton rb = findViewById(idCheckedBox);
        char c = rb.getText().charAt(0);
        TextView output = findViewById(R.id.output);
        int zahl1 = Integer.parseInt(wert1.getText().toString());
        int zahl2 = Integer.parseInt(wert2.getText().toString());
        int result;
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
    public void activateButtons() {
        findViewById(R.id.radioButton1).setEnabled(true);
        findViewById(R.id.radioButton2).setEnabled(true);
        findViewById(R.id.radioButton3).setEnabled(true);
        findViewById(R.id.radioButton4).setEnabled(true);
        findViewById(R.id.buttonBerechnen).setEnabled(true);
    }
    public void saveErgebnis(View v) {
        TextView output = findViewById(R.id.output);
        int ergebnis = Integer.parseInt(output.getText().toString());
        SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("Ergebnis", ergebnis);
        editor.commit();
        Toast.makeText(this, "Das Ergebnis wurde erfolgreich gespeichert!",Toast.LENGTH_SHORT).show();
    }
    public void loadErgebnis(View v) {
        SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
        int ergebnis = sharedPref.getInt("Ergebnis",0) ;
        TextView output = findViewById(R.id.output);
        if(ergebnis > 0) {
            output.setTextColor(Color.BLACK);
        }
        else if(ergebnis == 0){
            output.setTextColor(Color.WHITE);
        }
        else {
            output.setTextColor(Color.RED);
        }
        output.setText(String.valueOf(ergebnis));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.reset) {
            EditText input1 = findViewById(R.id.input1);
            input1.setText(null);
            EditText input2 = findViewById(R.id.input2);
            input2.setText(null);
            RadioGroup rg = findViewById(R.id.radioGroup);
            if(rg.getCheckedRadioButtonId() != 0) {
                RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
                rb.setChecked(false);
            }
            TextView tv = findViewById(R.id.output);
            tv.setText("0");
            return true;
        }
        else if(item.getItemId() == R.id.about) {
            Toast.makeText(this, "Raphael Tarnoczi\n2023-09-18",Toast.LENGTH_SHORT).show();
        }
        else {
            return false;
        }
        return false;
    }
}
