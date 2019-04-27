package com.engineer.shadowstackview.ui.fragments


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.engineer.ShadowStack

import com.engineer.shadowstackview.R
import com.engineer.shadowstackview.exts.NoScrollableManager
import kotlinx.android.synthetic.main.fragment_blank_a.*


/**
 * A simple [Fragment] subclass.
 *
 */
class BlankFragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ShadowStack.with(activity!!).targetView(view_1).apply()
        ShadowStack.with(activity!!).targetView(drawable).setShadowCount(40).apply()
        list.layoutManager = NoScrollableManager(context, 3)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        list.adapter = Adapter(activity!!)
    }


    inner class Adapter(activity: Activity) : RecyclerView.Adapter<Adapter.MyHolder>() {
        private val type_move = 0
        private val type_static = 1

        private var mActivity: Activity = activity

        private val imgs = arrayOf(R.drawable.city, R.drawable.cat, R.drawable.girl, R.drawable.paint)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_image_item, parent, false)
            if (viewType == type_move) {
                ShadowStack.with(mActivity).targetView(view).apply()
            }
            return MyHolder(view)
        }

        override fun getItemCount(): Int {
            return 9
        }

        override fun getItemViewType(position: Int): Int {
            return when (position % 4) {
                0 -> type_move
                else -> type_static
            }
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            val index = position % imgs.size
            holder.image.setImageResource(imgs[index])
        }


        inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image = view.findViewById<ImageView>(R.id.image)
        }
    }


}
