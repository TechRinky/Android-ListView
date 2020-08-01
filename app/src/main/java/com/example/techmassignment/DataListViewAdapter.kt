package com.example.techmassignment

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * This class is responsible to show data in list by using base adapter
 */
class DataListViewAdapter(rowList: ArrayList<DataOfImagesBean.Row>, context: Context) :
    BaseAdapter() {
    private var context: Context
    private var rowList = ArrayList<DataOfImagesBean.Row>()
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        this.rowList = rowList
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View ?{
        val view: View?
        val viewHolder: ListRowHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.row_listview, parent, false)
            viewHolder = ListRowHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ListRowHolder
        }
        viewHolder.title.setText(rowList.get(position).title)
        viewHolder.descrpTv.setText(rowList.get(position).description)
        val url =  rowList.get(position).imageHref
        Picasso.with(context).load(url).placeholder(R.drawable.techm_placeholder).into(viewHolder.imageView)
        return view
    }

    override fun getItem(p0: Int): Any {
        return rowList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return rowList.size
    }

    /**
     * This class is responsible to handle the view
     */
    private class ListRowHolder(row: View?) {
        val title: TextView
        val descrpTv: TextView
        val imageView: ImageView

        init {
            this.title = row?.findViewById(R.id.title_tv) as TextView
            this.descrpTv = row.findViewById(R.id.descrption_tv) as TextView
            this.imageView = row.findViewById(R.id.imageView) as ImageView
        }
    }
}