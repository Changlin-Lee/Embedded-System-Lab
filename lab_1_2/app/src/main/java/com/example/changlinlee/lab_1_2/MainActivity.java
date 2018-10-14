package com.example.changlinlee.lab_1_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button btnPicture = findViewById(R.id.btnPicture);
        final ImageView ivPicture = findViewById(R.id.ivPicture);
        final TextView tvName = findViewById(R.id.tvName);

        btnPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i = (int) (12 * (Math.random()));
                switch(i) {
                    case 0: ivPicture.setImageResource(R.drawable.bigbird);
                            tvName.setText(R.string.bird);
                            break;
                    case 1: ivPicture.setImageResource(R.drawable.cookiemonster);
                            tvName.setText(R.string.monster);
                            break;
                    case 2: ivPicture.setImageResource(R.drawable.elmo);
                            tvName.setText(R.string.mo);
                            break;
                    case 3: ivPicture.setImageResource(R.drawable.goldfish);
                            tvName.setText(R.string.fish);
                            break;
                    case 4: ivPicture.setImageResource(R.drawable.julia);
                            tvName.setText(R.string.lia);
                            break;
                    case 5: ivPicture.setImageResource(R.drawable.mrnoodle);
                            tvName.setText(R.string.noodle);
                            break;
                }



            }
        });

        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
