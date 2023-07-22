package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Watch extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ListModel> parrayList = new ArrayList<>();
    SharedPreferences sp;


    String[] productNameArray = {"Apple Smartwatch", "Dell Laptop", "iphone 13Pro max", "Nothinng Phone", "Bolt Smartwatch"};
    int[] productImageArray = {R.drawable.apple_smartwatch, R.drawable.dell_inspirion_15, R.drawable.iphone13, R.drawable.nothing_phone_1, R.drawable.power_bolt_smartwatch};
    String[] productPriceArray = {"35,000", "55,000", "1,10,000", "33,000", "5000"};
    String[] productDescriptionArray = {"Big screen. Huge impact. The challenge was to create a bigger display while barely expanding the dimensions of the watch itself. To do so, the display was completely re‑engineered reducing the borders by 40%, allowing for more screen area than both Series 6 and Series 3. Now that’s something to smile about.",
            "Dell 15 (2022) Amd Ryzen 5-3450U, 8Gb/1Tb Ssd, Windows 11+Mso'21, 15.6 Inches (39.61 Cms) Fhd Display, Platinum Silver (D560713Win9S, Inspiron 3515)",
            "17 cm (6.7-inch) Super Retina XDR display with ProMotion for a faster, more responsive feel\n" +
                    "Cinematic mode adds shallow depth of field and shifts focus automatically in your videos\n" +
                    "Pro camera system with new 12MP Telephoto, Wide and Ultra Wide cameras; LiDAR Scanner; 6x optical zoom range; macro photography; Photographic Styles, ProRes video, Smart HDR 4, Night mode, Apple ProRAW, 4K Dolby Vision HDR recording\n" +
                    "12MP TrueDepth front camera with Night mode, 4K Dolby Vision HDR recording\n" +
                    "A15 Bionic chip for lightning-fast performance\n" +
                    "Up to 28 hours of video playback, the best battery life ever in an iPhone",
            "Nothing phone (1) ; Internal, 128GB 8GB RAM, 256GB 8GB RAM, 256GB 12GB RAM ; Main Camera, Dual ; Features, LED flash, panorama, HDR ; Video, 4K@30fps, 1080p",
            "Display Size, 44mm ; Strap Material, Silicone ; Dial Shape, Square ; Display Type, TFT LED ; Ideal For, Unisex.\n"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.productphone_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(Watch.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());





        parrayList.add(new ListModel(productNameArray[0], productPriceArray[0], productDescriptionArray[0], productImageArray[0]));
        parrayList.add(new ListModel(productNameArray[3], productPriceArray[3], productDescriptionArray[3], productImageArray[3]));


//        HomeActivity h = new HomeActivity();
        ProductListAdapter adapter = new ProductListAdapter(this, parrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

