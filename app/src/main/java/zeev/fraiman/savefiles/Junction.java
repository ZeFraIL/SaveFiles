package zeev.fraiman.savefiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Junction extends AppCompatActivity  implements View.OnClickListener {

        Context context;
        Button bSaIn, bSaInMy, bSherPref;
        Intent go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junction);

        initComponents();

    }

    private void initComponents() {
        context=Junction.this;
        bSaIn=findViewById(R.id.bSaIn);
        bSaInMy=findViewById(R.id.bSaInMy);
        bSherPref=findViewById(R.id.bSherPref);
        bSaIn.setOnClickListener(this);
        bSaInMy.setOnClickListener(this);
        bSherPref.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view==bSaIn)  {
            go=new Intent(this, SaveInternal.class);
        }
        if (view==bSaInMy)  {
            go=new Intent(this, SaveInternalMyDir.class);
        }
        if (view==bSherPref)  {
            go=new Intent(this, SaveSherPref.class);
        }
        startActivity(go);
    }
}