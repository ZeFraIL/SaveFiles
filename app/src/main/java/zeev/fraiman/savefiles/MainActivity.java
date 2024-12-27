package zeev.fraiman.savefiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    Button bEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(new Toolbar(this));

        tvWelcome=findViewById(R.id.tvWelcome);

        buildText();

        bEnter=findViewById(R.id.bEnter);
        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(MainActivity.this, Junction.class);
                startActivity(go);
            }
        });
    }

    private void buildText() {
        is=getResources().openRawResource(R.raw.welcome);
        isr=new InputStreamReader(is);
        br=new BufferedReader(isr);
        String st,all="";
        try {
            while ((st=br.readLine())!=null)
                all+=st+"\n";
            br.close();
        } catch (IOException e) {
            Toast.makeText(this,
                    "Error", Toast.LENGTH_SHORT).show();
        }
        tvWelcome.setText(all);
    }


}