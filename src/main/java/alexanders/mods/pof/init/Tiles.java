package alexanders.mods.pof.init;

import alexanders.mods.pof.tile.BerryBushTile;
import alexanders.mods.pof.tile.VariantTile;

public class Tiles {
    public static VariantTile dryFarmland;
    public static VariantTile grass;
    public static VariantTile longGrass;
    public static VariantTile blueBerryBush;
    public static VariantTile redBerryBush;
    public static VariantTile uglyPlant;
    public static VariantTile largeUglyPlant;

    public static void init() {
        Asset.dry_farmland.tile = dryFarmland = new VariantTile(Resources.dryFarmland);
        Asset.grass.tile = grass = new VariantTile(Resources.grass);
        Asset.long_grass.tile = longGrass = new VariantTile(Resources.longGrass);
        Asset.blue_berry_bush.tile = blueBerryBush = new BerryBushTile(Resources.blueBerryBush);
        Asset.red_berry_bush.tile = redBerryBush = new BerryBushTile(Resources.redBerryBush);
        Asset.ugly_plant.tile = uglyPlant = new VariantTile(Resources.uglyPlant);
        Asset.large_ugly_plant.tile = largeUglyPlant = new VariantTile(Resources.largeUglyPlant);

        dryFarmland.register();
        grass.register();
        longGrass.register();
        blueBerryBush.register();
        redBerryBush.register();
        uglyPlant.register();
        largeUglyPlant.register();
    }
}
