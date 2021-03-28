package com.example.nofapmainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nofapmainactivity.Adapter.NofapServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<NofapServer> list;
    List<NofapServer> listFull;

    public RecyclerViewAdapter(Context context, List<NofapServer> list)
    {
        this.context = context;
        this.list = list;
        listFull = new ArrayList<>(list);
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.profileImage.setImageResource(list.get(position).getImageProfile());
        holder.username.setText(list.get(position).getUsername());
        holder.userDesc.setText(list.get(position).getUserDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return FilterNofapServer;
    }

    private Filter FilterNofapServer = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText = constraint.toString().toLowerCase();
            List<NofapServer> tempList = new ArrayList<>();

            if (searchText.length() == 0 || searchText.isEmpty())
            {
                tempList.addAll(listFull);
            }

            else
            {
                for (NofapServer nofapServer : listFull)
                {
                    if (nofapServer.getUsername().contains(searchText))
                    {
                        tempList.add(nofapServer);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = tempList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends NofapServer>) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView username, userDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            userDesc = itemView.findViewById(R.id.userDesc);
            username = itemView.findViewById(R.id.username);
        }
    }
}
