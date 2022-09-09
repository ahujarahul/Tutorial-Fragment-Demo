package com.example.fragmentdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.fragmentdemo.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var sharedDataItem: String? = null
    // access the host activity's ViewModel
    private val viewModel by activityViewModels<FragmentHandlerViewModel>()

    private var fragmentSecondBinding: FragmentSecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "inside onCreateView()")
        // Inflate the layout for this fragment using view binding
        fragmentSecondBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return fragmentSecondBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "inside onViewCreated()")

        viewModel.getSharedDataItem().observe(viewLifecycleOwner) {
            // value used in onResume()
            sharedDataItem = it
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(LOG_TAG, "inside onViewStateRestored()")
    }

    override fun onStart() {
        super.onStart()
        // any initialization will go here
        Log.d(LOG_TAG, "inside onStart()")

        // will work only if the fragment is added to backstack during transaction
        fragmentSecondBinding?.btnPreviousFragment?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "inside onResume()")

        Toast.makeText(context, "Params from First Fragment :: $param1, $param2. \nShared data :: $sharedDataItem", Toast.LENGTH_LONG).show()
        Log.d(LOG_TAG, "Params from First Fragment :: $param1, $param2. Shared data :: $sharedDataItem")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "inside onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "inside onStop()")

        viewModel.setSharedDataItem("shared data updated from Second Fragment..")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "inside onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LOG_TAG, "inside onDestroyView()")
        fragmentSecondBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "inside onDestroy()")
    }

    companion object {
        val LOG_TAG = SecondFragment::class.java.simpleName
        const val EXTRA_BUNDLE = "extra_bundle"
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}