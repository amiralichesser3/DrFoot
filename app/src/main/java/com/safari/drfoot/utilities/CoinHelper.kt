package com.safari.drfoot.utilities

import android.content.Context
import com.safari.drfoot.utility.SharedPreferencesHelper

private val PARENT = "coinp"
private val BALANCE = "balance";
public class CoinHelper {

    companion object {
        fun getCoinBalance(context: Context): Int {
            return SharedPreferencesHelper.getString(context, PARENT, BALANCE, "10")!!.toInt()
        }

        fun addCoin(context: Context): Int {
            val newBalance = (getCoinBalance(context) + 1)
            SharedPreferencesHelper.setString(context, PARENT, BALANCE, newBalance.toString())
            return newBalance
        }

        fun removeCoin(context: Context): Int {
            var newBalance = (getCoinBalance(context) - 1)
            if (newBalance < 0) {
                newBalance = 0
            }
            SharedPreferencesHelper.setString(context, PARENT, BALANCE, newBalance.toString())
            return newBalance
        }
    }
}