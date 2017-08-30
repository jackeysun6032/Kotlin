package com.sxc.kotlin.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.sxc.kotlin.R
import kotlinx.android.synthetic.main.view_setting_item.view.*

/**
 * Created by jackey on 2017/8/¬27.
 */
class SettingItemView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        LayoutInflater.from(context).inflate(R.layout.view_setting_item, this, true)
        val attributes: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView)
        val title: String = attributes.getString(R.styleable.SettingItemView_title)
        val titleColor: Int = attributes.getColor(R.styleable.SettingItemView_titleColor, Color.BLACK)
        itemContent.setTextColor(titleColor)
        itemContent.text = title
        val icon: Drawable? = attributes.getDrawable(R.styleable.SettingItemView_icon)
        iconTitle.setImageDrawable(icon)
        attributes.recycle()
    }

}