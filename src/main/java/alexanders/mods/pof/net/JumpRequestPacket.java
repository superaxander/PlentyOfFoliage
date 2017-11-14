package alexanders.mods.pof.net;

import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.data.set.DataSet;
import de.ellpeck.rockbottom.api.entity.Entity;
import de.ellpeck.rockbottom.api.entity.EntityLiving;
import de.ellpeck.rockbottom.api.net.packet.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.UUID;

public class JumpRequestPacket implements IPacket {
    private UUID uuid;

    public JumpRequestPacket() {
    }

    public JumpRequestPacket(UUID uuid) {
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
        game.enqueueAction((gameInstance, o) -> {
            if (uuid != null) {
                Entity e = gameInstance.getWorld().getEntity(uuid);
                if (e != null) {
                    DataSet additionalData;
                    if ((additionalData = e.getAdditionalData()) != null) {
                        if (additionalData.getInt("effectJumpActive") > 0) {
                            if (e instanceof EntityLiving) {
                                ((EntityLiving) e).jumping = false;
                                ((EntityLiving) e).jump(.5);
                            }
                        }
                    }
                }
            }
        }, null);
    }
}
