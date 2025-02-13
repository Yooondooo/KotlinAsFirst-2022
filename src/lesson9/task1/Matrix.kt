@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.IndexOutOfBoundsException

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    TODO()
//    if (height <= 0 || width <= 0) throw IllegalArgumentException()
//    val res = MatrixImpl<E>(height, width)
//    for (i in 0 until height) {
//        for (j in 0 until width) {
//            res[Cell(height, width)] = e
//        }
//    }
//    return res
}

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
//class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {
class MatrixImpl<E> : Matrix<E> {
    override val height: Int = TODO()

    override val width: Int = TODO()
    private val map = mutableMapOf<Cell, E>()

    override fun get(row: Int, column: Int): E {
        TODO()
//        val cell = Cell(row, column)
//        val m = map[cell]
//        if (m != null)
//            return m
//        throw IllegalArgumentException()
    }

    override fun get(cell: Cell): E {
        TODO()
//        val m = map[cell]
//        if (m != null)
//            return m
//        throw IllegalArgumentException()
    }

    override fun set(row: Int, column: Int, value: E) {
        TODO()
//        map[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: E) {
        TODO()
//        map[cell] = value
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width

//    override fun hashCode(): Int {
//        var result = height
//        result = 31 * result + width
//        result = 31 * result + map.hashCode()
//        return result
//    }

//    override fun toString(): String {
//        var str = ""
//        for (i in 0 until height) {
//            for (j in 0 until width) {
//                str += map.get(Cell(i,j)).toString() + " "
//            }
//            str.trim()
//            str += "\n"
//        }
//        return str
//    }
}

