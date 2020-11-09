package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Me(@PrimaryKey var id: Int,
         var name: String?,
         var imageUrl: String?,
         var imageLocal: Int?,
         var email: String?,
         var isComplete: Boolean?)