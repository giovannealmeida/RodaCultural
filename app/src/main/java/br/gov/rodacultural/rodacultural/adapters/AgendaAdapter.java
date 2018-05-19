package br.gov.rodacultural.rodacultural.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.models.AgendaEvent;
import br.gov.rodacultural.rodacultural.models.Group;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> {

    private final List<AgendaEvent> list;
    private Context context;

    public AgendaAdapter(List<AgendaEvent> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_agenda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getName());
        holder.tvDescription.setText(list.get(position).getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        holder.tvStart.setText(context.getString(R.string.event_start, sdf.format(list.get(position).getStart().getTime())));
        holder.tvEnd.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitle, tvDescription, tvStart, tvEnd;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            tvStart = (TextView) view.findViewById(R.id.tvStart);
            tvEnd = (TextView) view.findViewById(R.id.tvEnd);
        }
    }
}
