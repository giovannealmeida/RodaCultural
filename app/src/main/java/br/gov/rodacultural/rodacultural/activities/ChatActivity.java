package br.gov.rodacultural.rodacultural.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.adapters.ChatAdapter;
import br.gov.rodacultural.rodacultural.models.FeedItem;
import br.gov.rodacultural.rodacultural.models.Message;
import br.gov.rodacultural.rodacultural.models.User;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Picasso.get().load( getIntent().getExtras().get("user_pic").toString()).into((ImageView) findViewById(R.id.ivProfile));

        recyclerView = (RecyclerView) findViewById(R.id.chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Message> list = new ArrayList<>();
        list.add(new Message("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc pellentesque fermentum justo, vel vulputate mi luctus et. Nunc porta vestibulum nunc nec pulvinar. In nec vestibulum eros, non tempus mauris. Nunc ligula ligula, consequat a leo et, semper fermentum nulla. Donec pulvinar mauris quis faucibus feugiat. In eu leo a.", new User(1, getIntent().getExtras().get("user_name").toString(), "https://i.imgur.com/r0bsDqL.jpg", false), ""));
        list.add(new Message("WTF...", new User(1, "Você", "", true), ""));
        recyclerView.setAdapter(new ChatAdapter(list, this));

        getSupportActionBar().setTitle( getIntent().getExtras().get("user_name").toString());
    }
}
