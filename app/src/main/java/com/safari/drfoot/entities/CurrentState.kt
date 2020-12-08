package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CurrentState (@PrimaryKey val id: Int, var selectedSectionId: Int, var selectedPersonId: Int, var coinCount: Int)