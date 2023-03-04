package liquid.kt.lib.forge

import net.minecraft.util.profiling.ProfilerFiller

fun ProfilerFiller.use(name: String, toProfile: () -> Unit) {
    push(name)
    toProfile()
    pop()
}

fun ProfilerFiller.use(supplier: () -> String, toProfile: () -> Unit) {
    push(supplier)
    toProfile()
    pop()
}