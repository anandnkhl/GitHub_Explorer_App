package com.example.nikhilanand.githubexplorer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ProfileFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        String nameOfUser = bundle.getString( "nameOfUser" );
        String picURL = bundle.getString( "picURL" );
        String reposURL = bundle.getString( "reposURL" );
        String created_at = bundle.getString( "created_at" );
        String public_repos = bundle.getString( "public_repos" );

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.profile_frag_layout, container, false);
        TextView text =  view.findViewById(R.id.textView7);
        text.setText(nameOfUser);
        TextView text2 =  view.findViewById(R.id.textView10);
        text2.setText(created_at);
        TextView text3 =  view.findViewById(R.id.textView12);
        text3.setText(public_repos);

        try {
            ImageView i = (ImageView)view.findViewById(R.id.imageView);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
            i.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}
