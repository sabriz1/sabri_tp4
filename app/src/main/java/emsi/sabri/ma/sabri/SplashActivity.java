package emsi.sabri.ma.sabri;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splach_activity);

        logo = findViewById(R.id.logo);

        //Appliquer une animation de rotation sur le logo
        //res->anim->anim.xml

        logo.animate().rotation(360f).setDuration(2000); logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
        logo.animate().translationYBy(1000f).setDuration(2000);
        logo.animate().alpha(0f).setDuration(6000);
        Thread t = new Thread()
        {
            @Override public void run()
            { try
            { sleep(2000);
                Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                startActivity(intent);
            } catch (InterruptedException e) { e.printStackTrace();
            }
            }
        };
        t.start();




    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

}


