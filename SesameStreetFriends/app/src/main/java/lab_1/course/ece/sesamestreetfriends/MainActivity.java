package lab_1.course.ece.sesamestreetfriends;

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
        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnPicture = (Button) findViewById(R.id.btnPicture);
        final ImageView ivPicture = (ImageView) findViewById(R.id.ivPicture);
        final TextView tvName = (TextView) findViewById(R.id.tvName);


        btnPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Random random;
                random = new Random();
                int i;
                i = random.nextInt(6);
                switch (i) {
                    case 0:
                        tvName.setText(R.string.nameBigBird);
                        ivPicture.setImageResource(R.drawable.bigbird);
                        break;
                    case 1:
                        tvName.setText(R.string.nameCookieMonster);
                        ivPicture.setImageResource(R.drawable.cookiemonster);
                        break;
                    case 2:
                        tvName.setText(R.string.nameElmo);
                        ivPicture.setImageResource(R.drawable.elmo);
                        break;
                    case 3:
                        tvName.setText(R.string.nameGoldfish);
                        ivPicture.setImageResource(R.drawable.goldfish);
                        break;
                    case 4:
                        tvName.setText(R.string.nameJulia);
                        ivPicture.setImageResource(R.drawable.julia);
                        break;
                    case 5:
                        tvName.setText(R.string.nameMrNoodle);
                        ivPicture.setImageResource(R.drawable.mrnoodle);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        TextView tvName = (TextView) findViewById(R.id.tvName);
        ImageView ivPicture = (ImageView) findViewById(R.id.ivPicture);
        switch (item.getItemId()) {
            case R.id.id_menu_bigbird:
                tvName.setText(R.string.nameBigBird);
                ivPicture.setImageResource(R.drawable.bigbird);
                break;
            case R.id.id_menu_cookiemonster:
                tvName.setText(R.string.nameCookieMonster);
                ivPicture.setImageResource(R.drawable.cookiemonster);
                break;
            case R.id.id_menu_elmo:
                tvName.setText(R.string.nameElmo);
                ivPicture.setImageResource(R.drawable.elmo);
                break;
            case R.id.id_menu_goldfish:
                tvName.setText(R.string.nameGoldfish);
                ivPicture.setImageResource(R.drawable.goldfish);
                break;
            case R.id.id_menu_julia:
                tvName.setText(R.string.nameJulia);
                ivPicture.setImageResource(R.drawable.julia);
                break;
            case R.id.id_menu_mrnoodle:
                tvName.setText(R.string.nameMrNoodle);
                ivPicture.setImageResource(R.drawable.mrnoodle);
                break;
        }
        return true;
    }
}
