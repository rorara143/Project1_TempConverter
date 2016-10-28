package com.vitin.mylaptop.project1_tempconverter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class TempConverterActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private EditText fahrenheitEdiText;
    private TextView celciusTextView;
    private TextView Degrees;

    private String fahreinheintString;
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fahrenheitEdiText = (EditText) findViewById(R.id.fahrenheitEdiText);
        celciusTextView = (TextView) findViewById(R.id.celsiusTextView);

        fahrenheitEdiText.setOnEditorActionListener(this);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

@Override
public void onPause() {
    Editor editor = savedValues.edit();
    editor.putString ("fahreinheintString", fahreinheintString);
    editor.commit();
    super.onPause();
}

    @Override
    public void onResume() {
        super.onResume();
        fahreinheintString = savedValues.getString("fahreinheintString", "");
        fahrenheitEdiText.setText(fahreinheintString);
        calculateAndDisplay();
    }


    private void calculateAndDisplay() {
fahreinheintString = fahrenheitEdiText.getText().toString();
        float temp;

        if  (fahreinheintString.equals("")){
            temp= 0;

        } else
        {
            temp = Float.parseFloat(fahreinheintString);

        }
// calculate

       float c = (temp -32) * 5/9;
        NumberFormat Degrees = NumberFormat.getNumberInstance();
        celciusTextView.setText(Degrees.format(c));
    }


    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();

        return false;
    }
}

