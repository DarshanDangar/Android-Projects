package com.darshandangar.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        //single button alert dialog
//        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle("Tearms & Condition");
//        alertDialog.setIcon(R.drawable.info_icon);
//        alertDialog.setMessage("Have you read all tearms & condition");
//
//        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes,I've read",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Yes,you can proceed now..", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        alertDialog.show();
//
//        //double button dialog box
//
//        AlertDialog.Builder delDialog = new AlertDialog.Builder(MainActivity.this);
//        delDialog.setTitle("Delete");
//        delDialog.setIcon(R.drawable.delete_icon);
//        delDialog.setMessage("Are you sure want to delete");
//
//        delDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Item deleted.", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        delDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Item not delete.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        delDialog.show();
//
//
//    }
//
//    //triple button
//
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder exitDialog = new AlertDialog.Builder(MainActivity.this);
//        exitDialog.setTitle("Exit");
//        exitDialog.setMessage("Are you sure want to exit");
//        exitDialog.setIcon(R.drawable.exit_icon);
//
//        exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MainActivity.super.onBackPressed();
//            }
//        });
//
//        exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        exitDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Operation canceled", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        exitDialog.show();
//    }


        //custom dialog
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog_layout);

        Button okbtn = dialog.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=com.hiteshvirani.googlyplayer"));
                intent.setPackage("com.android.vending");
                startActivity(intent);

            }
        });
        dialog.show();

    }
}