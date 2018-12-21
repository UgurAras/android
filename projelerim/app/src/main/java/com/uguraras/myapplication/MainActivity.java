package com.uguraras.myapplication;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    TextToSpeech tts;
    Button btnKonus;
    EditText etGiris;
    Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKonus = (Button) findViewById(R.id.btnKonus);
        etGiris= (EditText) findViewById(R.id.etGiris);
        tts = new TextToSpeech(context,this);
        btnKonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                convertTextToSpeech();
            }
        });
    }
    @Override
    public void onInit(int status) {
        if(status==tts.SUCCESS){
          int sonuc= tts.setLanguage(Locale.ENGLISH);
          if(sonuc==tts.LANG_MISSING_DATA || sonuc == tts.LANG_NOT_SUPPORTED){
              Toast.makeText(context, "Bu dil Desteklenmiyor...", Toast.LENGTH_SHORT).show();
          } else{

              convertTextToSpeech();
          }

        } else {
            Toast.makeText(context, "Başarısız ...", Toast.LENGTH_SHORT).show();
        }
      }

      private void convertTextToSpeech(){

        String text = etGiris.getText().toString();

        if(null==text || "".equals(text)){

            Toast.makeText(context, "Lütfen boş geçmeyiniz", Toast.LENGTH_SHORT).show();
            return;
        }
        tts.speak(text,tts.QUEUE_FLUSH,null);

      }
}
