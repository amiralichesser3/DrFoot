package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Me(@PrimaryKey val id: Int,
             val name: String,
             val imageUrl: String?,
             val imageLocal: Int?,
             val email: String)