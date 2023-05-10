package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;

public class WalperSpam extends Module {
    public WalperSpam() {
        super(Walper.CATEGORY, "Walper spam", "cope");
    }
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("delay")
        .defaultValue(25)
            .sliderRange(1,350)
        .build()
    );

    private static int tick = 0;
    private static int count = 0;

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        tick++;
        if (count > 8) { //walpers fav interger
            tick = 0;
            count = 0;
            toggle();
        } else {
            if (tick >= delay.get()) {
                tick = 0;
                ChatUtils.sendPlayerMsg("I Love Walper");
            }
        }
    }
}
