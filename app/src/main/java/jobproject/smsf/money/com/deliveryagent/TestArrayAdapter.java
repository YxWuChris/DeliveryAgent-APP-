package jobproject.smsf.money.com.deliveryagent;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Zy18483 on 2019/5/23.
 */

public class TestArrayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String [] mStringArray;
    public TestArrayAdapter(Context context, String[] stringArray) {
        super(context, android.R.layout.simple_spinner_item, stringArray);
        mContext = context;
        mStringArray=stringArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
        }


        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArray[position]);
        tv.setTextSize(22f);
        tv.setTextColor(Color.RED);

        return convertView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv =convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArray[position]);
        tv.setTextSize(18f);
        tv.setTextColor(Color.BLUE);
        return convertView;
    }

}
