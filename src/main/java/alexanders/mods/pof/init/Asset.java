package alexanders.mods.pof.init;

import alexanders.mods.pof.render.BerryBushRenderer;
import alexanders.mods.pof.render.VariantTextureRenderer;
import alexanders.mods.pof.tile.VariantTile;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.assets.tex.Texture;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;

public enum Asset {
    grass(4, 2),
    long_grass(4, 4),
    blue_berry_bush(2, 16),
    red_berry_bush(2, 16),
    ugly_plant(1, 18),
    large_ugly_plant(1, 20),
    dry_farmland(2, 0);

    public final int amount;
    public final int chance; // 1 in chance
    public VariantTextureRenderer renderer;
    public VariantTile tile;

    Asset(int amount, int chance) {
        this.amount = amount;
        this.chance = chance;
    }

    public static void init(IAssetManager manager) {
        dry_farmland.renderer = new VariantTextureRenderer(getTextures(manager, Resources.dryFarmland, 2));
        grass.renderer = new VariantTextureRenderer(getTextures(manager, Resources.grass, 4));
        long_grass.renderer = new VariantTextureRenderer(getTextures(manager, Resources.longGrass, 4));
        blue_berry_bush.renderer = new BerryBushRenderer(getTextures(manager, Resources.blueBerryBush, 2), getEmptyTextures(manager, Resources.blueBerryBush, 2));
        red_berry_bush.renderer = new BerryBushRenderer(getTextures(manager, Resources.redBerryBush, 2), getEmptyTextures(manager, Resources.redBerryBush, 2));
        ugly_plant.renderer = new VariantTextureRenderer(getTextures(manager, Resources.uglyPlant, 1));
        large_ugly_plant.renderer = new VariantTextureRenderer(getTextures(manager, Resources.largeUglyPlant, 1));
    }

    private static Texture[] getEmptyTextures(IAssetManager manager, IResourceName name, int amount) {
        name = name.addSuffix("_empty");
        Texture[] textures = new Texture[amount];
        for (int i = 1; i <= amount; i++) {
            textures[i - 1] = manager.getTexture(name.addSuffix("_" + i));
        }
        return textures;
    }

    private static Texture[] getTextures(IAssetManager manager, IResourceName name, int amount) {
        Texture[] textures = new Texture[amount];
        for (int i = 1; i <= amount; i++) {
            textures[i - 1] = manager.getTexture(name.addSuffix("_" + i));
        }
        return textures;
    }
}
