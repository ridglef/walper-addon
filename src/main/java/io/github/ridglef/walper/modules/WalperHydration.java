package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.EnumSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.MeteorToast;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.Items;

public class WalperHydration extends Module {
    public WalperHydration(){
        super(Walper.CATEGORY, "Water", "Reminds you to drink water");
    }

    private SettingGroup sgGeneral = settings.getDefaultGroup();

    public enum notiftype{
        Toast,
        Chat,
        Both,
    }

    private final Setting<notiftype> mode = (Setting<notiftype>) sgGeneral.add(new EnumSetting.Builder<notiftype>()
        .name("Mode")
        .defaultValue(notiftype.Both)
        .build()
    );

    private Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("Delay")
        .description("Delay between notifications in ticks")
        .defaultValue(3000)
        .sliderRange(50, 50000)
        .build()
    );

    public static int Timer = 0;
    @EventHandler
    private void onTick(TickEvent.Pre event){
        Timer++;
        if (Timer > delay.get()) {
            Timer = 0;
            switch (mode.get()) {
                case Toast -> mc.getToastManager().add(new MeteorToast(Items.WATER_BUCKET, "Time to drink some water", "Stay Hydrated!"));
                case Chat -> info("It's time to drink some water");
                case Both -> { mc.getToastManager().add(new MeteorToast(Items.WATER_BUCKET, "Time to drink some water", "Stay Hydrated!")); info("It's time to drink some water");}
            }
        }
    }
}
