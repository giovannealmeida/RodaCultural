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
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.adapters.GroupAdapter;
import br.gov.rodacultural.rodacultural.models.Group;

public class AboutAppFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public AboutAppFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_app, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Novo grupo")
                        .setView(getLayoutInflater().inflate(R.layout.alert_new_group, null))
                        .setPositiveButton(getString(R.string.btAdd), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "Adicinar novo grupo", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getString(R.string.actionClose), null)
                        .show();
            }
        });

        fab.animate().translationY(0).setDuration(200);

        List<Group> list = new ArrayList<>();
        view.findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new GroupAdapter(list,getActivity()));
        return view;
    }
}
