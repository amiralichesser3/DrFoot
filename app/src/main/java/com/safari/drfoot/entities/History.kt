package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class History(@PrimaryKey val id: Int, val personId: Int, var pastFootHistory: String, val diabetesHistory: String, val diabetesHistoryimage: Int,
              val drugHistory: String, val pastMedicalHistory: String, val familyHistory: String, val psychologyHistory: String)