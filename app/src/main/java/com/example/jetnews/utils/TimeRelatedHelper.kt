package com.example.jetnews.utils


import java.text.SimpleDateFormat
import java.util.Date

fun getCurrentTimeMillis():Long{
    return System.currentTimeMillis()
}

fun formatSystemTime(timestamp:Long):String{
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = Date(timestamp)
    return sdf.format(date)
}

fun timePeriodCalculator(current:String,created:String):String{
    val currentDate = current.split(" ")[0]
    val createdDate = created.split(" ")[0]
    val currentTime = current.split(" ")[1]
    val createdTime = created.split(" ")[1]

    val currentYear = currentDate.split("-")[0].toInt()
    val currentMonth = currentDate.split("-")[1].toInt()
    val currentDay = currentDate.split("-")[2].toInt()
    val currentHour = currentTime.split(":")[0].toInt()
    val currentMin = currentTime.split(":")[1].toInt()


    val createdYear = createdDate.split("-")[0].toInt()
    val createdMonth = createdDate.split("-")[1].toInt()
    val createdDay = createdDate.split("-")[2].toInt()
    val createdHour = createdTime.split(":")[0].toInt()
    val createdMin = createdTime.split(":")[1].toInt()

    return if(currentYear > createdYear){
        (currentYear - createdYear).toString() + "year ago"
    }else if(currentMonth > createdMonth){
        (currentMonth - createdMonth).toString() + "month ago"
    }else if(currentDay > createdDay){
        (currentDay - createdDay).toString() + "day ago"
    }else if(currentHour > createdHour){
        (currentHour - createdHour).toString() + "hour ago"
    }else if(currentMin > createdMin){
        (currentMin - createdMin).toString() + "min ago"
    }else{
        "just now"
    }
}

fun dateLocaleFromDatabase(date : String):String{
    val createdDate = date.split(" ")[0]
    val createdYear = createdDate.split("-")[0]
    val createdMonth = createdDate.split("-")[1].toInt()
    val createdDay = createdDate.split("-")[2]

    val monthAfterFormat = when(createdMonth){
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "Aug"
        9 -> "Sept"
        10 -> "Oct"
        11 -> "Nov"
        12 -> "Dec"
        else -> {""}
    }

    return createdDay +" "+ monthAfterFormat + " "+createdYear
}