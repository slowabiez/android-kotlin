package com.anara.helloworld

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val data = MutableLiveData<List<Hewan>>()

    init {
        this.data.value = initData()
        this.retrieveDataFromServer()
    }

    private fun retrieveDataFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = HewanApi.service.getHewan()
                Log.d("MainViewModel", result)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Gagal: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = this.data

    private fun initData(): List<Hewan> {
        return listOf(
            Hewan("Angsa"  , "Cygnus olor"            , R.drawable.angsa),
            Hewan("Ayam"   , "Gallus gallus"          , R.drawable.ayam),
            Hewan("Bebek"  , "Cairina moschata"       , R.drawable.bebek),
            Hewan("Domba"  , "Ovis ammon"             , R.drawable.domba),
            Hewan("Kalkun" , "Meleagris gallopavo"    , R.drawable.kalkun),
            Hewan("Kambing", "Capricornis sumatrensis", R.drawable.kambing),
            Hewan("Kelinci", "Oryctolagus cuniculus"  , R.drawable.kelinci),
            Hewan("Kerbau" , "Bubalus bubalis"        , R.drawable.kerbau),
            Hewan("Kuda"   , "Equus caballus"         , R.drawable.kuda),
            Hewan("Sapi"   , "Bos taurus"             , R.drawable.sapi),
        )
    }

}