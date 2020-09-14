package com.example.electronic;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {

    private ArrayList<PersonalData> mList = null;
    private Activity context = null;

    public UsersAdapter(Activity context, ArrayList<PersonalData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView time;
        protected TextView charge;
        protected TextView preserved;

        public CustomViewHolder(View view) {
            super(view);
            this.time = (TextView) view.findViewById(R.id.textView_list_time);
            this.charge = (TextView) view.findViewById(R.id.textView_list_charge);
            this.preserved = (TextView) view.findViewById(R.id.textView_list_preserved);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        // 요금조회 리사이클러뷰에 제이슨으로 받은값 설정
        viewholder.time.setText(mList.get(position).getMember_time());
        viewholder.charge.setText(mList.get(position).getMember_charge());
        String preserve = (mList.get(position).getMember_preserved());  // 변수값 받아서
        if(preserve == "예약가능")   // 예약가능하면 파란글씨로 출력
        {
            viewholder.preserved.setText(preserve);
            viewholder.preserved.setTextColor(Color.BLUE);
        }
        else
        {            //불가하면 빨간글씨로 출력
            viewholder.preserved.setText(preserve);
            viewholder.preserved.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}