package com.example.root.maasmark_1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpeechTest extends AppCompatActivity {

    private TextView result_text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_test);
        result_text= (TextView) findViewById(R.id.result);
        EditText amnt = (EditText) findViewById(R.id.amount);

        String a;
        Button next = (Button) findViewById(R.id.submittt);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent data = new Intent(SpeechTest.this, MapsActivity.class);
                //data.putExtra("am", amnt.getText().toString());
                data.putExtra("am", result_text.getText().toString());
                startActivity(data);
                //Intent maps = new Intent(DetailsActivity.this, MapsActivity.class);

            }


        });

    }

    public void speech(View v){

        prompt();
    }

    public void prompt(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try{
            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(SpeechTest.this, "Sorry", Toast.LENGTH_LONG).show();
        }
    }


    public void onActivityResult(int request_code, int result_code, Intent i){
        EditText amnt = (EditText) findViewById(R.id.amount);
        super.onActivityResult(request_code, result_code, i);

        switch (request_code){
            case 100: if(result_code == RESULT_OK && i != null)
            {
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                result_text.setText(result.get(0));
                amnt.setText(result.get(0));
            }

        }

    }

}
