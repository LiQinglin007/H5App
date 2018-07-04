package h5app.comxiaomi.h5app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import h5app.comxiaomi.h5app.R;
import h5app.comxiaomi.h5app.bean.WebUrlBean;
import h5app.comxiaomi.h5app.utils.ScreenUtils;
import h5app.comxiaomi.h5app.utils.recycler.OnItemClickListener;
import h5app.comxiaomi.h5app.utils.recycler.OnSideslipClick;


/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-02<br>
 * Time: 9:40<br>
 * UpdateDescription：<br>
 */

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    private Context mContext;

    public IconAdapter(Context mContext) {
        this.mContext = mContext;
    }

    ArrayList<Integer> mList = new ArrayList<>();

    public void setList(ArrayList<Integer> mDataList) {
        mList.clear();
        mList.addAll(mDataList);
        notifyDataSetChanged();
    }

    private OnItemClickListener mClick = null;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        mClick = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_icon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mImageView.setImageResource(mList.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mClick) {
                    mClick.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_icon_img);
        }
    }
}
