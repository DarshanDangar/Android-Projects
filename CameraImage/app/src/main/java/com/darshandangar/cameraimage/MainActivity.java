package com.darshandangar.cameraimage;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int CAMERA_REQUEST_CODE = 100;
    private final int GELERY_REQUEST_CODE = 10;
    ImageView imageview;
    AppCompatButton camerabtn,galerybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = findViewById(R.id.imageview);
        camerabtn = findViewById(R.id.camera);
        galerybtn = findViewById(R.id.galery);

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera,CAMERA_REQUEST_CODE);
            }

        });

        galerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent igalery = new Intent(Intent.ACTION_PICK);
                igalery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igalery,GELERY_REQUEST_CODE);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                Bitmap img = (Bitmap)data.getExtras().get("data");
                imageview.setImageBitmap(img);

            } else if (requestCode == GELERY_REQUEST_CODE) {
                imageview.setImageURI(data.getData());
            }
        }
    }


//    protected void startActivityForResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==RESULT_OK) {
//            if (requestCode == CAMERA_REQUEST_CODE) {
//                Bitmap img = (Bitmap)data.getExtras().get("data");
//                imageview.setImageBitmap(img);
//
//            }
//        }
//    }

}