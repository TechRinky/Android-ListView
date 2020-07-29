package com.example.techmassignment

import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private  var dataRepo = DataRepo()
    var liveData = dataRepo.filteredLiveData
    var loadingState = dataRepo.loadingState

    fun getDataFromAsset(){
        dataRepo.getDataFromAsset()
    }

}
