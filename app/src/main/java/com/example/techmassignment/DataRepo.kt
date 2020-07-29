package com.example.techmassignment

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class DataRepo {
    var loadingState = MutableLiveData<Boolean>()
    var filteredLiveData = MediatorLiveData<DataOfImagesBean>()
    /**
     * This method is use to get data from asset folder
     */
    fun getDataFromAsset() {
        var json: String = ""
        try {
            loadingState.value = true
            val inputStream = MainApplication.appContext.getAssets().open("techassignment.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
            val gson  = Gson()
            val type =  object : TypeToken<DataOfImagesBean>(){}.type
            val dataBean : DataOfImagesBean =   gson.fromJson(json,type)
            filteredLiveData.value = dataBean
        } catch (ioException: IOException) {
            loadingState.value = false
            ioException.printStackTrace()
        }
        // print the data
//        Log.i("data", json)
        loadingState.value = false
    }

}