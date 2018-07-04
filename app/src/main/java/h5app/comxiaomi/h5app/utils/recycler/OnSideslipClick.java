package h5app.comxiaomi.h5app.utils.recycler;

/**
 * 作者：dell or Xiaomi Li<br>
 * 时间： 2018/1/25<br>
 * 内容：配合LrcyclerView实现下拉+侧滑的接口<br>
 * 最后修改：
 */

public interface OnSideslipClick {
    /**
     * 删除按钮
     *
     * @param position
     */
    void OnDeleteClick(int position);

    /**
     * 编辑按钮
     *
     * @param position
     */
    void OnEditClick(int position);
}
