package br.gov.rodacultural.rodacultural.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.activities.ProfileActivity;
import br.gov.rodacultural.rodacultural.models.FeedItem;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final List<FeedItem> list;
    private Context context;
    private String PROFILE_PLACEHOLDER = "https://projects.scpr.org/static-files/_v4/images/default_avatar.png";
    private String CONTENT_PLACEHOLDER = "https://www.jennybeaumont.com/wp-content/uploads/2015/03/placeholder-800x423.gif";

    public FeedAdapter(List<FeedItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.subTitle.setText(list.get(position).getSubTitle());
        holder.content.setText(list.get(position).getContent());

        if(list.get(position).getContentImageUrl().isEmpty()) {
            Picasso.get().load(CONTENT_PLACEHOLDER).into(holder.contentImage);
        } else {
            Picasso.get().load(list.get(position).getContentImageUrl()).into(holder.contentImage);
        }

        if(list.get(position).getTitleImageUrl().isEmpty()) {
            Picasso.get().load(PROFILE_PLACEHOLDER).into(holder.titleImage);
        } else {
            Picasso.get().load(list.get(position).getTitleImageUrl()).into(holder.titleImage);
        }

        if (list.get(position).isMEI()) {
            holder.mei.setVisibility(View.VISIBLE);
        } else {
            holder.mei.setVisibility(View.GONE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProfileActivity.class).putExtra("profile",list.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView subTitle;
        public final TextView content;
        public final TextView mei;
        public final ImageView contentImage;
        public final ImageView titleImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.tvTitle);
            subTitle = (TextView) view.findViewById(R.id.tvSubTitle);
            content = (TextView) view.findViewById(R.id.tvContent);
            mei = (TextView) view.findViewById(R.id.tvMEI);
            contentImage = (ImageView) view.findViewById(R.id.ivContent);
            titleImage = (ImageView) view.findViewById(R.id.ivTitle);
        }
    }
}
