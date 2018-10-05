package io.rybalkinsd.kotlinbootcamp.geometry

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
    override fun isColliding(obj: Point): Boolean = if (obj === this) true else ((obj.x == x) and (obj.y == y))
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(val firstCornerX: Int, val firstCornerY: Int, val secondCornerX: Int, val secondCornerY: Int) : Collider<Bar> {
    override fun isColliding(obj: Bar): Boolean {
        TODO("not implemented")
    }
}
fun Point.isColliding(obj: Bar): Boolean = TODO()
fun Bar.isColliding(obj: Point): Boolean = TODO()