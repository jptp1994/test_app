package com.jean.mercadopago.database.dataBaseInter


import androidx.lifecycle.LiveData
import androidx.room.*
import com.jean.mercadopago.database.modelDataBase.FindModel

@Dao
interface FindsLogs {

    @Transaction
    fun insertNewFind(agreementResponse: FindModel){
        insertFind(agreementResponse)
    }

    @Transaction
    fun deleteAllFinds(agreementResponse: FindModel){
        deleteAllAgreementsResponse()
    }
    @Query("SELECT * FROM findTable")
    fun getAgreementResponse():  LiveData<List<FindModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFind(agreementResponse: FindModel)

    @Query("DELETE FROM findTable")
    fun deleteAllAgreementsResponse()


}