package com.example.techmassignment

import androidx.lifecycle.ViewModel

/**
 * This is viewmodel which communicates with view and the repo
 */
class DataViewModel : ViewModel() {
    private  var dataRepo = DataRepo()
    var liveData = dataRepo.filteredLiveData
    var loadingState = dataRepo.loadingState

    /**
     * This method is responsible to call data from repository
     */
    fun getDataFromAsset(){
        dataRepo.getDataFromAsset()
    }

}
