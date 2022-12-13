@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import kotlin.math.*
import lesson3.task1.digitNumber
import kotlin.math.pow

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    writer.use {
        for (line in File(inputName).readLines()) {
            if (line == "")
                writer.newLine()
            else
                if (line.first() != '_') {
                    writer.write(line)
                    writer.newLine()
                }
        }
    }
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    TODO()
}


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    writer.use {
        var maxx = 0
        for (i in File(inputName).readLines()) {
            maxx = max(maxx, i.trim().length)
        }
        for (i in File(inputName).readLines()) {
            for (j in 0 until (maxx - i.trim().length) / 2) writer.write(" ")
            writer.write(i.trim())
            writer.newLine()
        }
    }
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val inp = File(inputName).readLines()
    var maxx = -1
    var sum = 0
    for (i in inp) {
        maxx = max((i.trim()).length, maxx)
    }
    for (i in inp) {
        if (i == "") {
            writer.newLine()
            continue
        }
        if ((i.trim()).length == maxx) writer.write("${i.trim()}\n")
        else {
            val spl = i.trim().split(Regex("""\s+""")).toMutableList()
            if (spl.size == 1) {
                writer.write(spl[0])
                writer.newLine()
                continue
            }
            for (j in spl.indices)
                sum += spl[j].length
            var k = maxx - sum
            var j = 0
            while (k > 0) {
                if (k == 0) break
                spl[j] += " "
                j = (j + 1) % (spl.size - 1)
                k--
            }
            for (j in spl.indices) {
                writer.write(spl[j])
            }
            writer.newLine()
            sum = 0
        }
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
const val multiAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ"
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    val write = File(outputName).bufferedWriter()
    write.use {
        val dict = mutableMapOf<Char, String>()
        for ((key, value) in dictionary) {
            dict[key.toUpperCase()] = value
        }
        if (dict[' '] == null)
            dict[' '] = " "
        for (line in File(inputName).readLines()) {
            val l = line
            if (l != "") {
                var st = ""
                for (i in 0..line.length - 1) {
                    val p = dict[line[i].toUpperCase()]
                    if (p != null)
                        if (line[i] in multiAlphabet)
                            st += (p.toLowerCase()).capitalize()
                        else
                            st += p.toLowerCase()
                    else
                        st += line[i]
                }
                write.write(st)
            }
            write.newLine()
        }
    }
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val ifile = File(inputName).readLines().toMutableList()
    writer.write("<html><body><p>")
    val italics = listOf<String>("<i>", "</i>")
    val bold = listOf<String>("<b>", "</b>")
    val strikethrough = listOf<String>("<s>", "</s>")
    var i = 0
    var b = 0
    var s = 0
    var del = false
    var yr = 0
    if (ifile.size == 0) writer.write("</p></body></html>")
    else {
        while (yr <= ifile.size - 2) {
            if (ifile[0].isEmpty()) ifile.removeAt(0)
            if (ifile[yr].isBlank() && ifile[yr + 1].isBlank())
                ifile.removeAt(yr + 1)
            else yr++
        }
        if (ifile[ifile.size - 1].isEmpty())
            ifile.removeAt(ifile.size - 1)
        for (l in ifile) {
//        if (l.isEmpty() && yr) {
            if ((l.isEmpty() || l.isBlank())) {
//            if (l.isEmpty())
                writer.write("</p><p>")
//            else
//                writer.write("</p><p></p><p>")
//            yr = false
            } else {
                val fiil = l.replace(Regex("[\\s\\n\\t]+"), " ").split("").toMutableList()
                if (l == "") continue
                for (j in 0 until fiil.size - 1) {
                    when {
                        del -> del = false
                        fiil[j] == "~" && fiil[j + 1] == "~" -> {
                            del = true
                            writer.write(strikethrough[s])
                            s = (s + 1) % 2
                        }

                        fiil[j] == "*" && fiil[j + 1] == "*" -> {
                            del = true
                            writer.write(bold[b])
                            b = (b + 1) % 2
                        }

                        fiil[j] == "*" -> {
                            writer.write(italics[i])
                            i = (i + 1) % 2
                        }

                        else -> writer.write(fiil[j])
                    }
                }
                if (fiil[fiil.size - 1] == "*" && !del) {
                    writer.write(italics[i])
                    i = (i + 1) % 2
                } else writer.write(fiil[fiil.size - 1])
            }
        }
        writer.write("</p></body></html>")
    }
    writer.close()
}


/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
//    var spaces = 0
//    val res = File(outputName).bufferedWriter()
//    val li = listOf<String>("<li>", "/li")
//    val ol = listOf<String>("<ol>", "/ol")
//    val ul = listOf<String>("<ul>", "/ul")
//    var cli = 0
//    var col = 0
//    var cul = 0
//    res.write("<html><body><p><ul>")
//    for (line in File(inputName).readLines()) {
//        when {
//            line.first() == '*' -> {
//                res.write(ol[col])
//                res.write(line)
//                col = (col + 1) % 2
//            }
//
//            line.first().isDigit() -> {
//                res.write(ul[cul])
//                res.write(line)
//                cul = (cul + 1) % 2
//            }
//        }
//    }
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val resList = mutableListOf<String>()
    var newLhv = lhv
    var dill = 0
    val ten = 10.0
    var num = ""
    var pl = 0
    var conInt = 0
    var nuk = true
    var cons = newLhv.toString()
    if (lhv / rhv == 0) {
        if (digitNumber(lhv) <= 1) dill = 1
        resList.add(tos(dill) + "$lhv | $rhv")
        val prop = digitNumber(lhv) + dill + 1
        resList.add("-0" + tos(prop) + "0")
        resList.add("--")
        resList.add(tos(dill) + "$lhv")
        pl++
    } else {
        var k = digitNumber(newLhv) - digitNumber(rhv)
        num = (newLhv / ten.pow(k).toInt()).toString()
        if (num.toInt() < rhv) {
            k -= 1
            num = (newLhv / ten.pow(k).toInt()).toString()
        }
        dill = lhv / rhv
        var min = num.toInt() - (num.toInt() % rhv)
        val prop = digitNumber(lhv) - digitNumber(min) + 3
        val poi = digitNumber(min) - digitNumber(num.toInt()) + 1
        resList.add(tos(poi) + "$lhv | $rhv")
        resList.add("-$min" + tos(prop + poi - 1) + "$dill")
        resList.add(toi(digitNumber(min) + 1))
        num = (num.toInt() % rhv).toString()
        dill = digitNumber(min) - num.length + 1
        if (digitNumber(min) < digitNumber(newLhv)) {
            if (num == "0" && cons[digitNumber(min)] == '0') {
                resList.add(tos(dill) + "00")
                resList.add(tos(dill) + "-0")
                resList.add(tos(dill) + toi(2))
                dill++
                conInt = 1
            }
        }
        newLhv -= min * ten.pow(k).toInt()
        while (newLhv >= rhv) {
            nuk = true
            conInt = 0
            cons = newLhv.toString()
            if (num[0] == '0') pl++
            k = digitNumber(newLhv) - 2 + pl
            num += ((newLhv / ten.pow(k).toInt()) % 10).toString()
            if (digitNumber(num.toInt()) == digitNumber(cons.toInt()) && num[0] != '0')
                num = cons
            min = num.toInt() - (num.toInt() % rhv)
            if (num.length > 1)
                if (num[0] == '0' && num[1] == '0')
                    num = num.drop(1)
            while (min < rhv) {
                resList.add(tos(dill) + num)
                resList.add(tos(dill) + "-0")
                k -= 1
                resList.add(tos(dill) + toi(num.length))
                num += ((newLhv / ten.pow(k).toInt()) % 10).toString()
                min = num.toInt() - (num.toInt() % rhv)
            }
            resList.add(tos(dill) + num)
            resList.add(tos(dill - 1 + pl) + "-$min")
            resList.add(tos(dill - 1 + pl) + toi(num.length + 1 - pl))
            newLhv -= min * ten.pow(k).toInt()
            num = (num.toInt() % rhv).toString()
            dill += digitNumber(min) - num.length + pl
            if (digitNumber(min) < digitNumber(newLhv)) {
                if (num == "0" && cons[digitNumber(min)] == '0') {
                    resList.add(tos(dill) + "00")
                    resList.add(tos(dill) + "-0")
                    resList.add(tos(dill) + toi(2))
                    dill++
                    conInt = 1
                }
            }
            if (num[0] == '0')
                num = "0$num"
            pl = 0
            if (min % 10 == 0)
                nuk = !nuk
        }
    }
    if (num.length > 1)
        if (num[0] == '0' && num[1] == '0')
            num = num.drop(1)
    if (lhv % 10 == 0 && conInt != 1 && nuk) {
        num += '0'
        resList.add(tos(dill) + num)
        resList.add(tos(dill) + "-0")
        resList.add(tos(dill) + toi(num.length))
        dill++
    }
    val opl = lhv % rhv
    if (num != "")
        if (num.toInt() in 1 until opl && newLhv != 0) {
            resList.add(tos(dill) + "$opl")
            resList.add(tos(dill) + "-0")
            resList.add(tos(dill) + toi(digitNumber(opl)))
            resList.add(tos(dill) + "$opl")
            pl = 1
        }
    if (pl == 0)
        resList.add(tos(dill) + "$opl")
    for (i in resList) {
        writer.write(i)
        writer.newLine()
    }
    writer.close()
}

fun toi(a: Int): String {
    var s = ""
    for (i in 0 until a) {
        s += '-'
    }
    return s
}

fun tos(a: Int): String {
    var s = ""
    for (i in 0 until a) {
        s += ' '
    }
    return s
}

