package com.example.nikhilanand.githubexplorer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RepoFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();

        String reposURL = bundle.getString( "reposURL" );


        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.repo_frag_layout, container, false);

        //t.setText(reposURL);

        TextView textView = view.findViewById(R.id.textView13);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='"+reposURL+"'> List of Public Repos </a>";
        textView.setText(Html.fromHtml(text));

        return view;
    }
}
