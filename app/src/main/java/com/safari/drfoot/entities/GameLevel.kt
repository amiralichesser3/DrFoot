package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class GameLevel (@PrimaryKey val id: Int, val name: String, val isLocked: Boolean, val image: String?)