package com.example.homework21.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.api.RetrofitService
import kotlinx.coroutines.*


class NewsViewModel: ViewModel() {

    fun reCall() : Job {
        return viewModelScope.launch {
            withContext(Dispatchers.IO){
                while (isActive){
                    val result = RetrofitService.getAllNews.getNews()
                    delay(5000)
                }
            }
        }

    }

 // am funqcias gamovidzaxebt da sul wava da wava gaaketebs reqvestebs da  ro mogvindeba ro gavtishiot
    // davwert reCall.cancel() axla activitishimaq roca activiti mokvdeba mashin mokvdeba esec :D D: :D :D
    // is vici ro tu activiti mokvda esec mokvdeba isedac mara sademonstraciot wava  :D :D :D

}