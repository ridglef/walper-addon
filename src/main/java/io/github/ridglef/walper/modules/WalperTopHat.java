package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class WalperTopHat extends Module {
    public WalperTopHat(){
        super(Walper.CATEGORY, "TopHat", "gives u a tophat");
    }
    private SettingGroup sgGeneral = settings.getDefaultGroup();
    private Setting<Double> scl = sgGeneral.add(new DoubleSetting.Builder()
        .name("Scale")
        .range(0.1,3)
        .defaultValue(1)
        .build());
    private final Setting<ShapeMode> rentype = sgGeneral.add(new EnumSetting.Builder<ShapeMode>()
        .name("mode")
        .defaultValue(ShapeMode.Lines)
        .build()
    );

    private Setting<SettingColor> colores = sgGeneral.add(new ColorSetting.Builder()
        .name("Color")
        .defaultValue(new SettingColor(0, 0, 0, 255))
        .build()
    );
    private Setting<SettingColor> colorestripe = sgGeneral.add(new ColorSetting.Builder()
        .name("Stripe Color")
        .defaultValue(new SettingColor(0, 0, 0, 255))
        .build()
    );
    @EventHandler
    public void onRender(Render3DEvent event) {

        for (PlayerEntity player : mc.world.getPlayers()) {
            Vec3d e = bruh(player);
            Color ez = colores.get();
            Double b = scl.get();
            Color noez = colorestripe.get();
            if (player == mc.player){
                event.renderer.box(e.getX() - 0.2 * b, e.getY(), e.getZ() - 0.2 * b, e.getX() + .2 * b, e.getY() + .8 * b, e.getZ() + .2 * b, new Color(ez.r, ez.g, ez.b, ez.a), ez, rentype.get(), 0);
                event.renderer.box(e.getX() - 0.24  * b, e.getY() + .2 * b, e.getZ() - 0.24 * b, e.getX() + 0.24 * b, e.getY() + .3 * b, e.getZ() +.24 * b, new Color(noez.r, noez.g, noez.b, noez.a), noez, rentype.get(), 0);
                event.renderer.box(e.getX() - 0.4 * b, e.getY(), e.getZ() - 0.4 * b, e.getX() + .4 * b, e.getY() + .2 * b, e.getZ() + .4 * b, new Color(ez.r, ez.g, ez.b, ez.a), ez, rentype.get(), 0);
            }else {}}
    }

    private static Vec3d bruh(PlayerEntity entity){
        return new Vec3d(entity.prevX, entity.prevY + 1.8, entity.prevZ);
    }
}
