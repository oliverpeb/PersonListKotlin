package com.example.personlistkotlin

data class Person(
    var id: Int = -1,
    var name: String,
    var address: String,
    var age: Int
){
    override fun toString(): String {
        return "$id $name $age $address"
    }
}
