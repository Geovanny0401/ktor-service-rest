package com.geovannycode.services

import com.geovannycode.database.Database
import com.geovannycode.database.getCourses
import com.geovannycode.database.getStudents
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Single

@Single
class DataBaseService(
    private val database: Database,
) {
    private fun initData() = runBlocking {
        getCourses().forEach {
            database.tableCourses[it.id] = it
        }

        getStudents().forEach {
            database.tableStudents[it.id] = it
        }
    }

    fun getTables() = database

    fun initDataBaseService() {
        initData()
    }

    fun clearDataBaseService() {
        database.tableStudents.clear()
        database.tableCourses.clear()
    }
}
