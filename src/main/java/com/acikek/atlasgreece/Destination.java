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
        DESTINATIONS.put("zakynthos", of(-3351, 63, -618, COAST));
        DESTINATIONS.put("kefalonia", of(-3739, 63, -1546, COAST));
        DESTINATIONS.put("lefkada", of(-3641, 63, -2731, COAST));
        DESTINATIONS.put("preveza", of(-3574, 63, -2995, COAST));
        DESTINATIONS.put("agrinio", of(-2538, 65, -2299, INLAND));
        DESTINATIONS.put("patras", of(-2005, 63, -1523, COAST));
        DESTINATIONS.put("pyrgos", of(-2419, 68, -482, COAST));
        DESTINATIONS.put("kalmata", of(-1404, 63, 861, COAST));
        DESTINATIONS.put("gerolimenas", of(-964, 63, 1935, COAST));
        DESTINATIONS.put("sparta", of(-933, 73, 767, INLAND));
        DESTINATIONS.put("taygetus", of(-1042, 169, 1000, MOUNTAIN));
        DESTINATIONS.put("kythera", of(-45, 69, 2563, COAST));
        DESTINATIONS.put("antikythera", of(434, 63, 3101, COAST));
        DESTINATIONS.put("leonidio", of(-245, 64, 592, COAST));
        DESTINATIONS.put("tripoli", of(-990, 94, -92, INLAND));
        DESTINATIONS.put("kyllini", of(-975, 169, -939, MOUNTAIN));
        DESTINATIONS.put("argos", of(-447, 64, -389, COAST));
        DESTINATIONS.put("hydra", of(711, 63, 229, COAST));
        DESTINATIONS.put("corinth", of(-134, 63, -943, COAST));
        DESTINATIONS.put("megara", of(516, 65, -1067, COAST));
        DESTINATIONS.put("athens", of(1151, 71, -1181, INLAND));
        DESTINATIONS.put("chalcis", of(974, 63, -2000, COAST));
        DESTINATIONS.put("lamia", of(-1089, 66, -2924, INLAND));
        DESTINATIONS.put("parnassus", of(-634, 169, -2122, MOUNTAIN));
        DESTINATIONS.put("livadia", of(-1089, 66, -2924, INLAND));
        DESTINATIONS.put("skyros", of(2434, 63, -2889, COAST));
        DESTINATIONS.put("psara", of(4004, 67, -2160, COAST));
        DESTINATIONS.put("karystos", of(2190, 63, -1098, COAST));
        DESTINATIONS.put("andros", of(3022, 64, -738, COAST));
        DESTINATIONS.put("tinos", of(3384, 63, -143, COAST));
        DESTINATIONS.put("naxos", of(3721, 63, 713, COAST));
        DESTINATIONS.put("paros", of(3357, 63, 753, COAST));
        DESTINATIONS.put("ios", of(3594, 63, 1479, COAST));
        DESTINATIONS.put("thera", of(3712, 64, 2095, COAST));
        DESTINATIONS.put("folegandros", of(2995, 72, 1650, COAST));
        DESTINATIONS.put("adamantas", of(2258, 63, 1452, COAST));
        DESTINATIONS.put("serifos", of(2350, 68, 602, COAST));
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
