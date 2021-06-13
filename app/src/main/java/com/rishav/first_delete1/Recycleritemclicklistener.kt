package com.rishav.first_delete1

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class Recycleritemclicklistener(context: Context, recyclerview: RecyclerView,private val listener:OnClickListenerFunction)
    :RecyclerView.SimpleOnItemTouchListener(){
    val TAG="TOUCH"
    interface OnClickListenerFunction{
        fun OnNormalClick(view: View,position:Int)
        fun OnNormalLongclick(view:View,position:Int)
    }

    private val gestureDetector= GestureDetectorCompat(context,object:GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG,"---------------------------onSingleTapUp: starts")
            val childview=recyclerview.findChildViewUnder(e.x,e.y)
            listener.OnNormalClick(childview!!,recyclerview.getChildAdapterPosition(childview!!))
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG,"-------------------------onLongTap: starts")
            val childview=recyclerview.findChildViewUnder(e.x,e.y)
            listener.OnNormalLongclick(childview!!,recyclerview.getChildAdapterPosition(childview!!))
        }
    })
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d("message",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> onintersepttouchallowed ${e}")
//        return super.onInterceptTouchEvent(rv, e)
        val result= gestureDetector.onTouchEvent(e)
        Log.d(TAG,"click event $result")
        return result
    }
}