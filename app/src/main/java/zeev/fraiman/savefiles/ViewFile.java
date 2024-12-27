package zeev.fraiman.savefiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewFile extends AppCompatActivity {

    Context context;
    TextView tvView;
    Button bView;
    Intent takeit;
    String what, filename="", myDir="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);

        initComponents();

        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                what=takeit.getStringExtra("what");
                Toast.makeText(context, ""+what, Toast.LENGTH_SHORT).show();
                if (what.equals("internal"))  {
                    filename=takeit.getStringExtra("file");
                    Toast.makeText(context, ""+filename, Toast.LENGTH_SHORT).show();
                    try {
                        String sb = "";
                        String line;

                        FileInputStream fis = openFileInput(filename);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        while ((line = br.readLine()) != null) {
                            sb+=line;
                        }
                        br.close();

                        tvView.setText(sb);
                    } catch (FileNotFoundException e) {
                        Toast.makeText(context, "Error="+e.toString(),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(context, "Error="+e.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
                if (what.equals("internalMy"))  {
                    filename=takeit.getStringExtra("file");
                    myDir=takeit.getStringExtra("mydir");
                    File file = new File("data/data/"+getPackageName()+"/"+myDir+"/", filename);
                    try {
                        FileInputStream fis = new FileInputStream(String.valueOf(file));
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        String fileContents = sb.toString();
                        tvView.setText(fileContents);
                        br.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initComponents() {
        context=ViewFile.this;
        tvView=findViewById(R.id.tvView);
        bView=findViewById(R.id.bView);
        takeit=getIntent();
    }
}