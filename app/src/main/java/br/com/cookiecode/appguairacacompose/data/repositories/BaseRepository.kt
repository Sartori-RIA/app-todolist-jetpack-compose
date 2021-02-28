package br.com.cookiecode.appguairacacompose.data.repositories

import androidx.lifecycle.LiveData

interface BaseRepository<T, PK> {

    fun all(): LiveData<List<T>>

    fun show(id: PK): LiveData<T>

    fun create(data: T)

    fun update(data: T)

    fun delete(data: T)
}