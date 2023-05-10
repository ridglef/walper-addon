package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.Rotations;
import meteordevelopment.orbit.EventHandler;

public class WalperMode extends Module {
    public WalperMode(){
        super(Walper.CATEGORY, "Walper Mode", "do the walpuh dance");
    }

    @Override
    public void onDeactivate() {
        mc.options.sneakKey.setPressed(false);
    }

    private SettingGroup sgGeneral = settings.getDefaultGroup();

    private Setting<Boolean> twork = sgGeneral.add(new BoolSetting.Builder()
        .name("Twerk")
        .defaultValue(true)
        .build()
    );
    private Setting<Integer> tworkeringspeed = sgGeneral.add(new IntSetting.Builder()
        .name("Twerk Speed (ticks)")
        .visible(twork::get)
        .sliderRange(1,140)
        .defaultValue(10)
        .build()
    );
    private Setting<Boolean> beeebapumbadadodeebadadabado = sgGeneral.add(new BoolSetting.Builder()
        .name("Spin")
        .defaultValue(true)
        .build()
    );
    private Setting<Integer> spinsped = sgGeneral.add(new IntSetting.Builder()
        .name("Spin Speed")
        .visible(beeebapumbadadodeebadadabado::get)
        .sliderRange(1,20)
        .defaultValue(1)
        .build());

    private Setting<Integer> spinfactor = sgGeneral.add(new IntSetting.Builder()
        .name("Spin Speed")
        .visible(beeebapumbadadodeebadadabado::get)
        .sliderRange(5,70)
        .defaultValue(20)
        .build());

    private static int twerkdelay;
    private static int spintimer;
    private static boolean pressed = false;
    private static int progresspin = 0;

    @EventHandler
    public void onTick(TickEvent.Post event) {
        assert mc.player != null;

        if (twork.get()) {
            twerkdelay++;
            if (twerkdelay > tworkeringspeed.get()) {
                twerkdelay = 0;
                if (pressed == false) {
                    mc.options.sneakKey.setPressed(true);
                    pressed = true;
                } else {
                    mc.options.sneakKey.setPressed(false);
                    pressed = false;
                }
            }
        }

        if (beeebapumbadadodeebadadabado.get()) {
            //spin code
            spintimer++;
            if (spintimer > spinsped.get()) {
                spintimer = 0;
                progresspin = progresspin + spinfactor.get();
                if (progresspin > 360){progresspin = 0;}
                Rotations.rotate(progresspin, mc.player.prevPitch);

            }
        }
    }

}
