package liquid.kt.lib.forge

import com.mojang.blaze3d.vertex.PoseStack
import org.joml.Quaternionf

fun PoseStack.use(toRun: () -> Unit) {
    pushPose()
    toRun()
    popPose()
}

operator fun PoseStack.timesAssign(matrix: Quaternionf): Unit = mulPose(matrix)