package com.suresh.myapplication.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListViewHolder> {

    private DataModel list;
    private Context context;

    // constructor which gets context and data from calling fragment/activity
    public CustomAdapter(Context context, DataModel list) {
        this.context = context;
        this.list = list;
    }


    // inflates layout on fragment creation
    @NonNull
    @Override
    public CustomAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_layout, parent, false);
        return new ListViewHolder(view);
    }

    // binds data to viewholder
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ListViewHolder holder, int position) {

        // assign value to textview
        Picasso.Builder picasso = new Picasso.Builder(context);

        holder.titleText.setVisibility(View.GONE);
        holder.descriptionText.setVisibility(View.GONE);
        holder.imageView.setVisibility(View.GONE);
        holder.imageViewRightArrow.setVisibility(View.GONE);

        if (list.getRows().get(position).getTitle() == null) {

            Log.d("NPE", "Title value is null");

        } else {
            holder.titleText.setVisibility(View.VISIBLE);
            holder.imageViewRightArrow.setVisibility(View.VISIBLE);
            holder.titleText.setText(list.getRows().get(position).getTitle());
        }

        if (list.getRows().get(position).getDescription() == null) {

            Log.d("NPE", "Description value is null");

        } else {
            holder.descriptionText.setVisibility(View.VISIBLE);
            holder.imageViewRightArrow.setVisibility(View.VISIBLE);
            holder.descriptionText.setText(list.getRows().get(position).getDescription());
        }

        if (list.getRows().get(position).getImageHref() == null) {

            Log.d("NPE", "Image value is null");

        } else {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageViewRightArrow.setVisibility(View.VISIBLE);
            picasso.build().load(list.getRows().get(position).getImageHref())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return list.getRows().size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder {

        View view;
        private TextView titleText;
        private TextView descriptionText;
        private ImageView imageView;
        private ImageView imageViewRightArrow;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            titleText = view.findViewById(R.id.titleTextView);
            descriptionText = view.findViewById(R.id.descriptionTextView);
            imageView = view.findViewById(R.id.imageView);
            imageViewRightArrow = view.findViewById(R.id.imageViewArrow);
        }
    }
}
