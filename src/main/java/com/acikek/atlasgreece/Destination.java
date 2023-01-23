package com.acikek.atlasgreece;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

import static com.acikek.atlasgreece.Destination.Type.*;

public record Destination(BlockPos pos, Type type) {

    public enum Type {
        COAST,
        INLAND,
        MOUNTAIN
    }

    public static Destination of(int x, int y, int z, Type type) {
        return new Destination(new BlockPos(x, y, z), type);
    }

    public static final Map<String, Destination> DESTINATIONS = new HashMap<>();

    static {
        DESTINATIONS.put("zakynthos", of(-3351, 62, -618, COAST));
        DESTINATIONS.put("kefalonia", of(-3739, 62, -1546, COAST));
        DESTINATIONS.put("lefkada", of(-3641, 62, -2731, COAST));
        DESTINATIONS.put("preveza", of(-3574, 62, -2995, COAST));
        DESTINATIONS.put("agrinio", of(-2538, 64, -2299, INLAND));
        DESTINATIONS.put("patras", of(-2005, 62, -1523, COAST));
        DESTINATIONS.put("pyrgos", of(-2419, 67, -482, COAST));
        DESTINATIONS.put("kalmata", of(-1404, 62, 861, COAST));
        DESTINATIONS.put("gerolimenas", of(-964, 62, 1935, COAST));
        DESTINATIONS.put("sparta", of(-933, 72, 767, INLAND));
        DESTINATIONS.put("taygetus", of(-1042, 168, 1000, MOUNTAIN));
        DESTINATIONS.put("kythera", of(-45, 68, 2563, COAST));
        DESTINATIONS.put("antikythera", of(434, 62, 3101, COAST));
        DESTINATIONS.put("leonidio", of(-245, 63, 592, COAST));
        DESTINATIONS.put("tripoli", of(-990, 93, -92, INLAND));
        DESTINATIONS.put("kyllini", of(-975, 168, -939, MOUNTAIN));
        DESTINATIONS.put("argos", of(-447, 63, -389, COAST));
        DESTINATIONS.put("hydra", of(711, 62, 229, COAST));
        DESTINATIONS.put("corinth", of(-134, 62, -943, COAST));
        DESTINATIONS.put("megara", of(516, 64, -1067, COAST));
        DESTINATIONS.put("athens", of(1151, 70, -1181, INLAND));
        DESTINATIONS.put("chalcis", of(974, 62, -2000, COAST));
        DESTINATIONS.put("lamia", of(-1089, 65, -2924, INLAND));
        DESTINATIONS.put("parnassus", of(-634, 168, -2122, MOUNTAIN));
        DESTINATIONS.put("livadia", of(-1089, 65, -2924, INLAND));
        DESTINATIONS.put("skyros", of(2434, 62, -2889, COAST));
        DESTINATIONS.put("psara", of(4004, 66, -2160, COAST));
        DESTINATIONS.put("karystos", of(2190, 62, -1098, COAST));
        DESTINATIONS.put("andros", of(3022, 63, -738, COAST));
        DESTINATIONS.put("tinos", of(3384, 62, -143, COAST));
        DESTINATIONS.put("naxos", of(3721, 62, 713, COAST));
        DESTINATIONS.put("paros", of(3357, 62, 753, COAST));
        DESTINATIONS.put("ios", of(3594, 62, 1479, COAST));
        DESTINATIONS.put("thera", of(3712, 63, 2095, COAST));
        DESTINATIONS.put("folegandros", of(2995, 71, 1650, COAST));
        DESTINATIONS.put("adamantas", of(2258, 62, 1452, COAST));
        DESTINATIONS.put("serifos", of(2350, 67, 602, COAST));
    }

    public static Map.Entry<String, Destination> getDestination(ServerPlayerEntity player) {
        var destinations = DESTINATIONS.entrySet().stream().toList();
        return destinations.get(player.world.random.nextInt(destinations.size()));
    }

    public MutableText getSpawnText(String name) {
        Text destinationText = Text.translatable("destination.atlasgreece." + name);
        return Text.translatable("spawn.atlasgreece." + type.name().toLowerCase(), destinationText);
    }
}
