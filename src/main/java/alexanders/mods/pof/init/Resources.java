package alexanders.mods.pof.init;

import alexanders.mods.pof.PoF;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;

import static de.ellpeck.rockbottom.api.RockBottomAPI.createRes;

public class Resources {
    public static IResourceName dryFarmland;
    public static IResourceName grass;
    public static IResourceName longGrass;
    public static IResourceName blueBerryBush;
    public static IResourceName redBerryBush;
    public static IResourceName uglyPlant;
    public static IResourceName largeUglyPlant;
    public static IResourceName shears;

    public static void init() {
        dryFarmland = createRes(PoF.instance, "dry_farmland");
        grass = createRes(PoF.instance, "grass");
        longGrass = createRes(PoF.instance, "long_grass");
        blueBerryBush = createRes(PoF.instance, "blue_berry_bush");
        redBerryBush = createRes(PoF.instance, "red_berry_bush");
        uglyPlant = createRes(PoF.instance, "ugly_plant");
        largeUglyPlant = createRes(PoF.instance, "large_ugly_plant");
        shears = createRes(PoF.instance, "shears");
    }
}
