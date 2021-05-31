package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    interface OnFragmentSendDataListener {
        fun openSecondFragment(min: Int, max: Int)
    }

    private var onFragmentSendDataListener: OnFragmentSendDataListener? = null

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onFragmentSendDataListener = context as OnFragmentSendDataListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " должен реализовывать интерфейс OnFragmentSendDataListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        onFragmentSendDataListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min = Integer.parseInt(minEditText?.text.toString().trim())
            val max = Integer.parseInt(maxEditText?.text.toString().trim())
            onFragmentSendDataListener?.openSecondFragment(min, max)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

}