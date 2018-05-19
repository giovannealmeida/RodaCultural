package br.gov.rodacultural.rodacultural;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.gov.rodacultural.rodacultural.activities.LoginActivity;
import br.gov.rodacultural.rodacultural.utils.SessionHelper;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    final SessionHelper session = new SessionHelper(SplashActivity.this);
                    if(session.isLogged()){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
