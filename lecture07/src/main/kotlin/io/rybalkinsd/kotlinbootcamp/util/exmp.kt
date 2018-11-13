package io.rybalkinsd.kotlinbootcamp.util

import kotlin.reflect.KProperty

class User{
    val firstName : String = "Alisa"
    var age: Int by AgeDelegate()
}
class AgeDelegate{
    operator fun getValue(thisRef: Any?, property:KProperty<*>):Int{
        return 42
    }
    operator fun setValue (thisRef: Any?, property:KProperty<*>, value :Int){
        println("hello")
    }
}

