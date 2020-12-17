package com.pupportweb.gobobakapartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pupportweb.gobobakapartner.R;
import com.pupportweb.gobobakapartner.retrofitModel.StoreEarningsItem;

import java.util.List;

public class EarningDetailsAdapter extends RecyclerView.Adapter<EarningDetailsAdapter.ViewHolder>{

    private Context context;
    private List<StoreEarningsItem> list;



    public EarningDetailsAdapter(Context applicationContext, List<StoreEarningsItem> list) {
        this.context = applicationContext;

        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_earning_details, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtDeliveryDate.setText(list.get(position).getDeliveryDate());
        holder.txtTotalPrice.setText(list.get(position).getOrderTotalPrice());
        holder.txtPaymentMethod.setText(list.get(position).getOrderStatus());
        holder.txtProductName.setText(list.get(position).getProductName().toString());

    }

    @Override
    public int getItemCount() {
        return (list != null && list.size() > 0) ? list.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDeliveryDate, txtTotalPrice, txtPaymentMethod, txtProductName;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDeliveryDate = itemView.findViewById(R.id.txtDeliveryDate);
            txtTotalPrice = itemView.findViewById(R.id.txtTotalPrice);
            txtPaymentMethod = itemView.findViewById(R.id.txtPaymentMethod);
            txtProductName = itemView.findViewById(R.id.txtProductName);

        }
    }
}