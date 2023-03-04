package liquid.kt.lib.forge.vectors.d4

import org.joml.*

operator fun Vector4d.plusAssign(other: Vector4dc){
    add(other)
}

operator fun Vector4dc.plus(other: Vector4dc): Vector4d = add(other, Vector4d())

operator fun Vector4dc.unaryMinus(): Vector4d = negate(Vector4d())

operator fun Vector4d.minusAssign(other: Vector4dc) {
    x -= other.x()
    y -= other.y()
    z -= other.z()
    w -= other.w()
}

operator fun Vector4dc.minus(other: Vector4dc): Vector4d = sub(other, Vector4d())

operator fun Vector4d.minusAssign(other: Vector4fc) {
    x -= other.x()
    y -= other.y()
    z -= other.z()
    w -= other.w()
}

operator fun Vector4dc.minus(other: Vector4fc): Vector4d = sub(other, Vector4d())

operator fun Vector4d.timesAssign(scalar: Double) {
    mul(scalar)
}

operator fun Vector4dc.times(scalar: Double): Vector4d = mul(scalar, Vector4d())

operator fun Vector4d.timesAssign(other: Vector4dc) {
    mul(other)
}

operator fun Vector4dc.times(other: Vector4dc): Vector4d = mul(other, Vector4d())

operator fun Vector4d.timesAssign(matrix: Matrix4dc) {
    mul(matrix)
}

operator fun Vector4dc.times(matrix: Matrix4dc): Vector4d = mul(matrix, Vector4d())

operator fun Vector4d.timesAssign(quaternion: Quaterniondc) {
    rotate(quaternion)
}

operator fun Vector4dc.times(quaternion: Quaterniondc): Vector4d = rotate(quaternion, Vector4d())

operator fun Vector4d.timesAssign(matrix: Matrix4x3fc) {
    mul(matrix)
}

operator fun Vector4dc.times(matrix: Matrix4x3fc): Vector4d = mul(matrix, Vector4d())

operator fun Vector4d.divAssign(scalar: Double) {
    div(scalar)
}

operator fun Vector4dc.div(scalar: Double): Vector4d = div(scalar, Vector4d())

operator fun Vector4d.divAssign(other: Vector4dc) {
    div(other)
}

operator fun Vector4dc.div(other: Vector4dc): Vector4d = div(other, Vector4d())

fun Vector4dc.deepCopy(): Vector4d = Vector4d(x(), y(), z(), w())

operator fun Vector4dc.component1(): Double = x()

operator fun Vector4dc.component2(): Double = y()

operator fun Vector4dc.component3(): Double = z()

operator fun Vector4dc.component4(): Double = w()

operator fun Vector4dc.iterator(): DoubleIterator {
    return object: DoubleIterator() {
        var index = 0

        override fun hasNext(): Boolean {
            return index <= 3
        }

        override fun nextDouble(): Double {
            if (index > 3) throw IndexOutOfBoundsException()
            return this@iterator[index++]
        }
    }
}

operator fun Vector4d.set(index: Int, scalar: Double) {
    setComponent(index, scalar)
}

fun Vector4dc.toVector4f(): Vector4f = get(Vector4f())