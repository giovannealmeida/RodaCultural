package br.gov.rodacultural.rodacultural.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.adapters.EditalAdapter;
import br.gov.rodacultural.rodacultural.adapters.MessageAdapter;
import br.gov.rodacultural.rodacultural.models.Conversation;
import br.gov.rodacultural.rodacultural.models.Edital;
import br.gov.rodacultural.rodacultural.models.Message;
import br.gov.rodacultural.rodacultural.models.User;

public class MessageFragment extends Fragment {

    private RecyclerView recyclerView;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Conversation> list = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(2018,Calendar.MAY,20,20,00);

        list.add(new Conversation(new Message("Obrigado",new User(10,"Cláudia Fernanda","",false),"18/05"),new User(10,"Cláudia Fernanda","",false)));
        list.add(new Conversation(new Message("$20,00",new User(10,"Instituto Cultural Steve Biko","https://static.wixstatic.com/media/d7dbcc_397215f6704d4e77a5f6aff465b72305~mv2.jpeg/v1/fill/w_300,h_225,al_c,q_80,usm_0.66_1.00_0.01/d7dbcc_397215f6704d4e77a5f6aff465b72305~mv2.webp",false),"18/05"),new User(10,"Cláudia Fernanda","",false)));
        list.add(new Conversation(new Message("WTF...",new User(1,"Você","",true),"19/05"),new User(10,"Jailso Andrade","",false)));

//        view.findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new MessageAdapter(list,getActivity()));
        return view;
    }
}
