package alexanders.mods.pof;

import alexanders.mods.pof.event.DamageNegator;
import alexanders.mods.pof.event.InputListener;
import alexanders.mods.pof.event.MovementAllower;
import alexanders.mods.pof.gen.VariantGen;
import alexanders.mods.pof.init.Asset;
import alexanders.mods.pof.init.Resources;
import alexanders.mods.pof.init.Tiles;
import alexanders.mods.pof.item.JumpBoostItem;
import alexanders.mods.pof.item.ShearsItem;
import alexanders.mods.pof.item.SpeedBoostItem;
import alexanders.mods.pof.net.JumpRequestPacket;
import alexanders.mods.pof.net.MoveRequestPacket;
import de.ellpeck.rockbottom.api.IApiHandler;
import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.construction.BasicRecipe;
import de.ellpeck.rockbottom.api.event.IEventHandler;
import de.ellpeck.rockbottom.api.event.impl.EntityDamageEvent;
import de.ellpeck.rockbottom.api.event.impl.EntityTickEvent;
import de.ellpeck.rockbottom.api.event.impl.ResetMovedPlayerEvent;
import de.ellpeck.rockbottom.api.item.ItemBasic;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.mod.IMod;

import static de.ellpeck.rockbottom.api.GameContent.TILE_ROCK;
import static de.ellpeck.rockbottom.api.GameContent.TILE_WOOD_BOARDS;
import static de.ellpeck.rockbottom.api.RockBottomAPI.*;

public class PoF implements IMod {
    public static PoF instance;

    public PoF() {
        instance = this;
        Resources.init();
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
        Asset.init(assetManager);
    }

    @Override
    public void init(IGameInstance game, IApiHandler apiHandler, IEventHandler eventHandler) {
        ShearsItem shears = new ShearsItem(Resources.shears);
        MANUAL_CONSTRUCTION_RECIPES.add(new BasicRecipe(new ItemInstance(shears), new ItemInstance(TILE_ROCK, 6), new ItemInstance(TILE_WOOD_BOARDS, 3)));
        ItemBasic berryBlue = new SpeedBoostItem(Resources.blueBerryBush.addSuffix(".berry"));
        ItemBasic berryRed = new JumpBoostItem(Resources.redBerryBush.addSuffix(".berry"));
        shears.register();
        berryBlue.register(); //TODO: Add an Items init class
        berryRed.register();
        Tiles.init();
        if (!game.isDedicatedServer())
            eventHandler.registerListener(EntityTickEvent.class, new InputListener());
        eventHandler.registerListener(EntityDamageEvent.class, new DamageNegator());
        eventHandler.registerListener(ResetMovedPlayerEvent.class, new MovementAllower());
        WORLD_GENERATORS.add(VariantGen.class);
        PACKET_REGISTRY.register(PACKET_REGISTRY.getNextFreeId(), MoveRequestPacket.class);
        PACKET_REGISTRY.register(PACKET_REGISTRY.getNextFreeId(), JumpRequestPacket.class);
    }
}
