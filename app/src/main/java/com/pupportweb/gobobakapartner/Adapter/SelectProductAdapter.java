package com.pupportweb.gobobakapartner.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pupportweb.gobobakapartner.Config.BaseURL;
import com.pupportweb.gobobakapartner.R;
import com.pupportweb.gobobakapartner.retrofitModel.ProductSelectDataItem;
import com.pupportweb.gobobakapartner.util.AllProductClickListner;
import com.pupportweb.gobobakapartner.util.Session_management;

import java.util.List;
import java.util.Locale;

public class SelectProductAdapter extends RecyclerView.Adapter<SelectProductAdapter.ViewHolder>{

    private Context context;
    private List<ProductSelectDataItem> searchList;
    private List<ProductSelectDataItem> productListFiltered;
    //    private AllProductsAdapterListener listener;
    private AllProductClickListner allProductClickListner;
    private Session_management session_management;


    public SelectProductAdapter(Context applicationContext, List<ProductSelectDataItem> movieList, List<ProductSelectDataItem> searchList, AllProductClickListner allProductClickListner) {
        this.context = applicationContext;
        this.productListFiltered = movieList;
        this.searchList = searchList;
        this.allProductClickListner = allProductClickListner;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_select_product, parent, false);
        session_management = new Session_management(parent.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProductSelectDataItem movie = productListFiltered.get(position);
//        if (movie.getStock().equals("1")){
//            holder.stock.setText(context.getResources().getString(R.string.in_st));
//        }else if (movie.getStock().equals("2")){
//            holder.stock.setText(context.getResources().getString(R.string.out_st));
//            holder.stock.setTextColor(context.getResources().getColor(R.color.color_3));
//        }
//        String sign = MainActivity.currency_sign;
//        holder.product_Id.setText(movie.getCat_id());
        holder.product_name.setText(movie.getProductName());
        if (movie.getDescription() != null && !movie.getDescription().equalsIgnoreCase("") && !movie.getDescription().equalsIgnoreCase("null")) {
            holder.product_desp.setVisibility(View.VISIBLE);
            holder.product_desp.setText(movie.getDescription());
        } else {
            holder.product_desp.setVisibility(View.GONE);
        }
//        holder.catogary_id.setText(movie.getMrp());
//        holder.increment.setText(movie.getIncrement());
        holder.price.setText("Price - " + session_management.getCurrency() + "" + movie.getPrice());
        holder.mrp.setText("Mrp - " + session_management.getCurrency() + "" + movie.getMrp());
        holder.qty.setText(movie.getQuantity() + " " + movie.getUnit());
//        holder.unit_value.setText();
//        holder.reward.setText(movie.getReward());
        Log.d("Img_url",BaseURL.IMG_PRODUCT_URL + movie.getVarientImage());
        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL + movie.getVarientImage())
                .centerCrop()
                .placeholder(R.drawable.app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.productimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("a", movie.getP_id());
                allProductClickListner.onClick(position);
//                delete(movie.getP_id());

            }
        });

    }

    @Override
    public int getItemCount() {
        return (productListFiltered != null && productListFiltered.size() > 0) ? productListFiltered.size() : 0;
    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    productListFiltered = list;
//                } else {
//                    List<NewAllProductModel> filteredList = new ArrayList<>();
//                    for (NewAllProductModel row : list) {
//                        if (row.getProduct_name().toLowerCase().contains(charString.toLowerCase()) || row.getCat_id().contains(charSequence)) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    productListFiltered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = productListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                productListFiltered = (ArrayList<NewAllProductModel>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    public boolean filterProductName(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        productListFiltered.clear();
        if (charText.length() == 0) {
            productListFiltered.addAll(searchList);
        } else {

            for (int i = 0; i < searchList.size(); i++) {
                String product_name = searchList.get(i).getProductName();
                product_name = product_name.toLowerCase(Locale.getDefault());
                if (product_name.contains(charText)) {
                    productListFiltered.add(searchList.get(i));
                }
            }

        }
        notifyDataSetChanged();
        return true;
    }

//    public interface AllProductsAdapterListener {
//        void onContactSelected(AlllProductModel contact);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name, price, unit_value, qty, mrp, product_desp;
        public ImageView productimage, delete;

        public ViewHolder(View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.name_product);
            product_desp = itemView.findViewById(R.id.product_desp);
            price = itemView.findViewById(R.id.price_product);
//            unit_value = itemView.findViewById(R.id.unit_product);
            qty = itemView.findViewById(R.id.qty_product);
            productimage = itemView.findViewById(R.id.imageview_product);
            mrp = itemView.findViewById(R.id.mrp_product);
            delete = itemView.findViewById(R.id.delete);


        }
    }
}