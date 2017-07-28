package alexanders.mods.pof;

import alexanders.mods.pof.gen.VariantGen;
import alexanders.mods.pof.init.Asset;
import alexanders.mods.pof.init.Resources;
import alexanders.mods.pof.init.Tiles;
import alexanders.mods.pof.item.ShearsItem;
import de.ellpeck.rockbottom.api.IApiHandler;
import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.event.IEventHandler;
import de.ellpeck.rockbottom.api.item.ItemBasic;
import de.ellpeck.rockbottom.api.mod.IMod;

import static de.ellpeck.rockbottom.api.RockBottomAPI.WORLD_GENERATORS;

public class PoF implements IMod {
    public static PoF instance;

    public PoF() {
        instance = this;
    }

    @Override
    public String getDisplayName() {
        return "PlentyOfFoliage";
    }

    @Override
    public String getId() {
        return "pof";
    }

    @Override
    public String getVersion() {
        return "@VERSION@";
    }

    @Override
    public String getResourceLocation() {
        return "/assets/" + getId();
    }

    @Override
    public String getDescription() {
        return RockBottomAPI.getGame().getAssetManager().localize(RockBottomAPI.createRes(this, "desc.mod"));
    }

    @Override
    public void initAssets(IGameInstance game, IAssetManager assetManager, IApiHandler apiHandler) {
        Resources.init();
        Asset.init(assetManager);
    }

    @Override
    public void init(IGameInstance game, IApiHandler apiHandler, IEventHandler eventHandler) {
        ShearsItem shears = new ShearsItem(Resources.shears);
        ItemBasic berryBlue = new ItemBasic(Resources.blueBerryBush.addSuffix(".berry"));
        ItemBasic berryRed = new ItemBasic(Resources.redBerryBush.addSuffix(".berry"));
        shears.register();
        berryBlue.register();
        berryRed.register();
        Tiles.init();
        WORLD_GENERATORS.add(VariantGen.class);
    }
}
