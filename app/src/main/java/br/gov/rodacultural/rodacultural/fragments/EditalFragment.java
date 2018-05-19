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
import br.gov.rodacultural.rodacultural.adapters.EditalAdapter;
import br.gov.rodacultural.rodacultural.adapters.GroupAdapter;
import br.gov.rodacultural.rodacultural.models.Edital;
import br.gov.rodacultural.rodacultural.models.Group;

public class EditalFragment extends Fragment {

    private RecyclerView recyclerView;

    public EditalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edital, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Edital> list = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(2018,Calendar.MAY,20,20,00);

        list.add(new Edital("Selo Literário João Ubaldo Ribeiro - Ano II","",c));
        list.add(new Edital("Capoeira Viva Cultura","",c));
        list.add(new Edital("Edital Arte Em Toda Parte - Ano III","",c));

//        view.findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new EditalAdapter(list,getActivity()));
        return view;
    }
}
