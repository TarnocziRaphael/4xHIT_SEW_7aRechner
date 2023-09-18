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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
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
        Spinner spinner = (Spinner) findViewById(R.id.rechentyp_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.rechentyp_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
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
        Spinner spinner = (Spinner) findViewById(R.id.rechentyp_spinner);
        String rechenart = spinner.getSelectedItem().toString();
        TextView output = findViewById(R.id.output);
        int zahl1 = Integer.parseInt(wert1.getText().toString());
        int zahl2 = Integer.parseInt(wert2.getText().toString());
        int result;
        switch(rechenart) {
            case "+":
                result = zahl1+zahl2;
                break;
            case "-":
                result = zahl1 - zahl2;
                break;
            case "*":
                result = zahl1 * zahl2;
                break;
            case "/":
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
            Spinner spinner = (Spinner) findViewById(R.id.rechentyp_spinner);
            spinner.setSelection(0);
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
