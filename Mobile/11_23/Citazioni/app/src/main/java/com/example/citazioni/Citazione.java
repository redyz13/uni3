package com.example.citazioni;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Citazione extends Fragment implements View.OnClickListener {
    private TextView quoteView;
    private TextView authorView;
    private int current_index = -1;
    private int quoteArrayLen;
    String[] quotes;
    String[] authors;
    Button backButton;
    Activity hostActivity;

    public void showIndex(int newIndex, int mode) {
        if (backButton != null && mode == 2)
            backButton.setVisibility(View.INVISIBLE);
        if (newIndex < 0 || newIndex >= quoteArrayLen)
            return;
        current_index = newIndex;
        quoteView.setText(quotes[current_index]);
        authorView.setText(authors[current_index]);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        current_index = -1;
        hostActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_layout, container, false);
        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        quoteView = hostActivity.findViewById(R.id.quoteView);
        authorView = hostActivity.findViewById(R.id.authorName);
        quotes = getResources().getStringArray(R.array.Quotes);
        authors = getResources().getStringArray(R.array.Authors);
        quoteArrayLen = quotes.length;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        current_index = -1;
    }

    @Override
    public void onClick(View v) {
        hostActivity.onBackPressed();
    }
}
