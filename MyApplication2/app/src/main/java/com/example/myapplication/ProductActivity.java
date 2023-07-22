package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ListModel> arrayList = new ArrayList<>();
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
        setContentView(R.layout.activity_product);
        getSupportActionBar().setTitle("Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.product_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());




        for (int i = 0; i < productNameArray.length ; i++) {



            arrayList.add(new ListModel(productNameArray[i], productPriceArray[i], productDescriptionArray[i], productImageArray[i]));

        }
        HomeActivity h = new HomeActivity();
        ProductListAdapter adapter = new ProductListAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);


//        if (new ConnectionDetector(ProductActivity.this).isConnectingToInternet()) {
//            new getProductData().execute();
//        } else {
//            new ConnectionDetector(ProductActivity.this).connectiondetect();
//        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class getProductData extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ProductActivity.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            if (sp.getString(ConstantSp.CATEGORY_ID, "").equalsIgnoreCase("")) {
                return new MakeServiceCall().MakeServiceCall(ConstantUrl.PRODUCT_ALL_URL, MakeServiceCall.GET, new HashMap<>());
            }
            else{
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("categoryId",sp.getString(ConstantSp.CATEGORY_ID,""));
                return new MakeServiceCall().MakeServiceCall(ConstantUrl.PRODUCT_URL, MakeServiceCall.POST, hashMap);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getBoolean("Status") == true) {
                    JSONArray jsonArray = object.getJSONArray("ProductData");
                    arrayList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ListModel list = new ListModel();
                        list.setName(jsonObject.getString("name"));
                        list.setPrice(jsonObject.getString("price"));

                        list.setDescription(jsonObject.getString("description"));
                        list.setImage(jsonObject.getInt("image"));
                        arrayList.add(list);
                    }
                    ProductListAdapter productAdapter = new ProductListAdapter(ProductActivity.this, arrayList);
                    recyclerView.setAdapter(productAdapter);
                } else {
                    new CommonMethod(ProductActivity.this, object.getString("Message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                new CommonMethod(ProductActivity.this, e.getMessage());
            }
        }
    }

}