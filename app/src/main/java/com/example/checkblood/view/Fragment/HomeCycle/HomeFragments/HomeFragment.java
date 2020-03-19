package com.example.checkblood.view.Fragment.HomeCycle.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.checkblood.R;
import com.example.checkblood.adapter.ViewPagerAdapter;
import com.example.checkblood.helper.HelperMethod;
import com.example.checkblood.view.Fragment.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.fragment_home_tb_tabLayout)
    TabLayout fragmentHomeTbTabLayout;
    @BindView(R.id.fragment_home_vp_vPager)
    ViewPager HomeVpVPager;
    @BindView(R.id.fragment_home_fab_floating)
    FloatingActionButton fragmentHomeFabFloating;
    private Unbinder unbinder;

    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);
        setUpViewPager();

        return view;
    }

    private void setUpViewPager() {

            ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getParentFragmentManager(), 1);
            ArticleFragment articleFragment = new ArticleFragment();
            DonationRequestFragment donationRequestFragment = new DonationRequestFragment();

            vpAdapter.addPager(articleFragment, getString(R.string.articles));
            vpAdapter.addPager(donationRequestFragment, getString(R.string.donation));

            HomeVpVPager.setAdapter(vpAdapter);
            fragmentHomeTbTabLayout.setupWithViewPager(HomeVpVPager);

    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_home_fab_floating)
    public void onViewClicked() {

        HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                new RequsetDonateFragment(), R.id.drawer_layout, null, null);
    }
}
