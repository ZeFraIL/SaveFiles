package zeev.fraiman.savefiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SaveSherPref extends AppCompatActivity {

    Context context;
    Button bSaveShPr;
    EditText etName, etPass, etMail;
    String stName, stPass, stMail;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Pass = "passKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_sher_pref);

        initComponents();

        bSaveShPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stName=etName.getText().toString();
                stPass=etPass.getText().toString();
                stMail=etMail.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, stName);
                editor.putString(Pass, stPass);
                editor.putString(Email, stMail);
                editor.commit();

                Toast.makeText(SaveSherPref.this,
                        "Thanks",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initComponents() {
        context=SaveSherPref.this;
        bSaveShPr=findViewById(R.id.bSaveShPr);
        etName=findViewById(R.id.etName);
        etPass=findViewById(R.id.etPass);
        etMail=findViewById(R.id.etMail);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
    }
}