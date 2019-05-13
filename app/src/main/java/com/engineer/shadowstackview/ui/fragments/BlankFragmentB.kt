package com.engineer.shadowstackview.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.engineer.ShadowStack

import com.engineer.shadowstackview.R
import kotlinx.android.synthetic.main.fragment_blank_b.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BlankFragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ShadowStack.with(activity!!).targetView(view_1).apply()
        ShadowStack.with(activity!!).targetView(text_view_2).apply()
    }


}
