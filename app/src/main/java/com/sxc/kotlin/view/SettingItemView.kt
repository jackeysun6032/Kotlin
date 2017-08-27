package com.sxc.kotlin.view

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sxc.kotlin.R
import kotlinx.android.synthetic.main.view_setting_item.view.*

/**
 * Created by jackey on 2017/8/27.
 */
class SettingItemView : LinearLayout {

    var mContext: Context? = null
    var attrs: AttributeSet? = null
    var defStyleAttr: Int? = null

    constructor(context: Context) : super(context) {
        this.mContext = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mContext = context
        this.attrs = attrs
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.mContext = context
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
        init()
    }

    fun init() {
        LayoutInflater.from(mContext).inflate(R.layout.view_setting_item, this, true)
        val attributes: TypedArray? = mContext?.obtainStyledAttributes(attrs, R.styleable.SettingItemView)
        val title: String? = attributes?.getString(R.styleable.SettingItemView_title)
        val titleColor: Int? = attributes?.getColor(R.styleable.SettingItemView_titleColor,Color.BLACK)
        itemContent.setTextColor(titleColor!!)
        itemContent.text = title

        val icon: Drawable? = attributes?.getDrawable(R.styleable.SettingItemView_icon)
        iconTitle.setImageDrawable(icon)
        attributes?.recycle()
    }

    fun setTitle(title: String) {
        itemContent.text = title
    }

    fun setTitle(title: Int) {
        itemContent.text = mContext?.resources?.getString(title)
    }

    fun setIcon(icon: Int) {
        iconTitle.setImageDrawable(mContext?.resources?.getDrawable(icon))
    }
}