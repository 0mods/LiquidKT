package liquid.kt.lib.forge.vectors.d3

import org.joml.Vector3d
import org.joml.Vector3f
import net.minecraft.core.Vec3i
import net.minecraft.world.phys.Vec3

operator fun Vec3i.plus(other: Vec3i): Vec3i = offset(other)

operator fun Vec3i.unaryMinus(): Vec3i = this * -1

operator fun Vec3i.minus(other: Vec3i): Vec3i = subtract(other)

operator fun Vec3i.times(scalar: Int): Vec3i = multiply(scalar)

operator fun Vec3i.times(other: Vec3i): Vec3i = Vec3i(x * other.x, y * other.y, z * other.z)

operator fun Vec3i.div(scalar: Int): Vec3i = Vec3i(x / scalar, y / scalar, z / scalar)

operator fun Vec3i.div(other: Vec3i): Vec3i = Vec3i(x / other.x, y / other.y, z / other.z)

infix fun Vec3i.dot(other: Vec3i): Int {
    val (x, y, z) = this

    return x * other.x + y * other.y + z * other.z
}

fun Vec3i.deepCopy(): Vec3i {
    return Vec3i(x, y, z)
}

operator fun Vec3i.component1(): Int = x

operator fun Vec3i.component2(): Int = y

operator fun Vec3i.component3(): Int = z

operator fun Vec3i.iterator(): IntIterator {
    return object: IntIterator() {
        var index = 0

        override fun hasNext(): Boolean {
            return index <= 2
        }

        override fun nextInt(): Int {
            return this@iterator[index++]
        }
    }
}

operator fun Vec3i.get(index: Int): Int {
    if (index == 0) return x
    if (index == 1) return y
    if (index == 2) return z
    throw IndexOutOfBoundsException()
}

fun Vec3i.toVec3(): Vec3 = Vec3.atLowerCornerOf(this)

fun Vec3i.toVector3f(): Vector3f = Vector3f(x.toFloat(), y.toFloat(), z.toFloat())

fun Vec3i.toVector3d(): Vector3d = Vector3d(x.toDouble(), y.toDouble(), z.toDouble())