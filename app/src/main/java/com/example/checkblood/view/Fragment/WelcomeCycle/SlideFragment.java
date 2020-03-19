package com.example.checkblood.view.Fragment.WelcomeCycle;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.example.checkblood.R;
import com.example.checkblood.adapter.SlideAdapter;
import com.example.checkblood.view.Activity.UserActivity.UserActivity;
import com.example.checkblood.view.Fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.example.checkblood.R.layout.fragment_slide;


public class SlideFragment extends BaseFragment  {

    @BindView(R.id.fragment_slide_vp_slider)
    ViewPager vpPager;


    public SlideFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(fragment_slide, container, false);

        ButterKnife.bind(this, view);

        SlideAdapter slideAdapter = new SlideAdapter(getActivity());
        vpPager.setAdapter(slideAdapter);

        return view;

    }

    @OnClick(R.id.slidefragment_bt_skip)
    public void onViewClicked() {

        Intent i1 = new Intent(getActivity(),UserActivity.class);
        startActivity(i1);
        getActivity().finish();
    }

}



