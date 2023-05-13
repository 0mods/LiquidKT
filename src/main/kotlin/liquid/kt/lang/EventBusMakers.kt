package liquid.kt.lang

import net.minecraftforge.eventbus.api.BusBuilder
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.IEventExceptionHandler
import net.minecraftforge.fml.event.IModBusEvent

internal sealed interface EventBusMaker {
    fun make(exceptionHandler: IEventExceptionHandler): IEventBus
}

internal object OldEventBusMaker : EventBusMaker {
    private val builderMethod = BusBuilder::class.java.getDeclaredMethod("builder")
    private val setExceptionHandlerMethod = BusBuilder::class.java.getDeclaredMethod("setExceptionHandler", IEventExceptionHandler::class.java)
    private val setTrackPhasesMethod = BusBuilder::class.java.getDeclaredMethod("setTrackPhases", Boolean::class.java)
    private val markerTypeMethod = BusBuilder::class.java.getDeclaredMethod("markerType", Class::class.java)
    private val buildMethod = BusBuilder::class.java.getDeclaredMethod("build")

    override fun make(exceptionHandler: IEventExceptionHandler): IEventBus {
        val builder = builderMethod.invoke(null)

        setExceptionHandlerMethod.invoke(builder, exceptionHandler)
        setTrackPhasesMethod.invoke(builder, false)
        markerTypeMethod.invoke(builder, IModBusEvent::class.java)

        return buildMethod.invoke(builder)
    }
}

internal object NewEventBusMaker: EventBusMaker {
    override fun make(exceptionHandler: IEventExceptionHandler): IEventBus {
        return BusBuilder.builder().setExceptionHandler(exceptionHandler).setTrackPhases(false).markerType(IModBusEvent::class.java).useModLauncher().build()
    }
}