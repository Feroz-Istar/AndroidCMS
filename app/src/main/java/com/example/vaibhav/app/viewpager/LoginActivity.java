package com.example.vaibhav.app.viewpager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vaibhav.app.R;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    private EditText ppt_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};


        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        button = (Button) findViewById(R.id.signin);
        ppt_id = (EditText) findViewById(R.id.email);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ppt_id.getText() != null && !ppt_id.getText().toString().trim().equalsIgnoreCase("")){
                    Intent i = new Intent(LoginActivity.this,SampleActivity.class);
                    i.putExtra("ppt_id", ppt_id.getText().toString());
                    startActivity(i);

                }else{
                    ppt_id.setError("Please Enter a ppt id to proceed");
                    ppt_id.requestFocus();
                }
            }
        });

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }



}
