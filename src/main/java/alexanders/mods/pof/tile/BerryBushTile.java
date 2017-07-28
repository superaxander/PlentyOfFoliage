package alexanders.mods.pof.tile;

import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.tile.state.BoolProp;
import de.ellpeck.rockbottom.api.tile.state.TileState;
import de.ellpeck.rockbottom.api.util.Util;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.TileLayer;

import static de.ellpeck.rockbottom.api.RockBottomAPI.ITEM_REGISTRY;

public class BerryBushTile extends VariantTile {
    public BoolProp empty = new BoolProp("empty", false);
    
    public BerryBushTile(IResourceName name) {
        super(name);
        this.addProps(empty);
    }

    @Override
    public boolean onInteractWith(IWorld world, int x, int y, TileLayer layer, double mouseX, double mouseY, AbstractEntityPlayer player) {
        if(RockBottomAPI.getNet().isServer() || !RockBottomAPI.getNet().isActive()) {
            TileState state = world.getState(layer, x, y);
            if(!state.get(empty))
            {
                player.getInv().addExistingFirst(new ItemInstance(ITEM_REGISTRY.get(name.addSuffix(".berry")), 1), false);
                world.setState(x,y, state.prop(empty, true));
                return true;
            }
        }
        return super.onInteractWith(world, x, y, layer, mouseX, mouseY, player);
    }

    @Override
    public void updateRandomly(IWorld world, int x, int y) {
        if(Util.RANDOM.nextInt(16) == 0) {
            world.setState(x,y, world.getState(x, y).prop(empty, false));
        }
    }
}
