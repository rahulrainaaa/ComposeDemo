package com.example.testcompose3app.db

import androidx.room.*

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    val bookId: Int? = null,

    @ColumnInfo(name = "book_name")
    var bookName: String? = null,

    @ColumnInfo(name = "book_description")
    var bookDescription: String? = null,

    )

@Dao
interface BookDao {
    @Query("select * from book_table")
    fun getAll(): List<Book>

    @Insert
    fun insert(book: Book): Int

    @Insert
    fun insertAll(vararg book: Book)

    @Delete
    fun delete(book: Book)

    @Query("Delete from book_table")
    fun deleteAll()

    @Query("Select * from book_table where book_id=:bookId")
    fun get(bookId: Book): Book
}

@Database(entities = [Book::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun getBookDao(): BookDao
}