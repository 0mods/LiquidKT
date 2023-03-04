package liquid.kt.lib.forge.vectors.d2

import net.minecraft.world.phys.Vec2

operator fun Vec2.plus(other: Vec2): Vec2 = add(other)

operator fun Vec2.minus(other: Vec2): Vec2 = Vec2(x - other.x, y - other.y)

operator fun Vec2.unaryMinus(): Vec2 = negated()

operator fun Vec2.times(scalar: Float): Vec2 = scale(scalar)

operator fun Vec2.times(other: Vec2): Vec2 = Vec2(x * other.x, y * other.y)

operator fun Vec2.div(scalar: Float): Vec2 = Vec2(x / scalar, y / scalar)

operator fun Vec2.div(other: Vec2): Vec2 = Vec2(x / other.x, y / other.y)

fun Vec2.deepCopy(): Vec2 {
    return Vec2(x, y)
}

operator fun Vec2.component1(): Float = x

operator fun Vec2.component2(): Float = y

operator fun Vec2.iterator(): FloatIterator {
    return object: FloatIterator() {
        var index = 0

        override fun hasNext(): Boolean {
            return index <= 1
        }

        override fun nextFloat(): Float {
            return this@iterator[index++]
        }
    }
}

operator fun Vec2.get(index: Int): Float {
    if (index == 0) return x
    if (index == 1) return y
    throw IndexOutOfBoundsException()
}