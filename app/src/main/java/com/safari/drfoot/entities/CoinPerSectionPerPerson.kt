package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CoinPerSectionPerPerson(@PrimaryKey(autoGenerate = true) val id: Int,
                              val personId: Int,
                              val sectionId: Int,
                              var coinCount: Int)