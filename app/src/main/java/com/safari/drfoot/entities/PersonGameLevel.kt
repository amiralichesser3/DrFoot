package com.safari.drfoot.entities

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["personId", "gameLevelId"])
class PersonGameLevel(val personId: Int,
                      val gameLevelId: Int)