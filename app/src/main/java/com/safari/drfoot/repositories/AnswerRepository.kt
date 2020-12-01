package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.AnswerDao
import com.safari.drfoot.entities.Answer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnswerRepository @Inject constructor(private val answerDao: AnswerDao) {
    fun exists(): Boolean {
        return answerDao.count() != 0
    }

    fun load(userId: Int, mode: String, sectionId: Int): LiveData<List<Answer>> {
        return answerDao.load(userId, mode, sectionId)
    }

    fun save(Answer: Answer) {
        AsyncTask.execute {
            answerDao.save(Answer)
        }
    }

    fun save(Answers: ArrayList<Answer>) {
        AsyncTask.execute {
            answerDao.save(Answers)
        }
    }
}