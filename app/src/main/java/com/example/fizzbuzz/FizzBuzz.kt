package com.example.fizzbuzz

//Function that takes the output res and adds Fezz in the correct place before any B-word
fun addFezz(res: MutableList<String> ) {
    var isFezzAdded = false
    var count = 0
    for (i in res){

        if (i != "Fizz"){
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

fun numClassifier(i: Int): String{
    val res = mutableListOf<String>() // res represent the result of any certain number
    //constructs res which will be the output depending on the conditions
    if (i%3 ==0) res.add("Fizz")
    if (i%5 ==0) res.add("Buzz")
    if (i%7 == 0) res.add("Bang")
    if (i%11 == 0) {
        res.removeAll(res)
        res.add("Bong")
    }
    if (i%13 == 0) addFezz(res)
    if (i%17 ==0) res.reverse()
    if (res.isEmpty()) res.add(i.toString())

    return (res.joinToString(separator = ""))
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
    var maxNum = 0
    println("Enter an integer greater than or equal to 1 to be the maximum number in your fizzbuzz sequence")
    while (maxNum == 0) {
        val input: String? = readlnOrNull()
        try {
            maxNum = input!!.toInt()
        } catch (e: NumberFormatException) {
            println("Please enter only valid integers greater than or equal 1, make sure you only use digits and no floats!")
        }
    }

    println("Which rules would you like to use ")
    println("Type 'a' to use all the rules")
    println("If you want to only use partial rules type the number that represents the rule separated by a comma")
    println("E.G 3,5,13,17")
    println("RULES AVAILABLE: [3, 5, 7, 11, 13, 17]")


    for (i in 1..maxNum){
       println(numClassifier(i))
    }

}