package com.example.fizzbuzz



//Function that takes the output res and adds Fezz in the correct place before any B-word
fun addFezz(res: MutableList<String> ) {
    var isFezzAdded = false
    var count = 0
    for (i in res){

        if ((!isFezzAdded) && (i != "Fizz")){
            res.add(count, "Fezz")
            isFezzAdded = true
            break
        }
        count += 1
    }
    if (!isFezzAdded) {
        res.add("Fezz")
    }
}
fun main() {

    //SOLUTION 1
    /*
    for (i in 1..100){
        if (i % 15 == 0)  print("FizzBuzz")
        else if (i % 5 == 0)  println("Buzz")
        else if (i % 3 == 0)  println("Fizz")
        else println(i)

    }
    */


    //SOLUTION 2
    val res = mutableListOf<String>() // res represent the result of any certain number
    // Loops through numbers and constructs res which will be the output depending on the conditions
    for (i in 1..255){

        res.removeAll(res)
        if (i%3 ==0) res.add("Fizz")
        if (i%5 ==0) res.add("Buzz")
        if (i%7 == 0) res.add("Bang")
        if (i%11 == 0) {
            res.removeAll(res)
            res.add("Bong")
        }
        if (i%13 == 0) addFezz(res)
        if (i%17 ==0) res.reverse()
        if (res.isEmpty()) println(i)
        else println(res.joinToString(separator = ""))

    }

}