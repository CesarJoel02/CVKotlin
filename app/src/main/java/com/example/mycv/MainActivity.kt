package com.example.mycv

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AsyncTaskExample(this).execute()
        val txt = findViewById<TextView>(R.id.textView2)
    }


}


class AsyncTaskExample(private var activity: MainActivity?) : AsyncTask<String, String, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: String?): String {

        var result = ""
        try {
            val url = URL("https://jsonplaceholder.typicode.com/todos/1")
            Log.d("URL", "Url - " + url)

            val httpURLConnection : HttpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.inputStream.bufferedReader().use {
                val responseCode: Int = httpURLConnection.responseCode
                Log.d("responseCode", "responseCode - " + responseCode)
                result = it.readText()
                Log.d("respuesta",it.readText())

            }
        } catch (ex: Exception) {
            Log.d("", "Error in doInBackground " + ex.message)
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result == "") {
        } else {
            var parsedResult = result
            var jsonfromString = JSONObject(result)
            var title : String = jsonfromString.get("title").toString()

            val txt = activity?.findViewById<TextView>(R.id.textView2)
            txt?.text = title
            Log.d("post",title)
            var jsonObject: JSONObject? = JSONObject(result)
        }
    }
}