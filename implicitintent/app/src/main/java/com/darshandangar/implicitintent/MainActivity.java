package com.darshandangar.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btncall,btnmsg,btnshare,btngmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncall = findViewById(R.id.btncall);
        btnmsg = findViewById(R.id.btnmsg);
        btnshare = findViewById(R.id.btnshare);
        btngmail = findViewById(R.id.btngmail);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent icall = new Intent(Intent.ACTION_DIAL);
                icall.setData(Uri.parse("tel: +919687528489"));
                startActivity(icall);
            }
        });

        btnmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imsg = new Intent(Intent.ACTION_SENDTO);
                imsg.setData(Uri.parse("smsto:"+Uri.encode("+919687528489")));
                imsg.putExtra("sms_body","please solve this issue asap.");
                startActivity(imsg);

            }
        });

        btngmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imail = new Intent(Intent.ACTION_SEND);
                imail.setType("message/rfc822");
                imail.putExtra(Intent.EXTRA_EMAIL,new String[]{"darshanahir57267@gmail.com"});
                imail.putExtra(Intent.EXTRA_SUBJECT,"Queries");
                imail.putExtra(Intent.EXTRA_TEXT,"Please Resolve this issue assap.");
                startActivity(Intent.createChooser(imail,"Email via"));
            }
        });

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ishare = new Intent(Intent.ACTION_SEND);
                ishare.setType("text/plain");
                ishare.putExtra(Intent.EXTRA_TEXT,"Download this amazing app,https://play.google.com/store/apps/details?id=com.hiteshvirani.googlyplayer");
                startActivity(Intent.createChooser(ishare,"Share via"));
            }
        });
    }
}