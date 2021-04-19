package com.example.justscanit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendFeedbackActivity extends AppCompatActivity {
EditText edtTo,edtSubject,edtMessage;
Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);
        edtTo=findViewById(R.id.edt_to);
        edtSubject=findViewById(R.id.edt_subject);
        edtMessage=findViewById(R.id.edt_message);
        btnSend=findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW
                , Uri.parse("mailto:"+edtTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT,edtSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,edtMessage.getText().toString());
                startActivity(intent);
            }
        });

    }
}