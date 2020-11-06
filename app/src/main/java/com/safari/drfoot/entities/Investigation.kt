package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Investigation(@PrimaryKey val id: Int,
                    val personId: Int,
                    var investigationImage: Int)