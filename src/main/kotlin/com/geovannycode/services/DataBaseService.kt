package com.geovannycode.services

import com.geovannycode.Database.Database
import com.geovannycode.Database.getCourses
import com.geovannycode.Database.getStudents
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
        database.tableUsers.clear()
        database.tableCourses.clear()
    }
}
