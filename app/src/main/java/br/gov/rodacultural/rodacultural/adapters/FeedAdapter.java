package br.gov.rodacultural.rodacultural.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.models.FeedItem;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final List<FeedItem> list;

    public FeedAdapter(List<FeedItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.subTitle.setText(list.get(position).getSubTitle());
        holder.content.setText(list.get(position).getContent());
        Picasso.get().load(list.get(position).getContentImageUrl()).into(holder.contentImage);
        Picasso.get().load(list.get(position).getTitleImageUrl()).into(holder.titleImage);

        if(list.get(position).isMEI()){
           holder.mei.setVisibility(View.VISIBLE);
        } else {
            holder.mei.setVisibility(View.GONE);
        }
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
