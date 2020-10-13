package com.parkwaydrive.vod4u;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    String sourceText ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sourceText += "APP_NAME" + "%0A%E2%9C%85%0A";


        Button carbutton = (Button) findViewById(R.id.CarButton);
        carbutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                sourceText += "FREE " + "%0A";

                try {
                    Intent k = new Intent(MainActivity.this, Main2Activity.class);
                    k.putExtra("message", sourceText);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

        Button healthbutton = (Button) findViewById(R.id.HealthButton);
        healthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sourceText += "PREMIUM " + "%0A";

                try {
                    Intent k = new Intent(MainActivity.this, Main2Activity.class);
                    k.putExtra("message", sourceText);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }
}
