@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sq = 0.0
    for (i in 0..v.size - 1) {
        sq = v[i] * v[i] + sq
    }
    sq = sqrt(sq)
    return sq
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.size == 0) return 0.0
    else return list.sum() / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val md = mean(list)
    for (i in 0..list.size - 1) {
        list[i] -= md
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0..a.size - 1) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var mnch = 0
    var count = 0
    val x1 = x.toDouble()
    for (i in 0..p.size - 1) {
        mnch += (p[i] * (x1.pow(i).toInt()))
    }
    return when {
        p.isEmpty() -> 0
        else -> mnch
    }
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in list.size - 1 downTo 0) {
        for (j in 0..i) {
            sum += list[j]
        }
        list[i] = sum
        sum = 0
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val res = mutableListOf<Int>()
    var n1 = n
    when {
        n1 == 0 -> res.add(0)
    }
    while (n1 > 0) {
        res.add(n1 % base)
        n1 /= base
    }
    return res.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var st = ""
    var n1 = n
    var c = 0
    var sti = listOf<Int>()
    val alf = "abcdefghijklmnopqrstuvwxyz"
    if (n==0) return "0"
    when {
        base <= 10 -> {
            sti = convert(n, base)
            for (i in sti) {
                st += i.toString()
                st.reversed()
            }
        }

        else -> while (n1 > 0) {
            c = n1 % base
            when {
                c < 10 -> st = c.toString() + st
                else -> st = alf[c - 10].toString() + st
            }
            n1 /= base
        }
    }
    return st
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = 0
    val b = base.toDouble()
    var st = 0
    for (i in digits.size - 1 downTo 0) {
        sum += digits[i] * (b.pow(st).toInt())
        st += 1
    }
    return sum
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val st = str
    val lip = mutableListOf<Int>()
    val alf = "0123456789abcdefghijklmnopqrstuvwxyz"
    for (i in st) {
        for (j in 0..alf.length - 1) {
            if (i == alf[j]) {
                lip += j
                break
            }
        }
    }
    return decimal(lip, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var n1 = n
    var st = ""
    val r = mapOf(
        1000 to 'M', 900 to "CM", 500 to 'D',
        400 to "CD", 100 to 'C', 90 to "XC", 50 to 'L',
        40 to "XL", 10 to 'X', 9 to "IX", 5 to 'V', 4 to "IV",
        1 to 'I'
    )
    for (i in r) {
        while (n1 >= i.key) {
            st += i.value
            n1 -= i.key
        }
    }
    return st
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var thou = 0
    var unit = 0
    var st = ""
    when {
        n >= 1000 -> {
            thou = n / 1000
            unit = n % 1000
        }

        else -> unit = n
    }
    val thou1 = thou / 100
    val thou2 = thou / 10 % 10
    val thou3 = thou % 10
    val unit1 = unit / 100
    val unit2 = unit / 10 % 10
    val unit3 = unit % 10
    if (n >= 1000) st = numinStr(thou1, thou2, thou3) + last(thou3, 1, thou2) +
            thousend(thou2, thou3) + numinStr(unit1, unit2, unit3) + last(unit3, 0, unit2)
    else st = numinStr(unit1, unit2, unit3) + last(unit3, 0, unit2)
    return st.trim()
}

fun numinStr(fir1: Int, fir2: Int, fir3: Int): String {
    var st = ""
    val hundreds = mapOf<Int, String>(
        1 to "сто ", 2 to "двести ", 3 to "триста ", 4 to "четыреста ",
        5 to "пятьсот ", 6 to "шестьсот ", 7 to "семьсот ", 8 to "восемьсот ",
        9 to "девятьсот "
    )
    val exceptions = mapOf<Int, String>(
        0 to "десять ", 1 to "одиннадцать ", 2 to "двенадцать ",
        3 to "тринадцать ", 4 to "четырнадцать ", 5 to "пятнадцать ",
        6 to "шестнадцать ", 7 to "семнадцать ", 8 to "восемнадцать ",
        9 to "девятнадцать "
    )
    val dozens = mapOf<Int, String>(
        2 to "двадцать ", 3 to "тридцать ", 4 to "сорок ", 5 to "пятьдесят ",
        6 to "шестьдесят ", 7 to "семьдесят ", 8 to "восемьдесят ", 9 to "девяносто "
    )
    if (fir1 > 0) st += hundreds[fir1]
    if (fir2 == 1) st += exceptions[fir3]
    else if (fir2 != 0) st += dozens[fir2]
    return st
}

fun thousend(a: Int, b: Int): String {
    var st = ""
    when {
        a == 1 || b in 5..9 || b == 0 -> st = "тысяч "
        else -> when {
            b == 1 -> st = "тысяча "
            else -> st = "тысячи "
        }
    }
    return st
}

fun last(fir3: Int, b: Int, a: Int): String {
    var st = ""
    val units = mapOf<Int, String>(
        1 to "один", 2 to "два", 3 to "три ", 4 to "четыре ",
        5 to "пять ", 6 to "шесть ", 7 to "семь ", 8 to "восемь ",
        9 to "девять ", 11 to "одна ", 12 to "две "
    )
    if (a != 1)
        if (fir3 in 3..9) st += units[fir3]
        else if(fir3 != 0 ) st += units[fir3 + b * 10]
    return st
}