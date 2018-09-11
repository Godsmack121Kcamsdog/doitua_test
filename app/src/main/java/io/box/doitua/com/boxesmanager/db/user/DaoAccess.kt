package io.box.doitua.com.boxesmanager.db.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface DaoAccess {

    @Query("SELECT * FROM items")
    fun getAll(): Single<List<Box>>

    @Query("SELECT * FROM words WHERE email LIKE :email LIMIT 1")
    fun findByEmail(email: String): Single<User>

    @Query("SELECT * FROM items WHERE id LIKE :id")
    fun findById(id: Int): Single<List<Box>>

    @Insert
    fun insertAll(vararg entities: User)

    @Insert(onConflict = REPLACE)
    fun insert(entity: User)

    @Insert(onConflict = REPLACE)
    fun insert(item: Box)

    @Delete
    fun delete(entity: User)
}
