package cn.omsfuk.mod.mineutils.config;

import cn.omsfuk.mod.mineutils.MineUtils;
import net.minecraftforge.common.config.Config;

@Config(modid = MineUtils.MODID)
public class ModConfig {

    @Config.Comment("注册为创造流体方块的流体。颜色部分可以留空（即'water'这种格式)。默认颜色为流体本身颜色，注意有些流体没有默认颜色")
    @Config.RequiresMcRestart
    @Config.Name("fluidRegistry")
    public static String[] fluidRegistry = new String[] {"water:00008B", "lava:DC143C"};

}
