package io.rybalkinsd.kotlinbootcamp.geometry
import kotlin.math.max
import kotlin.math.min
/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider<T> {
    fun isColliding(obj: T): Boolean
}
/**
 * 2D point with integer coordinates
 */
data class Point(val x: Int, val y: Int) : Collider<Point> {
    override fun isColliding(obj: Point): Boolean = (obj === this) or ((obj.x == x) and (obj.y == y))
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(val firstCornerX: Int, val firstCornerY: Int, val secondCornerX: Int, val secondCornerY: Int) : Collider<Bar> {
    val MaxPoint: Point = Point(max(firstCornerX, secondCornerX), max(firstCornerY, secondCornerY))
    val MinPoint: Point = Point(min(firstCornerX, secondCornerX), min(firstCornerY, secondCornerY))
    override fun isColliding(obj: Bar): Boolean {
        return this.equals(obj) or intersect(obj)
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as Bar
        return (MaxPoint == other.MaxPoint) and (MinPoint == other.MinPoint)
    }
    fun intersect(obj: Bar): Boolean {
        val listObjX = (obj.MinPoint.x..obj.MaxPoint.x).toList()
        val listObjY = (obj.MinPoint.y..obj.MaxPoint.y).toList()
        val listThisX = (MinPoint.x..MaxPoint.x).toList()
        val listThisY = (MinPoint.y..MaxPoint.y).toList()
        return listThisX.fold(false) { acc, el -> (if (el in listObjX) true else false) or acc } and listThisY.fold(false) { acc, el -> (if (el in listObjY) true else false) or acc }
    }
}
fun Point.isColliding(obj: Bar): Boolean = (x in (obj.MinPoint.x..obj.MaxPoint.x).toList()) and (y in (obj.MinPoint.y..obj.MaxPoint.y).toList())
fun Bar.isColliding(obj: Point): Boolean = obj.isColliding(this)
