package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.friends.Friends;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class WalpuhThighHighlighter extends Module {
    public WalpuhThighHighlighter(){
        super(Walper.CATEGORY, "Walper Thigh-High(lighter)s", "thick thighs save lives");
    }
    private SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<ShapeMode> rentype = sgGeneral.add(new EnumSetting.Builder<ShapeMode>()
        .name("mode")
        .defaultValue(ShapeMode.Lines)
        .build()
    );

    private Setting<SettingColor> colores = sgGeneral.add(new ColorSetting.Builder()
        .name("Color")
        .defaultValue(new SettingColor(255, 170, 0, 255))
        .build()
    );
    private final Setting<Double> tallness = sgGeneral.add(new DoubleSetting.Builder()
        .name("Height")
        .defaultValue(0.8)
        .sliderRange(0.5, 1)
        .build()
    );
    private final Setting<Boolean> ignself = sgGeneral.add(new BoolSetting.Builder()
        .name("Ignore Self")
        .defaultValue(false)
        .build()
    );
    private final Setting<Boolean> ignfriends = sgGeneral.add(new BoolSetting.Builder()
        .name("Ignore Friends")
        .defaultValue(false)
        .build()
    );
    @EventHandler
    public void onRender(Render3DEvent event) {
        for (PlayerEntity player : mc.world.getPlayers()) {
            Vec3d e = bruh(player);
            Color ez = colores.get();
            if (ignself.get() && player == mc.player || ignfriends.get() && Friends.get().isFriend(player)){}else {
                event.renderer.box(e.getX() - 0.2, e.getY(), e.getZ() - 0.2, e.getX() + .2, e.getY() + tallness.get(), e.getZ() + .2, new Color(ez.r, ez.g, ez.b, ez.a), ez, rentype.get(), 0);
            }}
    }

    private static Vec3d bruh(PlayerEntity entity){
        return new Vec3d(entity.prevX, entity.prevY, entity.prevZ);
    }
}
