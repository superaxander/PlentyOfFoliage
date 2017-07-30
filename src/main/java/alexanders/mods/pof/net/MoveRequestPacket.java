package alexanders.mods.pof.net;

import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.entity.Entity;
import de.ellpeck.rockbottom.api.net.packet.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.UUID;

public class MoveRequestPacket implements IPacket {
    private UUID uuid;

    public MoveRequestPacket() {
    }

    public MoveRequestPacket(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void toBuffer(ByteBuf buf) throws IOException {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    @Override
    public void fromBuffer(ByteBuf buf) throws IOException {
        uuid = new UUID(buf.readLong(), buf.readLong());
    }

    @Override
    public void handle(IGameInstance game, ChannelHandlerContext context) {
        game.scheduleAction(() -> {
            if (uuid != null) {
                Entity e = game.getWorld().getEntity(uuid);
                if (e != null) {

                }
            }
            return true;
        });
    }
}
