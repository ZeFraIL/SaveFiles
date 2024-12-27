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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SaveInternalMyDir extends AppCompatActivity {

    Context context;
    Button bSaveMy, bViewInternalMy;
    EditText etFnameMy, etTextMy, etDirMy;
    String stFname, stText, stDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_internal_my_dir);

        initComponents();

        bSaveMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stDir=etDirMy.getText().toString();
                if (stDir.equals(""))  {
                    Toast.makeText(SaveInternalMyDir.this,
                            getString(R.string.emptyDir), Toast.LENGTH_LONG).show();
                    return;
                }
                stFname=etFnameMy.getText().toString();
                if (stFname.equals(""))  {
                    Toast.makeText(SaveInternalMyDir.this,
                            "Not found file name!", Toast.LENGTH_LONG).show();
                    return;
                }
                stFname+=".txt";
                stText=etTextMy.getText().toString();
                if (stText.equals(""))  {
                    Toast.makeText(SaveInternalMyDir.this,
                            "Not found text for file!", Toast.LENGTH_LONG).show();
                    return;
                }
                File myDir=new File("data/data/"+getPackageName()+"/"+stDir);
                myDir.mkdir();
                File myFile=new File(myDir,stFname);

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(myFile);
                    OutputStreamWriter osw=new OutputStreamWriter(fos);
                    BufferedWriter bw=new BufferedWriter(osw);
                    bw.write(stText);
                    bw.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(context,
                            "Error="+e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(context,
                            "Error="+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        bViewInternalMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stFname=etFnameMy.getText().toString();
                if (stFname.equals(""))  {
                    Toast.makeText(SaveInternalMyDir.this,
                            "Not found file name!", Toast.LENGTH_LONG).show();
                    return;
                }
                stFname+=".txt";
                stDir=etDirMy.getText().toString();
                if (stDir.equals(""))  {
                    Toast.makeText(SaveInternalMyDir.this,
                            getString(R.string.emptyDir), Toast.LENGTH_LONG).show();
                    return;
                }
                Intent go=new Intent(context, ViewFile.class);
                go.putExtra("what","internalMy");
                go.putExtra("file",stFname);
                go.putExtra("mydir",stDir);
                startActivity(go);
            }
        });
    }

    private void initComponents() {
        context=SaveInternalMyDir.this;
        etFnameMy=findViewById(R.id.etFnameMy);
        etTextMy=findViewById(R.id.etTextMy);
        etDirMy=findViewById(R.id.etDirMy);
        bSaveMy=findViewById(R.id.bSaveMy);
        bViewInternalMy=findViewById(R.id.bViewInternalMy);
    }
}