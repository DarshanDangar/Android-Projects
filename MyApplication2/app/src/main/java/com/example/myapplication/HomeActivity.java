package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TextView title;
    SharedPreferences sp;

    RecyclerView recyclerView,productRecyclerView;

    String[] categoryNameArray = {"Smartphone","Laptop","Smart Watch","Tablet"};
    int[] categoryImageArray = {R.drawable.all_ph,R.drawable.all_laptop,R.drawable.all_smartwatch,R.drawable.all_tab};

    ArrayList<CategoryList> arrayList;

    String[] productNameArray = {"Apple Smartwatch","Dell Laptop","iphone 13Pro max","Nothinng Phone","Bolt Smartwatch"};
    int[] productImageArray = {R.drawable.apple_smartwatch,R.drawable.dell_inspirion_15,R.drawable.iphone13,R.drawable.nothing_phone_1,R.drawable.power_bolt_smartwatch};
    String[] productPriceArray = {"35,000","55,000","1,10,000","33,000","5000"};
    String[] productDescriptionArray = {"Big screen. Huge impact. The challenge was to create a bigger display while barely expanding the dimensions of the watch itself. To do so, the display was completely re‑engineered reducing the borders by 40%, allowing for more screen area than both Series 6 and Series 3. Now that’s something to smile about.",
            "Dell 15 (2022) Amd Ryzen 5-3450U, 8Gb/1Tb Ssd, Windows 11+Mso'21, 15.6 Inches (39.61 Cms) Fhd Display, Platinum Silver (D560713Win9S, Inspiron 3515)",
            "17 cm (6.7-inch) Super Retina XDR display with ProMotion for a faster, more responsive feel\n" +
                    "Cinematic mode adds shallow depth of field and shifts focus automatically in your videos\n" +
                    "Pro camera system with new 12MP Telephoto, Wide and Ultra Wide cameras; LiDAR Scanner; 6x optical zoom range; macro photography; Photographic Styles, ProRes video, Smart HDR 4, Night mode, Apple ProRAW, 4K Dolby Vision HDR recording\n" +
                    "12MP TrueDepth front camera with Night mode, 4K Dolby Vision HDR recording\n" +
                    "A15 Bionic chip for lightning-fast performance\n" +
                    "Up to 28 hours of video playback, the best battery life ever in an iPhone\n",
            "Nothing phone (1) ; Internal, 128GB 8GB RAM, 256GB 8GB RAM, 256GB 12GB RAM ; Main Camera, Dual ; Features, LED flash, panorama, HDR ; Video, 4K@30fps, 1080p",
            "Display Size, 44mm ; Strap Material, Silicone ; Dial Shape, Square ; Display Type, TFT LED ; Ideal For, Unisex.\n"};
    ArrayList<ProductList> productArrayList = new ArrayList<>();
    TextView viewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);
        title = findViewById(R.id.home_title);

        title.setText("Welcome to\n"+sp.getString(ConstantSp.EMAIL,""));

        viewAll = findViewById(R.id.home_view_all);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.CATEGORY_ID,"").commit();
                new CommonMethod(HomeActivity.this,ProductActivity.class);
            }
        });

        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        arrayList = new ArrayList<>();
        for (int i=0;i<categoryNameArray.length;i++){
            CategoryList list = new CategoryList();
            list.setName(categoryNameArray[i]);
            list.setImage(categoryImageArray[i]);
            arrayList.add(list);
        }

        CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this,arrayList);
        recyclerView.setAdapter(adapter);

        productRecyclerView = findViewById(R.id.home_product_recyclerview);
        productRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        productRecyclerView.setItemAnimator(new DefaultItemAnimator());
        productRecyclerView.setNestedScrollingEnabled(false);

          // ProductList list;
          // ArrayList<ProductList> productArrayList = new ArrayList<>();
        for(int i=0;i<productNameArray.length;i++){


            productArrayList.add(new ProductList(productNameArray[i],productPriceArray[i],productDescriptionArray[i],productImageArray[i]));
//            list.setName(productNameArray[i]);
//            list.setPrice(productPriceArray[i]);
//            list.setDescription(productDescriptionArray[i]);
//            list.setImage(productImageArray[i]);
//            productList.add(list);
        }

        ProductAdapter productAdapter = new ProductAdapter(this,productArrayList);
        productRecyclerView.setAdapter(productAdapter);

//        if (new ConnectionDetector(HomeActivity.this).isConnectingToInternet()) {
//            new getCategoryData().execute();
//            new getProductData().execute();
//        } else {
//            new ConnectionDetector(HomeActivity.this).connectiondetect();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
        {
            onBackPressed();
        }
        if(id==R.id.home_menu_logout){
            sp.edit().clear().commit();
            new CommonMethod(HomeActivity.this,MainActivity.class);
        }
        if(id==R.id.home_menu_chat){
            new CommonMethod(HomeActivity.this,ChatActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //finishAffinity();
        alertMethod();
    }

    private void alertMethod() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Exit Alert");
        builder.setMessage("Are You Sure Want To Exit!");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setNeutralButton("Rte Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                new CommonMethod(HomeActivity.this,"Rate Us");
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

//    private class getCategoryData extends AsyncTask<String,String,String> {
//
//        ProgressDialog pd;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd = new ProgressDialog(HomeActivity.this);
//            pd.setMessage("Please Wait...");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return new MakeServiceCall().MakeServiceCall(ConstantUrl.CATEGORY_URL,MakeServiceCall.GET,new HashMap<>());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            pd.dismiss();
//            try {
//                JSONObject object = new JSONObject(s);
//                if (object.getBoolean("Status") == true) {
//                    JSONArray jsonArray = object.getJSONArray("CategoryData");
//                    arrayList = new ArrayList<>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        CategoryList list = new CategoryList();
//                       // list.(jsonObject.getString("id"));
//                        list.setName(jsonObject.getString("name"));
//                        list.setImg(Integer.parseInt(jsonObject.getString("img")));
//                        arrayList.add(list);
//                    }
//                    CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this, arrayList);
//                    recyclerView.setAdapter(adapter);
//                }
//                else{
//                    new CommonMethod(HomeActivity.this,object.getString("Message"));
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                new CommonMethod(HomeActivity.this,e.getMessage());
//            }
//        }
//    }
//
//    private class getProductData extends AsyncTask<String,String,String> {
//
//        ProgressDialog pd;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd = new ProgressDialog(HomeActivity.this);
//            pd.setMessage("Please Wait...");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return new MakeServiceCall().MakeServiceCall(ConstantUrl.CATEGORY_URL,MakeServiceCall.GET,new HashMap<>());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            pd.dismiss();
//            try {
//                JSONObject object = new JSONObject(s);
//                if (object.getBoolean("Status") == true) {
//                    JSONArray jsonArray = object.getJSONArray("CategoryData");
//                    arrayList = new ArrayList<>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        CategoryList list = new CategoryList();
//                        // list.(jsonObject.getString("id"));
//                        list.setName(jsonObject.getString("name"));
//                        list.setImg(Integer.parseInt(jsonObject.getString("img")));
//                        arrayList.add(list);
//                    }
//                    CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this, arrayList);
//                    recyclerView.setAdapter(adapter);
//                }
//                else{
//                    new CommonMethod(HomeActivity.this,object.getString("Message"));
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                new CommonMethod(HomeActivity.this,e.getMessage());
//            }
//        }
//    }
}