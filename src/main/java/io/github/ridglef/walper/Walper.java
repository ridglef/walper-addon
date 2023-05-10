package io.github.ridglef.walper;

import io.github.ridglef.walper.hud.WalperHud;
import io.github.ridglef.walper.modules.*;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Walper extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Walper");

    @Override
    public void onInitialize() {
        LOG.info("Walper Addon Started!");
        Modules m = Modules.get();
        m.add(new WalperSpam());
        m.add(new WalperHydration());
        m.add(new WalperMode());
        m.add(new  WalperTopHat());
        m.add(new WalpuhMovin());
        m.add(new WalpuhThighHighlighter());
        Hud.get().register(WalperHud.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "io.github.ridglef.walper";
    }
}
