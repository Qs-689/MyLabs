package com.example.lab3_interface_4;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> items;
    private boolean isActionMode = false;
    private List<Integer> selectedPositions = new ArrayList<>();

    public CustomAdapter(Context context, List<String> items) {
        super(context, R.layout.list_item, items);
        this.context = context;
        this.items = items;
    }

    public void setActionMode(boolean actionMode) {
        isActionMode = actionMode;
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove((Integer) position);
        } else {
            selectedPositions.add(position);
        }
        notifyDataSetChanged();
    }

    public void clearSelection() {
        selectedPositions.clear();
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return selectedPositions.size();
    }

    public List<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.tvItemText);
            holder.checkBox = convertView.findViewById(R.id.cbSelection);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(items.get(position));

        if (isActionMode) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(selectedPositions.contains(position));
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }
}
