package com.mobarok.empty_project;


import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobarokOP.imageslider.ImageSlider;
import com.mobarokOP.imageslider.OnItemClickListener;
import com.mobarokOP.imageslider.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setAppearanceLightStatusBars(false);



        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        // Replace with your ImageSlider's ID

        ArrayList<SlideModel> imageList = new ArrayList<>();
        // ↑↑↑ Create a list of SlideModel objects

        imageList.add(new SlideModel(R.drawable.bukhari_head));
        // ↑↑↑ Only Load Image Form Drawable

        imageList.add(new SlideModel("https://cdn.pixabay.com/photo/2024/05/27/07/46/bee-8790316_640.jpg"));
        // ↑↑↑ Only Load Image From URL

        imageList.add(new SlideModel("https://cdn.pixabay.com/photo/2022/01/30/13/33/github-6980894_960_720.png","https://github.com/mobarokOP"));
        // ↑↑↑ Load Image From URL with Open Link

        imageList.add(new SlideModel(R.drawable.arif_bin_banner, "Title 2", "Description 2"));
        // ↑↑↑ Load Image From Drawable with Title and Description

        imageList.add(new SlideModel("https://i.ibb.co.com/qgDTsSk/465781910-518730594331898-9051687400886787353-n.jpg", "Mobarok Hossain", "Click Hare to Visit my Facebook", "https://www.facebook.com/mukutOP"));
        // ↑↑↑ Load Image From URL with Title, Description and Open Link

        imageSlider.setItems(MainActivity.this, imageList);
        // ↑↑↑ Set the list of SlideModel objects to the ImageSlider


        imageSlider.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Handle item click here via position
                Toast.makeText(MainActivity.this, "Clicked: "+position, Toast.LENGTH_SHORT).show();
            }
        });
        // ↑↑↑ Set an OnItemClickListener to handle item clicks





    }



}