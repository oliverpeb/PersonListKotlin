package com.example.personlistkotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

class PersonsViewModel : ViewModel() {
    private var _nextId = 1
    private var _personsList = mutableListOf(
        Person(_nextId++, "Oliver", "Roskilde", 27,),
        Person(_nextId++, "Bent", "København", 37),

    )
    private var _persons = MutableLiveData<List<Person>>(_personsList)
    val selected = MutableLiveData<Person>()

    var persons: LiveData<List<Person>> = _persons
    val adding: MutableLiveData<Boolean> = MutableLiveData(false)

    fun add(person: Person) {
        person.id = _nextId++
        _personsList.add(person)
        _persons.value = _personsList
    }
    fun remove(id: Int){
        _personsList.removeAll { person -> person.id == id}
    }

    fun update(id: Int, info: Person){
        val person: Person = _personsList.first { ps -> ps.id == id }
        person.name = info.name
        person.address = info.address
        person.age = info.age
    }

    operator fun get(position: Int): Person{
        return _personsList[position]
    }

}