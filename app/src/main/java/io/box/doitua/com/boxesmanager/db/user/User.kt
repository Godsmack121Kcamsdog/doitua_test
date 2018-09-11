package io.box.doitua.com.boxesmanager.db.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "words")
class User(val name: String, val email: String){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}