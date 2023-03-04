package liquid.kt.lib.forge

import net.minecraft.core.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

fun <T> ICapabilityProvider.getCapabilityOrThrow(cap: Capability<T>, direction: Direction? = null): T =
    getCapability(cap, direction).resolve().orElseThrow()
