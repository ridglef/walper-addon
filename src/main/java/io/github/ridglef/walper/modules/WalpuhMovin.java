package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.events.entity.player.ClipAtLedgeEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.misc.input.Input;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.EntityPose;
import net.minecraft.util.math.BlockPos;


public class WalpuhMovin extends Module {
    public WalpuhMovin(){
        super(Walper.CATEGORY, "walpuh-movin", "move");
    }
    private SettingGroup sgGeneral = settings.createGroup("General");
    private SettingGroup sgTunnel = settings.createGroup("Tunnel");
    private SettingGroup sgWater = settings.createGroup("Water");

    private final Setting<Boolean> safewalk = sgGeneral.add(new BoolSetting.Builder()
        .name("Safe Walk")
        .description("no fall off cliff")
        .defaultValue(false)
        .build());
    private final Setting<Boolean> sprint = sgGeneral.add(new BoolSetting.Builder()
        .name("Sprint")
        .defaultValue(false)
        .build());

    private final Setting<Boolean> Jump = sgTunnel.add(new BoolSetting.Builder()
        .name("Jump")
        .defaultValue(false)
        .build());

    private final Setting<Integer> JumpFrequency = sgTunnel.add(new IntSetting.Builder()
        .name("Jump Delay (ticks)")
        .visible(Jump::get)
        .sliderRange(0, 70)
        .build());

    private final Setting<Boolean> AutoSwim = sgWater.add(new BoolSetting.Builder()
        .name("Auto Swim")
        .defaultValue(false)
        .build());


    private static int timer;

    @EventHandler
    private void onTick(TickEvent.Post event) {
        timer++;
        if (sprint.get()){mc.player.setSprinting(true);}
        setPressed(mc.options.forwardKey, true);

        if (mc.player.isTouchingWater()){
            if (AutoSwim.get()){
                mc.player.setPose(EntityPose.SWIMMING);
            }
        }

        if (timer > JumpFrequency.get()){
            timer = 0;
            if (Jump.get()) {
                if (!mc.world.getBlockState(new BlockPos((int) mc.player.getPos().x, (int) (mc.player.getPos().y + 2), (int) mc.player.getPos().z)).isAir()) {
                    mc.player.jump();
                }
            }
        }
    }

    @Override
    public void onDeactivate() {
        unpress();
    }

    private void unpress() {
        setPressed(mc.options.forwardKey, false);
    }

    private void setPressed(KeyBinding key, boolean pressed) {
        key.setPressed(pressed);
        Input.setKeyState(key, pressed);
    }

    @EventHandler
    private void onClipAtLedge(ClipAtLedgeEvent event) {
        if (safewalk.get()) {
            if (!mc.player.isSneaking()) event.setClip(true);
        }
    }
}
