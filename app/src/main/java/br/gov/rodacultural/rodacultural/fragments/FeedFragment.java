package br.gov.rodacultural.rodacultural.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.adapters.FeedAdapter;
import br.gov.rodacultural.rodacultural.models.FeedItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    private RecyclerView recyclerView;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<FeedItem> list = new ArrayList<>();
        list.add(new FeedItem(1, "Jailso Andrade", "Professor de capoeira", getString(R.string.lorem_ipsum), "", "", true));
        list.add(new FeedItem(2, "Instituto Cultural Steve Biko", "ONG", getString(R.string.lorem_ipsum), "https://static.wixstatic.com/media/d7dbcc_397215f6704d4e77a5f6aff465b72305~mv2.jpeg/v1/fill/w_300,h_225,al_c,q_80,usm_0.66_1.00_0.01/d7dbcc_397215f6704d4e77a5f6aff465b72305~mv2.webp", "https://static.wixstatic.com/media/d7dbcc_edeb2e9faea94e5fafc17d248a0191c9.png/v1/fill/w_128,h_129,al_c,usm_0.66_1.00_0.01/d7dbcc_edeb2e9faea94e5fafc17d248a0191c9.png", true));
        list.add(new FeedItem(3, "Museu do descobrimento", "Museu", getString(R.string.lorem_ipsum), "", "", false));
        list.add(new FeedItem(4, "Rio dos Macacos", "Comunidade quilombola", getString(R.string.lorem_ipsum), "", "", false));
        list.add(new FeedItem(5, "CECUP - Centro de Educação e Cultura Popular", "ONG", getString(R.string.lorem_ipsum), "http://1.bp.blogspot.com/_CYiU1C4-83s/S9c7pSRmBOI/AAAAAAAAAq4/D-L0OSh8uTE/S260/cecup200.jpg", "", false));
        recyclerView.setAdapter(new FeedAdapter(list, getActivity()));

        return view;
    }

}
