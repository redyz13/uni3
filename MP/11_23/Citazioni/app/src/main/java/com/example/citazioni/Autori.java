package com.example.citazioni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class Autori extends ListFragment {
    public interface AuthorSelectionListener {
        void onAuthorSelection(int index);
    }

    private AuthorSelectionListener listener;
    private int currentSelection = -1;
    private String[] authors;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (AuthorSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement AuthorSelectionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authors = getResources().getStringArray(R.array.Authors);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, authors));
        if (currentSelection != -1) {
            setSelection(currentSelection);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        currentSelection = position;
        getListView().setItemChecked(position, true);
        listener.onAuthorSelection(position);
    }
}
