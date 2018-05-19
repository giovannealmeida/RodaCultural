package br.gov.rodacultural.rodacultural.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.adapters.AgendaAdapter;
import br.gov.rodacultural.rodacultural.adapters.GroupAdapter;
import br.gov.rodacultural.rodacultural.models.AgendaEvent;
import br.gov.rodacultural.rodacultural.models.Group;

public class AgendaFragment extends Fragment {

    private RecyclerView recyclerView;

    public AgendaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<AgendaEvent> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(2018,Calendar.MAY,20,20,00);
        list.add(new AgendaEvent("Circo Itinerante","O Ciro Itinerante está passando pela sua cidade. Venha nos visitar!","http://rioencena.com.br/site/wp-content/uploads/2015/08/Irm%C3%A3os-Brothers-Band-Foto-Ramon-Moreira2.jpg", c,null));
//        view.findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new AgendaAdapter(list,getActivity()));
        return view;
    }
}
