package com.example.roomdao.hospitalDatabase

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration1_2 = object: Migration(1,2){
//    indices = [Index(value = ["idProduct", "skuId"])])
//    val addIndex = """
//      CREATE INDEX IF NOT EXISTS index_Product_idProduct_skuId ON Product(idProduct, skuId)
//"""
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}