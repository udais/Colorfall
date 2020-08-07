package com.example.colorfall;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.Arrays;



@SuppressWarnings("unused")
public class galleryActivity extends Activity {
    private PlaceHolderView mGalleryView;
    private String list;

    //private class galleryActivity1 extends Fragment {


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        @SuppressLint("CutPasteId") TextView textView = findViewById(R.id.textView);
        @SuppressLint("CutPasteId") TextView m = findViewById(R.id.textView);
        @SuppressLint("CutPasteId") TextView textView1 = findViewById(R.id.textView);
        TextView m1 = findViewById(R.id.textView1);
        m.setBackgroundColor(Color.BLACK);
        m.setTextColor(Color.WHITE);

//WORK IN PROGRESS//
//
//        drawActivity class1 = new drawActivity();
//        String list1 = class1.getFiles();
//
//            m.setText("Gallery");
//
        //mGalleryView = findViewById(R.id.galleryView);
        //mGalleryView.addView(new GalleryItem(getResources().getDrawable(R.drawable.tempicon, null)));
        Intent intent = getIntent();

        //list = intent.getStringExtra("filenames");
        if(list!=null) {
            m.setText("Gallery Contents:");
           // m1.setText(list);
        }
        Context context = getApplicationContext();
        System.out.println(context.getFilesDir().getAbsolutePath());
        String[] fList = context.fileList();
       // m1.setText(Arrays.toString(fList));

        String gallery = Arrays.toString(fList);
        StringBuilder galleryList = new StringBuilder();
//        for(int i = 0; i<gallery.length(); i++){
//            if(gallery.charAt(i) !='[' || gallery.charAt(i)!=']'){
//                if(gallery.charAt(i)==','){
//                    galleryList += "\n";
//                }
//                galleryList += gallery.charAt(i);
//            }
//        }
        for (String s : fList) {
            galleryList.append(s);
            galleryList.append("\n");
        }
        m1.setText(galleryList.toString());

    }
}