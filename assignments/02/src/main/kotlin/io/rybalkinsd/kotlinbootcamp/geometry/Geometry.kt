package io.rybalkinsd.kotlinbootcamp.geometry

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Point): Boolean
   // fun isColliding(other: Bar): Boolean
}

/**
 * 2D point with integer coordinates
 */
data class Point(val x: Int, val y: Int) : Collider {
    override fun isColliding(p: Point): Boolean {
        if (p === this)
            return true
        else
            return ((p.x == x) and (p.y == y))
    }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(val firstCornerX: Int, val firstCornerY: Int, val secondCornerX: Int, val secondCornerY: Int) : Collider {
    override fun isColliding(other: Point): Boolean {
        TODO("not implemented")
    }
}