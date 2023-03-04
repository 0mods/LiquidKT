package liquid.kt.lib.forge.vectors.d3

import net.minecraft.core.Vec3i
import net.minecraft.world.phys.Vec3
import org.joml.Vector3d

operator fun Vec3.plus(other: Vec3): Vec3 = add(other)

operator fun Vec3.unaryMinus(): Vec3 = this * -1.0

operator fun Vec3.minus(other: Vec3): Vec3 = subtract(other)

operator fun Vec3.times(scalar: Double): Vec3 = scale(scalar)

operator fun Vec3.times(other: Vec3): Vec3 = multiply(other)

operator fun Vec3.div(scalar: Double): Vec3 = Vec3(x / scalar, y / scalar, z / scalar)

operator fun Vec3.div(other: Vec3): Vec3 = Vec3(x / other.x, y / other.y, z / other.z)

fun Vec3.deepCopy(): Vec3 = Vec3(x, y, z)

operator fun Vec3.component1(): Double = x

operator fun Vec3.component2(): Double = y

operator fun Vec3.component3(): Double = z

operator fun Vec3.iterator(): DoubleIterator {
    return object: DoubleIterator() {
        var index = 0

        override fun hasNext(): Boolean {
            return index <= 2
        }

        override fun nextDouble(): Double {
            return this@iterator[index++]
        }
    }
}

operator fun Vec3.get(index: Int): Double {
    if (index == 0) return x
    if (index == 1) return y
    if (index == 2) return z
    throw IndexOutOfBoundsException()
}

fun Vec3.toVec3i(): Vec3i = Vec3i(x, y, z)

fun Vec3.toVector3d(): Vector3d = Vector3d(x, y, z)