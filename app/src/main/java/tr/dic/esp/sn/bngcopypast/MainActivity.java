package tr.dic.esp.sn.bngcopypast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tr.dic.esp.sn.bngcopypast.service.CopyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        final Button bnt = (Button)findViewById(R.id.Start);
        final Button button = (Button) findViewById(R.id.connexion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bnt.isEnabled()){
                    bnt.setEnabled(true);
                    bnt.setBackgroundColor(Color.GREEN);
                }
            }
        });

        bnt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView add = (TextView)MainActivity.this.findViewById(R.id.champAddr);
                TextView port = (TextView)MainActivity.this.findViewById(R.id.champPort);
                Log.w("add", add.getText().toString());
                Log.w("port", port.getText().toString());
                SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("save",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("add", ""+add.getText());
                editor.putString("port", ""+port.getText());
                editor.commit();
                startService(new Intent(MainActivity.this, CopyService.class));
            }
        });
    }
}
