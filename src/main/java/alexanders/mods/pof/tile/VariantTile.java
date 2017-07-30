package alexanders.mods.pof.tile;

import alexanders.mods.pof.init.Asset;
import de.ellpeck.rockbottom.api.GameContent;
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.render.tile.ITileRenderer;
import de.ellpeck.rockbottom.api.tile.Tile;
import de.ellpeck.rockbottom.api.tile.state.IntProp;
import de.ellpeck.rockbottom.api.tile.state.TileState;
import de.ellpeck.rockbottom.api.util.BoundBox;
import de.ellpeck.rockbottom.api.util.Util;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.TileLayer;

public class VariantTile extends Tile {
    public IntProp variant;

    public VariantTile(IResourceName name) {
        super(name);
        this.variant = new IntProp("variant", 0, Asset.valueOf(name.getResourceName()).amount * 2);
        this.addProps(this.variant);
    }

    @Override
    public ITileRenderer getRenderer() {
        return Asset.valueOf(name.getResourceName()).renderer;
    }

    @Override
    public TileState getPlacementState(IWorld world, int x, int y, TileLayer layer, ItemInstance instance, AbstractEntityPlayer placer) {
        return getDefState().prop(variant, Util.RANDOM.nextInt(Asset.valueOf(name.getResourceName()).amount * 2));
    }

    public int getVariant(IWorld world, int x, int y) {
        return world.getState(x, y).get(variant);
    }

    @Override
    public boolean isFullTile() {
        return false;
    }

    @Override
    public boolean canPlace(IWorld world, int x, int y, TileLayer layer) {
        Tile below = world.getState(TileLayer.MAIN, x, y - 1).getTile();
        return (below == GameContent.TILE_DIRT || below == GameContent.TILE_GRASS) && super.canPlace(world, x, y, layer);
    }

    @Override
    public boolean canStay(IWorld world, int x, int y, TileLayer layer, int changedX, int changedY, TileLayer changedLayer) {
        if (y - 1 == changedY && x == changedX) {
            Tile below = world.getState(TileLayer.MAIN, x, y - 1).getTile();
            return below == GameContent.TILE_DIRT || below == GameContent.TILE_GRASS;
        }
        return super.canStay(world, x, y, layer, changedX, changedY, changedLayer);
    }

    @Override
    public boolean canPlaceInLayer(TileLayer layer) {
        return layer == TileLayer.MAIN;
    }

    @Override
    public BoundBox getBoundBox(IWorld world, int x, int y) {
        return null;
    }
}
