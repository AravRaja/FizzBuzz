package com.example.fizzbuzz


enum class Modifier {
    REVERSE, BEFORE_B, DESTROY_EARLIER, NONE
}
data class Rule(
    val num: Int,
    val word: String = "",
    val modifier: Modifier = Modifier.NONE
)

fun addBeforeWordsStartingWithB(word: String, wordList: MutableList<String> ) {
    var count = 0
    for (i in wordList){

        if (i.startsWith('B')){
            wordList.add(count, word)
            return
        }
        count += 1
    }

    wordList.add(word)

}

fun numClassifier(i: Int, rules: MutableList<Rule>): String{
    val numberToWord = mutableListOf<String>()
    for (rule: Rule in rules){
        if (i%rule.num == 0 ){
            if (rule.modifier == Modifier.REVERSE){
                numberToWord.reverse()
            }
            if (rule.modifier == Modifier.BEFORE_B){
                addBeforeWordsStartingWithB(rule.word, numberToWord)
            }
            if (rule.modifier == Modifier.DESTROY_EARLIER){
                numberToWord.removeAll(numberToWord)
                numberToWord.add(rule.word)
            }
            else{
                numberToWord.add(rule.word)
            }

        }

    }
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

    println("Enter an integer greater than or equal to 1 to be the maximum number in your fizzbuzz sequence")
    var maxNum = readln().toIntOrNull()
    while (maxNum == null) {
        println("Please enter only valid integers greater than or equal 1, make sure you only use digits and no floats!")
        maxNum = readln().toIntOrNull()

    }

    println("Which rules would you like to use ")
    println("Type 'a' to use all the rules")
    println("Type 'e' to use no rules")
    println("If you want to only use partial rules type the number that represents the rule separated by a comma")
    println("E.G 3,5,13,17")
    println("RULES AVAILABLE: [3, 5, 7, 11, 13, 17]")

    val initialRules = mapOf<Int, Rule>(
        3 to Rule(num = 3, word = "Fizz"),
        5 to Rule(num = 5, word = "Buzz"),
        7 to Rule(num = 7, word = "Bang"),
        11 to Rule(num = 11, word = "Bong", modifier = Modifier.DESTROY_EARLIER),
        13 to Rule(num = 13 , word = "Fezz", modifier = Modifier.BEFORE_B),
        17 to Rule(num = 17, modifier = Modifier.REVERSE)
    )
    val rules = mutableListOf<Rule>()
    while(rules.isEmpty()){
        val input: String = readln()
        if (input.lowercase() == "a") rules.addAll(initialRules.values)
        if (input.lowercase() == "e") break
        else {
            try {
                for(i in input.replace(" ", "").split(',').toSet()){
                    if (i.toInt() in initialRules.keys) initialRules[i.toInt()]?.let { rules.add(it) }
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
    rules.sortBy { it.num }


    while (!allRulesAdded){
        println("Would you like to add other custom rules?")
        println("answer with 'y' for yes and any other string for no")
        if (readln().lowercase() != "y") allRulesAdded = true
        else{
            println("First Enter the number for your rule (e.g '8') ")
            var input: Int? = readln().toIntOrNull()
            while(input == null) {
                input = readln().toIntOrNull()
                println("Make sure to format your integer as a singular integer with no decimal points!")
            }
            val num: Int = input
            println("Now decide the modifiers you would like to use: type '1' for reverse, 2 for 'placeBeforeB', 3 for 'destroyEarlier' and 4 (or anything else) for No Modifiers")
            input = readln().toIntOrNull()
            var modifier: Modifier = Modifier.NONE
            if (input == 1) modifier = Modifier.REVERSE
            else if (input == 2) modifier = Modifier.BEFORE_B
            else if (input == 3) modifier = Modifier.DESTROY_EARLIER
            var word = ""
            if (modifier != Modifier.REVERSE) {
                println("Next decide the String to connect to the rule")
                word = readln()
            }
            rules.add( Rule(num = num, word = word, modifier = modifier ))
        }

    }
    println(rules)


    for (i in 1..maxNum){
       println(numClassifier(i, rules))
    }

}