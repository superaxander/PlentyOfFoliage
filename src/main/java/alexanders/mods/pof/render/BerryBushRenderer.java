package alexanders.mods.pof.render;

import alexanders.mods.pof.tile.BerryBushTile;
import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.assets.tex.Texture;
import de.ellpeck.rockbottom.api.tile.state.TileState;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.TileLayer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BerryBushRenderer<T extends BerryBushTile> extends VariantTextureRenderer<T> {
    private Texture[] emptyTextures;

    public BerryBushRenderer(Texture[] textures, Texture... emptyTextures) {
        super(textures);
        this.emptyTextures = emptyTextures;
    }

    @Override
    public void render(IGameInstance game, IAssetManager manager, Graphics g, IWorld world, BerryBushTile tile, TileState state, int x, int y, TileLayer layer, float renderX, float renderY, float scale, Color[] light) {
        if (state.get(BerryBushTile.empty)) {
            int variant = tile.getVariant(world, x, y);

            if (variant >= tile.variant.getVariants() / 2) {
                Texture texture = emptyTextures[variant - tile.variant.getVariants() / 2];
                texture.drawWithLight(renderX + scale, renderY, renderX, renderY + scale, 0, 0, texture.getWidth(), texture.getHeight(), light, null);
            } else
                emptyTextures[variant].drawWithLight(renderX, renderY, scale, scale, light);
        } else
            super.render(game, manager, g, world, tile, state, x, y, layer, renderX, renderY, scale, light);
    }

    @Override
    public void renderItem(IGameInstance game, IAssetManager manager, Graphics g, BerryBushTile tile, int meta, float x, float y, float scale, Color filter) {
        emptyTextures[0].draw(x, y, scale, scale, filter);
    }
}
