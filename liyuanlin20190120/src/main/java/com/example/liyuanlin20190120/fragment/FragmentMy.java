package com.example.liyuanlin20190120.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liyuanlin20190120.R;

public class FragmentMy extends Fragment {

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = view.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(image, "rotation", 0, 180);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(image, "scaleX", 0, 2);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(objectAnimator).with(objectAnimator1);
                animatorSet.setDuration(3000);
                animatorSet.start();
            }
        });
    }
}
