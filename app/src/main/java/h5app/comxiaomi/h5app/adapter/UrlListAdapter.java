package h5app.comxiaomi.h5app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import h5app.comxiaomi.h5app.R;
import h5app.comxiaomi.h5app.bean.WebUrlBean;
import h5app.comxiaomi.h5app.utils.LogUtils;
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

public class UrlListAdapter extends RecyclerView.Adapter<UrlListAdapter.ViewHolder> {

    private Context mContext;

    public UrlListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    ArrayList<WebUrlBean> mList = new ArrayList<>();

    public void setList(ArrayList<WebUrlBean> mDataList) {
        mList.clear();
        mList.addAll(mDataList);
        notifyDataSetChanged();
    }


    private OnItemClickListener mClick = null;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        mClick = mOnItemClickListener;
    }

    private OnSideslipClick mOnSideslipClick = null;

    public void setOnSideslipClick(OnSideslipClick onSideslipClick) {
        mOnSideslipClick = onSideslipClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_url_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTitleTv.setText(mList.get(position).getWebUrlTitle());
        holder.mContentTv.setText(mList.get(position).getWebUrlContent());
        holder.mDefaultTv.setVisibility(mList.get(position).getDefaultUrl() ? View.VISIBLE : View.GONE);

        ViewGroup.LayoutParams linearParams = holder.mUrlListLy.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = ScreenUtils.getScreenWidth(mContext);
        holder.mUrlListLy.setLayoutParams(linearParams); //使设置好的布局参数应用到控件|

        holder.editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSideslipClick) {
                    mOnSideslipClick.OnEditClick(position);
                }
            }
        });

        holder.deleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSideslipClick) {
                    mOnSideslipClick.OnDeleteClick(position);
                }
            }
        });

        holder.mUrlListLy.setOnClickListener(new View.OnClickListener() {
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
        LinearLayout mUrlListLy;
        TextView mTitleTv;
        TextView mContentTv;
        TextView mDefaultTv;
        Button editTv;
        Button deleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mUrlListLy = itemView.findViewById(R.id.item_urllist_ly);
            mTitleTv = itemView.findViewById(R.id.item_urllist_title);
            mContentTv = itemView.findViewById(R.id.item_urllist_content);
            mDefaultTv = itemView.findViewById(R.id.item_urllist_default);
            editTv = itemView.findViewById(R.id.item_urllist_edit);
            deleTv = itemView.findViewById(R.id.item_urllist_dele);
        }
    }
}
