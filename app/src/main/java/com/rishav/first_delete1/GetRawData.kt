package com.rishav.first_delete1

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus{
    OK,NOT_INITIALIZED,INITIALIZATION_FAILURE,IDLE
}

class GetRawData(private val listener:OnDataComplete):AsyncTask<String,Void,String>()
{
    interface OnDataComplete{
        fun ondatacomplete(result:String,status:DownloadStatus)
    }

    var status=DownloadStatus.IDLE


    override fun doInBackground(vararg params: String?): String {
        if(params[0]==null)
        {
            status=DownloadStatus.NOT_INITIALIZED
            return ""
        }
        try{
            status=DownloadStatus.OK
            return URL(params[0]).readText()
        }catch(e:Exception)
        {
            val message=when(e)
            {
                is MalformedURLException->">>>>>>>>>>>>>>>>>> mal ${e.message}"
                is IOException->">>>>>>>>>>>>>>>>>>>> io ${e.message}"
                else->">>>>>>>>>>>>>>>>>>>>>>>>> un ${e.message}"
            }
            status=DownloadStatus.INITIALIZATION_FAILURE
            return message
        }
    }
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        listener.ondatacomplete(result,status)
    }
}