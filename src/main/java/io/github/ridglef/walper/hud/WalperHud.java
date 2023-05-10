package io.github.ridglef.walper.hud;

import meteordevelopment.meteorclient.settings.ColorSetting;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import net.minecraft.util.Identifier;

public class WalperHud extends HudElement {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    public static final HudElementInfo<WalperHud> INFO = new HudElementInfo<>(Hud.GROUP, "Walper", "Render walper", WalperHud::new);

    public WalperHud() {
        super(INFO);
        sreze();
    }


    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
        .name("scale")
        .description("how  big walpuh cock!!??!")
        .defaultValue(300)
        .onChanged((size) -> sreze())
        .sliderRange(40, 600)
        .build()
    );
    private final Setting<SettingColor> coluh = sgGeneral.add(new ColorSetting.Builder()
        .name("color")
        .description("walpuh filter color")
        .defaultValue(new SettingColor(255, 255, 255))
        .build()
    );

    Identifier Text = new Identifier("walper", "icon.png");
    public void sreze() {
        box.setSize(scale.get(), scale.get());
    }
    @Override
    public void render(HudRenderer renderer) {
        renderer.texture(Text, box.x, box.y, getWidth(), getHeight(), coluh.get());
    }
}
