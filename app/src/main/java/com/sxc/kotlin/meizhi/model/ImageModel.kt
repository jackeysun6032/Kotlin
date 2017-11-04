package com.sxc.kotlin.meizhi.model

import android.support.annotation.LayoutRes
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.app.turingrobot.multitype.base.BaseViewHolder
import com.app.turingrobot.multitype.base.ViewHolderPresenter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sword.library.multitype.base.ViewModel
import com.sxc.kotlin.R
import com.sxc.kotlin.base.GlideApp
import com.sxc.kotlin.bean.meizhi.NewsListBean
import com.sxc.kotlin.utils.DensityUtil

/**
 * Created by sword on 2017/9/30.
 */
class ImageModel(var newslistBean: NewsListBean) : ViewModel() {


    override fun viewTypeId(): Int = viewTypeId

    init {
        this.newslistBean = newslistBean
    }


    class DataHolder(@LayoutRes layoutId: Int, inflater: LayoutInflater, parent: ViewGroup) : BaseViewHolder(layoutId, inflater, parent) {

        val imageView by lazy { itemView.findViewById<ImageView>(R.id.imageView) }

        val tv_des by lazy { itemView.findViewById<TextView>(R.id.tv_des) }

    }

    class MeiZhiViewHolder : ViewHolderPresenter<DataHolder, ImageModel>() {

        private val indexMap = SparseArray<Float>()

        override fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater) = DataHolder(viewTypeId, inflater, parent)


        override fun onBind(holder: DataHolder, entity: ImageModel) {

            resizeItemView(holder.imageView, holder.adapterPosition)

            holder.tv_des.text = entity.newslistBean.ctime

            GlideApp.with(holder.imageView.context)
                    .load(entity.newslistBean.picUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView)
        }


        private fun resizeItemView(frontCoverImage: ImageView, position: Int) {
            val randomValue: Float
            if (indexMap.get(position) != null) {
                randomValue = indexMap.get(position)
            } else {
                randomValue = (Math.random() / 5).toFloat()
                indexMap.put(position, randomValue)
            }
            val params = frontCoverImage.layoutParams as FrameLayout.LayoutParams
            params.width = DensityUtil.getScreenWidth() / 2
            params.height = (params.width + params.width.toFloat() * randomValue * 4f).toInt() - DensityUtil.dip2px(frontCoverImage.context, 8F)
            frontCoverImage.layoutParams = params
        }

        override fun type(): Int = viewTypeId

    }

    companion object {

        const val viewTypeId = R.layout.layout_list_meizhi

        fun newPresenter() = ViewHolderPresenter.cast(MeiZhiViewHolder())
    }


}