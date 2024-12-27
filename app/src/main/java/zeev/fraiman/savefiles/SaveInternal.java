package zeev.fraiman.savefiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SaveInternal extends AppCompatActivity {

    Context context;
    EditText etFname, etText;
    String stFname, stText;
    Button bAdd, bRewrite, bViewInternal;
    private FileOutputStream fos;
    private OutputStreamWriter osw;
    private BufferedWriter bw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_internal);

        initComponents();

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfo();

                fos = null;
                try {
                    fos = openFileOutput(stFname+".txt",
                            Context.MODE_APPEND);
                    osw=new OutputStreamWriter(fos);
                    bw=new BufferedWriter(osw);
                    bw.append(stText);
                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        bRewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfo();

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(stFname+".txt",
                            Context.MODE_PRIVATE);
                    OutputStreamWriter osw=new OutputStreamWriter(fos);
                    BufferedWriter bw=new BufferedWriter(osw);
                    bw.write(stText);
                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        bViewInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stFname=etFname.getText().toString();
                if (stFname.equals(""))  {
                    Toast.makeText(SaveInternal.this,
                            "Not found file name!", Toast.LENGTH_LONG).show();
                    return;
                }
                stFname+=".txt";
                Intent go=new Intent(context, ViewFile.class);
                go.putExtra("what","internal");
                go.putExtra("file",stFname);
                startActivity(go);
            }
        });
    }

    private void takeInfo() {
        stFname=etFname.getText().toString();
        if (stFname.equals(""))  {
            Toast.makeText(SaveInternal.this,
                    "Not found file name!", Toast.LENGTH_LONG).show();
            return;
        }
        stText=etText.getText().toString();
        if (stText.equals(""))  {
            Toast.makeText(SaveInternal.this,
                    "Not found text for file!", Toast.LENGTH_LONG).show();
            return;
        }

    }

    private void initComponents() {
        context=SaveInternal.this;
        etFname=findViewById(R.id.etFname);
        etText=findViewById(R.id.etText);
        bAdd=findViewById(R.id.bAdd);
        bRewrite=findViewById(R.id.bRewrite);
        bViewInternal=findViewById(R.id.bViewInternal);
    }
}