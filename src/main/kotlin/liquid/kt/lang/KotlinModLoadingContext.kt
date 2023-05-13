package liquid.kt.lang

import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.ModLoadingContext

class KotlinModLoadingContext(private val container: KotlinModContainer) {
    /** Mods should access through [MOD_BUS] */
    fun getKEventBus(): IEventBus {
        return container.eventBus
    }

    companion object {
        /** Mods should access through [MOD_CONTEXT] */
        fun get(): KotlinModLoadingContext {
            return ModLoadingContext.get().extension()
        }
    }
}