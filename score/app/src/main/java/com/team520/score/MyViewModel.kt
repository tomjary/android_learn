package com.team520.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author: Administrator
 * @date: 2020/9/18 16:17
 */
class MyViewModel: ViewModel() {
    var aTeamScore: MutableLiveData<Int> = MutableLiveData()
    var bTeamScore: MutableLiveData<Int> = MutableLiveData()
    var aBack: Int? = null
    var bBack: Int? = null

    fun getaTeamScore(): MutableLiveData<Int> {
        if (aTeamScore.value == null){
            aTeamScore.value = 0
        }
        return aTeamScore
    }
    fun getbTeamScore(): MutableLiveData<Int> {
        if (bTeamScore.value == null){
            bTeamScore.value = 0
        }
        return bTeamScore
    }

    fun aTeamAdd(p: Int) {
        aBack = aTeamScore.value
        bBack = bTeamScore.value
        aTeamScore.value = aTeamScore.value?.plus(p)
    }
    fun bTeamAdd(p: Int) {
        aBack = aTeamScore.value
        bBack = bTeamScore.value
        bTeamScore.value = bTeamScore.value?.plus(p)
    }
    fun reset(){
        aBack = aTeamScore.value
        bBack = bTeamScore.value
        aTeamScore.value = 0
        bTeamScore.value = 0
    }
    fun undo() {
        aTeamScore.value = aBack
        bTeamScore.value = bBack
    }

}