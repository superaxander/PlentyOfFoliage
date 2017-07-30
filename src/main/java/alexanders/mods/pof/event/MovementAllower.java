package alexanders.mods.pof.event;

import de.ellpeck.rockbottom.api.data.set.DataSet;
import de.ellpeck.rockbottom.api.event.EventResult;
import de.ellpeck.rockbottom.api.event.IEventListener;
import de.ellpeck.rockbottom.api.event.impl.ResetMovedPlayerEvent;

public class MovementAllower implements IEventListener<ResetMovedPlayerEvent> { //Awful name I know
    @Override
    public EventResult listen(EventResult result, ResetMovedPlayerEvent event) {
        DataSet data = event.player.getAdditionalData();
        if (data != null) {
            double xMoved = event.player.x - event.lastCalcX;
            double yMoved = event.player.y - event.lastCalcY;
            double movedXAllowed = Math.abs(event.allowedDefaultDistance);
            double movedYAllowed = Math.abs(event.allowedDefaultDistance);
            int speedLeft;
            if ((speedLeft = data.getInt("effectSpeedActive")) > 0) {
                movedXAllowed += 0.1 * (250 - speedLeft);
            }
            if (data.getInt("effectJumpActive") > 0) {
                movedYAllowed += 1.5;
            }
            if (Math.abs(movedXAllowed) > Math.abs(xMoved) || Math.abs(movedYAllowed) > Math.abs(yMoved))
                return EventResult.CANCELLED;
        }
        return result;
    }
}
