package com.mkurbanov.appforalefim.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkurbanov.appforalefim.App;
import com.mkurbanov.appforalefim.ImageActivity;
import com.mkurbanov.appforalefim.R;
import com.mkurbanov.appforalefim.data.server.main.MainResponse;
import com.mkurbanov.appforalefim.interfaces.itemTypes;
import com.mkurbanov.appforalefim.utils.Functions;

import java.util.ArrayList;
import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> items;
    Context context;
    LayoutInflater layoutInflater;

    public MainAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == itemTypes.TYPE_ITEM) {
            view = layoutInflater.inflate(R.layout.item_main, parent, false);
            VHItem vhItem = new VHItem(view);
            return vhItem;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            VHItem vhItem = (VHItem) holder;
            String url = (String) items.get(position);

            App.getInstance().getRequests().getImage(url, vhItem.imageView);

            vhItem.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ImageActivity.class).putExtra("image", url));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Functions.logWrite("sz = " + items.size());
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return items.get(position).getTypeItem();
        return itemTypes.TYPE_ITEM;
    }

    private class VHItem extends RecyclerView.ViewHolder {
        ImageView imageView;

        public VHItem(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
        }
    }

}
