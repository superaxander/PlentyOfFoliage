package alexanders.mods.pof.render;

import alexanders.mods.pof.tile.VariantTile;
import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.assets.tex.Texture;
import de.ellpeck.rockbottom.api.render.tile.ITileRenderer;
import de.ellpeck.rockbottom.api.tile.state.TileState;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.TileLayer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class VariantTextureRenderer<T extends VariantTile> implements ITileRenderer<T> {
    protected Texture[] textures;

    public VariantTextureRenderer(Texture... textures) {
        this.textures = textures;
    }

    @Override
    public void render(IGameInstance game, IAssetManager manager, Graphics g, IWorld world, VariantTile tile, TileState state, int x, int y, TileLayer layer, float renderX, float renderY, float scale, Color[] light) {
        int variant = tile.getVariant(world, x, y);

        if (variant >= tile.variant.getVariants() / 2) {
            Texture texture = textures[variant - tile.variant.getVariants() / 2];
            texture.drawWithLight(renderX + scale, renderY, renderX, renderY + scale, 0, 0, texture.getWidth(), texture.getHeight(), light, null);
        } else
            textures[variant].drawWithLight(renderX, renderY, scale, scale, light);

    }

    @Override
    public void renderItem(IGameInstance game, IAssetManager manager, Graphics g, VariantTile tile, int meta, float x, float y, float scale, Color filter) {
        textures[0].draw(x, y, scale, scale, filter);
    }

    @Override
    public Image getParticleTexture(IGameInstance game, IAssetManager manager, Graphics g, VariantTile tile, TileState state) {
        return textures[0];
    }
}
