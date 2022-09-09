package com.example.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.fragment.app.replace

/**
 * A simple [AppCompatActivity] subclass.
 * Used to host the fragments.
 */
class MainActivity : AppCompatActivity(), FragmentHandlerContract {

    val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "inside onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "inside onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "inside onResume()")
        FirstFragment.setFragmentHandler(this)
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "inside onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "inside onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "inside onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "inside onRestart()")
    }

    override fun openSecondFragment(param1: String, param2: String) {
        // with the help of androidx.fragment:fragment-ktx
        supportFragmentManager.commit {
            addToBackStack(SecondFragment.LOG_TAG)
            replace(R.id.fragmentContainer, SecondFragment.newInstance("data-1", "data-2"))
        }
    }
}