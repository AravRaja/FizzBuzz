package com.example.fizzbuzz


fun addFezzBeforeWordsStartingWithB(wordList: MutableList<String> ) {
    var count = 0
    for (i in wordList){

        if (i.startsWith('B')){
            wordList.add(count, "Fezz")
            return
        }
        count += 1
    }

    wordList.add("Fezz")

}

fun numClassifier(i: Int, rules: MutableList<Int>): String{
    if (0 in rules) return i.toString()

    val numberToWord = mutableListOf<String>()
    if (i%11 == 0 && (11 in rules)) numberToWord.add("Bong")
    else {
        if (i % 3 == 0 && (3 in rules)) numberToWord.add("Fizz")
        if (i % 5 == 0 && (5 in rules)) numberToWord.add("Buzz")
        if (i % 7 == 0 && (7 in rules)) numberToWord.add("Bang")
    }
    if (i%13 == 0 && (13 in rules)) addFezzBeforeWordsStartingWithB(numberToWord)
    if (i%17 ==0 && (17 in rules)) numberToWord.reverse()
    if (numberToWord.isEmpty()) numberToWord.add(i.toString())

    return (numberToWord.joinToString(separator = ""))
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
        try {
            maxNum = readln().toInt()
        } catch (e: NumberFormatException) {
            println("Please enter only valid integers greater than or equal 1, make sure you only use digits and no floats!")
        }
    }

    println("Which rules would you like to use ")
    println("Type 'a' to use all the rules")
    println("Type 'e' to use no rules")
    println("If you want to only use partial rules type the number that represents the rule separated by a comma")
    println("E.G 3,5,13,17")
    println("RULES AVAILABLE: [3, 5, 7, 11, 13, 17]")
    val rules = mutableListOf<Int>()
    while(rules.isEmpty()){
        val input: String = readln()
        if (input.lowercase() == "a") rules.addAll(arrayOf(3,5,7,11,13,17))
        if (input.lowercase() == "e") rules.add(0)
        if (rules.isEmpty()) {
            try {
                for(i in input.replace(" ", "").split(',').toSet()){
                    if (i.toInt() in arrayOf(3,5,7,11,13,17)) rules.add(i.toInt())
                    else throw(Exception("Invalid Input"))
                }


            } catch (e: Exception) {
                println("Make sure your input is valid and you only use the numbers [3, 5, 7, 11, 13, 17] separated by commas")
                println("PLEASE TRY AGAIN:")
                rules.removeAll(rules)
            }
        }
    }
    var allRulesAdded = false
    val customRules = mutableMapOf<Int, String>()
    while (!allRulesAdded){
        println("Would you like to add other custom rules?")
        println("answer with 'y' for yes and any other string for no")
        val input: String = readln()
        if (input.lowercase() != "y") allRulesAdded = true
        else{
           println("NK")
        }

    }



    for (i in 1..maxNum){
       println(numClassifier(i, rules))
    }

}