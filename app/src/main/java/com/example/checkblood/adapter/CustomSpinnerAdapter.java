package com.example.checkblood.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.checkblood.R;
import com.example.checkblood.data.model.generalresponse.GeneralResponseData;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();

    @BindView(R.id.customSpinnerAdapter_tv_names)
    TextView customSpinnerAdapterTvNames;

    public int selected_id = 0 ;

    LayoutInflater flater;

    public CustomSpinnerAdapter(Activity context, int resouceId, int textviewId,
                                List<GeneralResponseData> generalResponseDataList,List<String> bloodtypename) {

        super(context, resouceId, textviewId, bloodtypename);
        this.generalResponseDataList = generalResponseDataList;
        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GeneralResponseData generalResponseData = generalResponseDataList.get(position);

        View view = flater.inflate(R.layout.item_custom_adapter, null, true);
        ButterKnife.bind(this, view);

        customSpinnerAdapterTvNames.setText(generalResponseData.getName());
        selected_id  = generalResponseData.getId();

        return view;
    }

}

