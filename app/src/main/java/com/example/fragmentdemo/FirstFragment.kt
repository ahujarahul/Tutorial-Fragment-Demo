package com.example.fragmentdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fragmentdemo.databinding.FragmentFirstBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null
    private val LOG_TAG = FirstFragment::class.java.simpleName
    // access the host activity's ViewModel
    private val viewModel: FragmentHandlerViewModel by activityViewModels()

    private var fragmentFirstBinding: FragmentFirstBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "inside onCreate()")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "inside onCreateView()")

        // Inflate the layout for this fragment using view binding
        fragmentFirstBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return fragmentFirstBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "inside onViewCreated()")

        // observing the shared data in ViewModel
        viewModel.dataForFirstFragment.observe(viewLifecycleOwner) { sharedData ->
            // value used in onResume()
            if (!sharedData.isNullOrBlank()) {
                Toast.makeText(context, "Shared data :: $sharedData", Toast.LENGTH_LONG).show()
            }
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
        fragmentFirstBinding?.btnNextFragment?.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "inside onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "inside onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "inside onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "inside onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LOG_TAG, "inside onDestroyView()")
        // remove view binding
        fragmentFirstBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "inside onDestroy()")
    }

    companion object {
        private var fragmentHandler: FragmentHandlerContract? = null

        fun setFragmentHandler(fragmentHandlerContract: FragmentHandlerContract) {
            fragmentHandler = fragmentHandlerContract
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnNextFragment -> replaceFragment()
        }
    }

    private fun replaceFragment() {
        //method 1
        // You should avoid depending on or manipulating one fragment from another and passing data directly between the fragments
        // The Fragment KTX library provides two options for communication: a shared ViewModel and a Fragment Result API
//        activity?.supportFragmentManager?.beginTransaction()?.apply {
//            replace(
//                R.id.fragmentContainer,
//                SecondFragment.newInstance("data-1", "data-2")
//            )
//            addToBackStack(SecondFragment.LOG_TAG)
//            commit()
//        }

        //method 2
        // Android KTX way to perform Fragment transaction but again, fragment should not do replacing and communication directly
//        activity?.supportFragmentManager?.commit {
//            addToBackStack(SecondFragment.LOG_TAG)
//            replace(R.id.fragmentContainer, SecondFragment.newInstance("data-1", "data-2"))
//        }

        //method 3
        // replace fragment using an interface via host activity
        fragmentHandler?.openSecondFragment("data-1", "data-2")

        // data sharing via ViewModel
        viewModel.setDataForSecondFragment("data from First Fragment..")
    }
}