package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Person(@PrimaryKey val id: Int, val name: String,
             val imageUrl: String?, val imageLocal: Int?,
             var demographicInfo: String?, var historyInfo: String?,
             var mainProblem: String?, var initialExaminationResult: String?,
             var labResults: String?)