import java.io.File
import java.util.*

fun game(usrStr: String?, random: String): Pair <Int?, Int?> {
    val res1 = usrStr?.foldIndexed(""){index, acc, elem -> acc + (if (elem == random[index]) elem  else ' ')}?.filter {it != ' ' }
    val bulls = res1?.length
    val s1 = usrStr?.filter {!res1!!.contains(it) }
    val s2 = random.filter {!res1!!.contains(it) }
    val cows = s1?.fold(0) {acc, elem -> (if (s2.contains(elem)) 1 else 0) + acc}
    return Pair(bulls,cows)
}
fun choiceWord(data: List<String>): String {
    val index: Int = Random().nextInt(data.size)
    return data[index]
}
fun main(args: Array<String>) {
    val file = File("task/dictionary.txt")
    val buf = file.bufferedReader()
    val text: List<String> = buf.readLines()
    println("Welcome ")
    while (true) {
        val gameWord: String = choiceWord(text)
        println("You have 10 attempts")
        val len = gameWord.length
        println("I offered a $len - letter word, your guess?")
        var i = 10
        while (i != 0 ) {
            println(">>$i>>Input:")
            val userWord: String? = readLine()
            if (userWord!!.all { it.isLetter() } ) {
                if (userWord.length == len) {
                    val res = game(userWord, gameWord)
                    print("Bulls: ")
                    println(res.first)
                    print("Cows: ")
                    println(res.second)
                    if (res.first == gameWord.length) {
                        println("You win")
                        break
                    }
                    i--
                } else {
                    println("Please, enter $len-letter word")
                }
            }else
                    println("Please, enter only letter word")
        }
        if (i == 0) {
            print("You lose,")
            println("The secret word is $gameWord")
        }
        println("Do you want play again? y/n")
        val ch : String? = readLine()
        if (ch == "n") {
            println("Bye")
            break
        }
    }
}