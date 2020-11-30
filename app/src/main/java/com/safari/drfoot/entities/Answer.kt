package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Answer(@PrimaryKey val id: Int,
                 val userId: Int,
                 val mode: String,
                 val isCorrect: Boolean,
                 val sectionId: Int,
                 val text: String?,
                 val image: String?)