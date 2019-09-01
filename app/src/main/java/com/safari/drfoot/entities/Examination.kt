package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Examination(@PrimaryKey val id: Int, val personId: Int, var inspection: String, val inspectionImage: Int, val neurologicalAssessment: String,
                  val footwearAssessment: String, val generalExamination: String)