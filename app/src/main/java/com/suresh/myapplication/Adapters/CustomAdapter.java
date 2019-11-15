package com.suresh.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.suresh.myapplication.Models.DataModel;
import com.suresh.myapplication.Models.Row;
import com.suresh.myapplication.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListViewHolder> {

    private DataModel list;
    private Context context;

    public CustomAdapter(Context context, DataModel list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_layout, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ListViewHolder holder, int position) {

        // assign value to textview
        Picasso.Builder picasso = new Picasso.Builder(context);
        holder.titleText.setText(list.getRows().get(position).getTitle());
        holder.descriptionText.setText(list.getRows().get(position).getDescription());


        picasso.build().load(list.getRows().get(position).getImageHref())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.getRows().size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        View view;
        private TextView titleText;
        private TextView descriptionText;
        private ImageView imageView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            titleText = view.findViewById(R.id.titleTextView);
            descriptionText = view.findViewById(R.id.descriptionTextView);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
