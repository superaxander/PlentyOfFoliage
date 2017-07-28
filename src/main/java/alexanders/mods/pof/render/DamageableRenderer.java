package alexanders.mods.pof.render;

import alexanders.mods.pof.item.IDamageable;
import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.item.Item;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.render.item.DefaultItemRenderer;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class DamageableRenderer<T extends Item & IDamageable> extends DefaultItemRenderer<T> {

    public DamageableRenderer(IResourceName name) {
        super(name);
    }

    @Override
    public void render(IGameInstance game, IAssetManager manager, Graphics g, T item, ItemInstance instance, float x, float y, float scale, Color filter) {
        super.render(game, manager, g, item, instance, x, y, scale, filter);
        int meta = instance.getMeta();
        if (meta != 0) {
            float x0 = x + scale * (1 / 12f);
            float y0 = y + scale * (10 / 12f);
            float width = scale * (10 / 12f);
            g.setColor(Color.red);
            g.drawLine(x0, y0, x + width, y0);
            g.setColor(Color.green);
            g.drawLine(x0, y0, x + width * (128 - instance.getMeta()) / item.getHighestPossibleMeta(), y0);
        }
    }
}
