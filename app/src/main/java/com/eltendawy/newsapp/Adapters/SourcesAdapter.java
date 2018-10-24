package com.eltendawy.newsapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eltendawy.newsapp.Interfaces.OnItemCLickListener;
import com.eltendawy.newsapp.R;
import com.eltendawy.newsapp.API.Models.Source;

import java.util.List;

/**
 * Created by Islam_Assem on 28-09-18.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {


    List<Source> sources;
    OnItemCLickListener onItemCLickListener;

    public SourcesAdapter(List<Source> sources) {
        this.sources = sources;
    }
    public void addSource(Source source)
    {
        if(sources!=null)
            sources.add(source);
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_item_sources,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.source.setText(sources.get(position).getName());
        holder.desc.setText(sources.get(position).getDescription());
        if(onItemCLickListener!=null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemCLickListener.onClick(sources.get(holder.getAdapterPosition()),holder.itemView,holder.getAdapterPosition());
                }
            });

    }

    @Override
    public int getItemCount() {
        return sources==null?0:sources.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView logo;
        TextView source,desc;
        public ViewHolder(View itemView) {
            super(itemView);
            source=itemView.findViewById(R.id.source);
            desc=itemView.findViewById(R.id.description);
            logo=itemView.findViewById(R.id.logo);
        }
    }
}
