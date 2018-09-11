package io.box.doitua.com.boxesmanager.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.box.doitua.com.boxesmanager.R;
import io.box.doitua.com.boxesmanager.api.models.Box;
import io.box.doitua.com.boxesmanager.databinding.BoxItemBinding;

public class BoxRecyclerAdapter extends RecyclerView.Adapter<BoxRecyclerAdapter.BoxViewHolder> {

    private List<Box> boxes;
    private String userName = "";
    private LayoutInflater inflater;

    public BoxRecyclerAdapter(Context ctx) {
        boxes = new ArrayList<>();
        inflater = LayoutInflater.from(ctx);
    }

    public BoxRecyclerAdapter(Context ctx, String userName) {
        boxes = new ArrayList<>();
        inflater = LayoutInflater.from(ctx);
        this.userName = userName;
    }

    @NonNull
    @Override
    public BoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.box_item, parent, false);
        return new BoxViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxViewHolder holder, int position) {
        Box box = boxes.get(position);
        if (box.isNamed()) holder.binding.box.setText(userName);
        holder.binding.info.color.setText(String.valueOf("Color: " + box.getColorName()));
        holder.binding.info.size.setText(String.valueOf("Size: " + box.getSize()));
        holder.binding.info.height.setText(String.valueOf("Height: " + box.getHeight()));
        holder.binding.info.width.setText(String.valueOf("Width: " + box.getWidth()));
        holder.binding.info.length.setText(String.valueOf("Length: " + box.getLength()));
        holder.binding.box.setBackgroundColor(box.getColor(box.getColorName()));
    }

    @Override
    public int getItemCount() {
        return boxes.size();
    }

    public void addBox(Box b) {
        boxes.add(b);
        notifyItemInserted(boxes.size() - 1);
    }

    public void addAll(List<Box> list) {
        boxes.addAll(list);
        notifyDataSetChanged();
    }

    class BoxViewHolder extends RecyclerView.ViewHolder {
        BoxItemBinding binding;

        public BoxViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
