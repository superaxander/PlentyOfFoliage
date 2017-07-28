package alexanders.mods.pof.item;

import alexanders.mods.pof.render.DamageableRenderer;
import alexanders.mods.pof.tile.VariantTile;
import de.ellpeck.rockbottom.api.GameContent;
import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer;
import de.ellpeck.rockbottom.api.inventory.IInventory;
import de.ellpeck.rockbottom.api.item.ItemBasic;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.render.item.IItemRenderer;
import de.ellpeck.rockbottom.api.tile.Tile;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.TileLayer;

public class ShearsItem extends ItemBasic implements IDamageable {

    public ShearsItem(IResourceName name) {
        super(name);
        this.maxAmount = 1;
    }

    @Override
    protected IItemRenderer createRenderer(IResourceName name) {
        return new DamageableRenderer<ShearsItem>(name);
    }

    @Override
    public int getHighestPossibleMeta() {
        return 128;
    }

    @Override
    public boolean onInteractWith(IWorld world, int x, int y, TileLayer layer, double mouseX, double mouseY, AbstractEntityPlayer player, ItemInstance instance) {
        if (RockBottomAPI.getNet().isServer() || !RockBottomAPI.getNet().isActive()) {
            Tile t = world.getState(layer, x, y).getTile();
            if (t instanceof VariantTile || t == GameContent.TILE_LEAVES) {
                t.doBreak(world, x, y, TileLayer.MAIN, player, true, true);
                instance.setMeta(instance.getMeta() + 1);
                if (instance.getMeta() >= 128) {
                    IInventory inv = player.getInv();
                    inv.set(inv.getItemIndex(instance), null);
                }
                return true;
            }
        }
        return super.onInteractWith(world, x, y, layer, mouseX, mouseY, player, instance);
    }
}
