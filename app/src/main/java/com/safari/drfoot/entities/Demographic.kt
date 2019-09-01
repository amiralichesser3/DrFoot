package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Demographic(@PrimaryKey val id: Int, val personId: Int, var info: String, val history: String, val medication: String,
                  val diabetesHistory: String, val labResultsImage: Int, val physicalExamResults: String, val presentingComplain: String,
                  val presentingComplainImage: Int)