package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class SubSection(@PrimaryKey val id: Int,
                 val name: String,
                 val isLocked: Boolean,
                 val image: String?,
                 val sectionId: Int,
                 val text: String?,
                 val extraImage: String?,
                 val hint: String?,
                 val hint2: String?,
                 val hint3: String?,
                 val hintImage: String?)