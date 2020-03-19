package com.example.checkblood.view.Fragment.HomeCycle.HomeFragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.checkblood.R;
import com.example.checkblood.adapter.RecycleArticleAdapter;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;

import com.example.checkblood.data.model.getArticle.Datum;
import com.example.checkblood.view.Fragment.BaseFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;


public class ArticleFragment extends BaseFragment {


    @BindView(R.id.articleFragment_sv_search)
    SearchView articleFragmentSvSearch;
    @BindView(R.id.articleFragment_sp_protection)
    Spinner articleFragmentSpProtection;
    @BindView(R.id.article_ll_linear)
    LinearLayout articleRlRelative;
    @BindView(R.id.article_rv_recycleView)
    RecyclerView articleRvRecycleView;

    private Unbinder unbinder;
    private ApiService apiService;

    private RecycleArticleAdapter recycleArticleAdapter;
    private LinearLayoutManager linearLayoutManager;


    public ArticleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);

        getRecycleView();

        return view;
    }

    private void getRecycleView() {

        apiService.getArticle().enqueue(new Callback<List<Datum>>() {
            @Override
            public void onResponse(@NonNull Call<List<Datum>> call, @NonNull Response<List<Datum>> response) {

                if (response.isSuccessful()) {

                    List<Datum> datumList = response.body();

                    linearLayoutManager = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false);
                    articleRvRecycleView.setLayoutManager(linearLayoutManager);

                    recycleArticleAdapter = new RecycleArticleAdapter(datumList, getActivity());
                    articleRvRecycleView.setAdapter(recycleArticleAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Datum>> call, Throwable t) {
                Log.d("Error message", t.getMessage());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }
}
