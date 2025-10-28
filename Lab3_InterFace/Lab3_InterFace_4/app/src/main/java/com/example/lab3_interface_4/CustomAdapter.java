package com.example.lab3_interface_4;

import android.content.Context;
import android.util.SparseBooleanArray;
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
    private SparseBooleanArray selectedPositions = new SparseBooleanArray();

    public CustomAdapter(Context context, List<String> items) {
        super(context, R.layout.list_item, items);
        this.context = context;
        this.items = items;
    }

    public void setActionMode(boolean actionMode) {
        isActionMode = actionMode;
        if (!actionMode) {
            selectedPositions.clear();
        }
        notifyDataSetChanged();
    }

    public void setSelected(int position, boolean selected) {
        if (selected) {
            selectedPositions.put(position, true);
        } else {
            selectedPositions.delete(position);
        }
        notifyDataSetChanged();
    }

    public void selectAll() {
        selectedPositions.clear();
        for (int i = 0; i < getCount(); i++) {
            selectedPositions.put(i, true);
        }
        notifyDataSetChanged();
    }

    public void clearSelection() {
        selectedPositions.clear();
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        int count = 0;
        for (int i = 0; i < getCount(); i++) {
            if (selectedPositions.get(i, false)) {
                count++;
            }
        }
        return count;
    }

    public List<Integer> getSelectedPositions() {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            if (selectedPositions.get(i, false)) {
                positions.add(i);
            }
        }
        return positions;
    }

    public boolean isSelected(int position) {
        return selectedPositions.get(position, false);
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
            holder.checkBox.setChecked(isSelected(position));

            // 设置CheckBox不可点击，避免与ListView的点击事件冲突
            holder.checkBox.setClickable(false);
            holder.checkBox.setFocusable(false);
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