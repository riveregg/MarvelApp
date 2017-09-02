package com.rafaelarnosti.marvelapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class SobreFragment extends Fragment {
    View itemview;

    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemview = inflater.inflate(R.layout.fragment_sobre, container, false);


        Animation anim = AnimationUtils.loadAnimation(getContext(),
                R.anim.animacao_splash);
        anim.reset();
        //Pegando o nosso objeto criado no layout
        ImageView iv = (ImageView) itemview.findViewById(R.id.ivGroot );
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(iv);
            Glide.with(this).load(R.drawable.groot).into(target);


        }
        return itemview;
    }
}



