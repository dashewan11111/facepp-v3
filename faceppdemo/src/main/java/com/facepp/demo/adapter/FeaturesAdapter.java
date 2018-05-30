package com.facepp.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facepp.demo.R;
import com.facepp.demo.bean.FaceActionItem;

import java.util.List;

/**
 * @author by licheng on 2018/5/29.
 */

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;

    private List<FaceActionItem> actionList;

    private OnItemClickListener onItemClickListener;

    public FeaturesAdapter(Context context, List<FaceActionItem> actionList) {
        this.context = context;
        this.actionList = actionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView;
        switch (viewType) {
            case FaceActionItem.ITEM_TYPE_ENABLE:
                rootView = LayoutInflater.from(context).inflate(R.layout.item_action_type_enable, null);
                break;
            case FaceActionItem.ITEM_TYPE_SWITCH:
                rootView = LayoutInflater.from(context).inflate(R.layout.item_action_type_switch, null);
                break;
            case FaceActionItem.ITEM_TYPE_SWITCH_2:
                rootView = LayoutInflater.from(context).inflate(R.layout.item_action_type_switch2, null);
                break;
            case FaceActionItem.ITEM_TYPE_INPUT:
                rootView = LayoutInflater.from(context).inflate(R.layout.item_action_type_input, null);
                break;
            case FaceActionItem.ITEM_TYPE_SELECTION:
                rootView = LayoutInflater.from(context).inflate(R.layout.item_action_type_selection, null);
                break;
            default:
                rootView = LayoutInflater.from(context).inflate(R.layout.item_action_type_enable, null);
                break;
        }
        rootView.setOnClickListener(this);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        FaceActionItem item = actionList.get(position);
        int gravity = position % 3;
        if (null != item) {
            switch (item.getType()) {
                case FaceActionItem.ITEM_TYPE_ENABLE:
                    holder.imageFeature.setImageResource(item.isEnable() ? item.getContentValues()[0] : item.getContentValues()[1]);
                    holder.txtFeatureName.setText(item.getBottomTitle()[0]);
                    holder.txtFeatureName.setTextColor(item.isEnable() ? 0XFF30364C : 0XFFD0D0D0);
                    break;
                case FaceActionItem.ITEM_TYPE_SWITCH:
                    holder.imageFeature.setImageResource(item.isEnable() ? item.getContentValues()[0] : item.getContentValues()[1]);
                    holder.txtFeatureName.setText(item.isEnable() ? item.getBottomTitle()[0] : item.getBottomTitle()[1]);
                    break;
                case FaceActionItem.ITEM_TYPE_SWITCH_2:
                    holder.txtFeatureValue.setText(item.isEnable() ? item.getContentValues()[0] : item.getContentValues()[1]);
                    holder.txtFeatureName.setText(item.isEnable() ? item.getBottomTitle()[0] : item.getBottomTitle()[1]);
                    break;
                case FaceActionItem.ITEM_TYPE_INPUT:
                    holder.txtFeatureValue.setText(String.valueOf(item.getCurrentValue()));
                    FeaturesAdapter.setTextSzie(holder.txtFeatureValue, String.valueOf(item.getCurrentValue()).length());
                    holder.txtFeatureName.setText(item.getBottomTitle()[0]);
                    break;
                case FaceActionItem.ITEM_TYPE_SELECTION:
                    holder.txtFeatureValue.setText(item.getSelections()[item.getSelectedId()]);
                    FeaturesAdapter.setTextSzie(holder.txtFeatureValue, item.getSelections()[item.getSelectedId()].length());
                    holder.txtFeatureName.setText(item.getBottomTitle()[0]);
                    break;
                default:
                    break;
            }
        }

        if (null != holder.itemContainer) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.itemContainer.getLayoutParams();
            if (0 == gravity) {
                layoutParams.leftMargin = 20;
                layoutParams.gravity = Gravity.START;
            } else if (1 == gravity) {
                layoutParams.rightMargin = 20;
                layoutParams.gravity = Gravity.CENTER;
            } else {
                layoutParams.gravity = Gravity.END;
            }
            holder.itemContainer.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return actionList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return actionList.size();
    }

    public FaceActionItem getItem(int position) {
        return actionList.get(position);
    }

    public void setItem(int position, FaceActionItem item) {
        actionList.set(position, item);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (null != onItemClickListener) {
            onItemClickListener.onItemClick((int) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageFeature;   // 图片
        TextView txtFeatureValue; // 文本值
        TextView txtFeatureName;  // 功能名

        LinearLayout itemContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            imageFeature = itemView.findViewById(R.id.feature_image);
            txtFeatureValue = (itemView).findViewById(R.id.feature_value);
            txtFeatureName = itemView.findViewById(R.id.feature_name);
            itemContainer = itemView.findViewById(R.id.item_container);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static void setTextSzie(TextView text, int num) {
        if (num < 3)
            text.setTextSize(20);
        else if (num < 5)
            text.setTextSize(18);
        else if (num < 7)
            text.setTextSize(16);
        else if (num < 9)
            text.setTextSize(14);
        else if (num >= 9)
            text.setTextSize(12);
    }
}
