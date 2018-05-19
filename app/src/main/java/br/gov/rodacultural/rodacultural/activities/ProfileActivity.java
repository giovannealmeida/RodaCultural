package br.gov.rodacultural.rodacultural.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.models.FeedItem;

public class ProfileActivity extends AppCompatActivity {

    private String PROFILE_PLACEHOLDER = "https://projects.scpr.org/static-files/_v4/images/default_avatar.png";
    private String CONTENT_PLACEHOLDER = "https://www.jennybeaumont.com/wp-content/uploads/2015/03/placeholder-800x423.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FeedItem feedItem = (FeedItem) getIntent().getExtras().get("profile");
        getSupportActionBar().setTitle(feedItem.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((TextView) findViewById(R.id.tvContent)).setText(feedItem.getContent());

        if (feedItem.getTitleImageUrl().isEmpty()) {
            Picasso.get().load(PROFILE_PLACEHOLDER).into((ImageView) findViewById(R.id.ivTitle));
        } else {
            Picasso.get().load(feedItem.getTitleImageUrl()).into((ImageView) findViewById(R.id.ivTitle));
        }

        if (feedItem.getContentImageUrl().isEmpty()) {
            Picasso.get().load(CONTENT_PLACEHOLDER).into((ImageView) findViewById(R.id.ivContent));
        } else {
            Picasso.get().load(feedItem.getContentImageUrl()).into((ImageView) findViewById(R.id.ivContent));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(ProfileActivity.this, ChatActivity.class)
                                .putExtra("user_name", feedItem.getTitle())
                                .putExtra("user_pic", feedItem.getTitleImageUrl().isEmpty()?PROFILE_PLACEHOLDER:feedItem.getTitleImageUrl()));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish(); //Só tem uma ação que é o botão de voltar...
        return super.onOptionsItemSelected(item);
    }
}
