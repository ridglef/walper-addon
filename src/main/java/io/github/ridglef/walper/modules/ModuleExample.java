package io.github.ridglef.walper.modules;

import io.github.ridglef.walper.Walper;
import meteordevelopment.meteorclient.systems.modules.Module;

public class ModuleExample extends Module {
    public ModuleExample() {
        super(Walper.CATEGORY, "example", "An example module in a custom category.");
    }
}
