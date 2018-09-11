package io.box.doitua.com.boxesmanager.db.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "items")
class Box(val height: Int, val width: Int, val length: Int, val color: String, val isNamed: Boolean, val size: String,
          @ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["itemId"]) val id: Int) {

    @PrimaryKey(autoGenerate = true)
    var itemId: Int? = null

    override fun toString(): String {
        return "Box $itemId; $height; $width; $length, $color," +
                " $isNamed, $id"
    }
}
