package com.example.injaaz.data.data_source

import com.example.injaaz.data.model.Task

object DataSource {
    var tasks = arrayListOf(
        Task("Do homework", false),
        Task("Go to Work", true),
        Task("Read books", true),
        Task("Sleep", false)
    )
}
