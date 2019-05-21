package com.hafezie.barname.dagger

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target
import kotlin.reflect.KClass

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
