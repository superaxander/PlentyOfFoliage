package alexanders.mods.pof.gen;

import alexanders.mods.pof.init.Asset;
import de.ellpeck.rockbottom.api.world.IChunk;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.TileLayer;
import de.ellpeck.rockbottom.api.world.gen.IWorldGenerator;

import java.util.Random;

public class VariantGen implements IWorldGenerator {
    @Override
    public boolean shouldGenerate(IWorld world, IChunk chunk, Random rand) {
        return chunk.getGridY() == 0;
    }

    @Override
    public void generate(IWorld world, IChunk chunk, Random rand) {
        Asset[] assets = Asset.values();
        int amount = rand.nextInt(64) + 1;
        //System.out.println(amount);
        for (int i = 0; i < amount; i++) {
            for (Asset asset : assets) {
                if (asset.chance != 0) {
                    if (rand.nextInt(asset.chance) == 0) {
                        //System.out.println("Generating");
                        int x = chunk.getX() + rand.nextInt(31);
                        int y = world.getLowestAirUpwards(TileLayer.MAIN, x, 0);
                        //System.out.println(x+":"+y);
                        if (y <= 31 && y >= 0)
                            if (asset.tile.canPlace(world, x, y, TileLayer.MAIN))
                                chunk.setState(TileLayer.MAIN, x, y, asset.tile.getDefState().prop(asset.tile.variant, rand.nextInt(asset.amount * 2))); // I do chunk.setState so that I notice when I bleed into other chunks 
                    }
                }
            }
        }
    }

    @Override
    public int getPriority() {
        return 600;
    }
}
