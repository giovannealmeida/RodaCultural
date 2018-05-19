package br.gov.rodacultural.rodacultural.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.models.Conversation;
import br.gov.rodacultural.rodacultural.models.Edital;
import br.gov.rodacultural.rodacultural.models.Message;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private final List<Conversation> list;
    private Context context;

    public MessageAdapter(List<Conversation> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvContact.setText(list.get(position).getUser().getName());
        holder.tvMessage.setText(list.get(position).getLastMessage().getText());
        holder.tvTimestamp.setText(list.get(position).getLastMessage().getTimestamp());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Abre conversa",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvContact, tvMessage, tvTimestamp;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvContact = (TextView) view.findViewById(R.id.tvContact);
            tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            tvTimestamp = (TextView) view.findViewById(R.id.tvTimestamp);
        }
    }
}
