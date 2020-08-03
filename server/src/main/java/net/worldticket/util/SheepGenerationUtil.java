package net.worldticket.util;

import net.worldticket.data.Sheep;
import net.worldticket.data.State;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Viktoriia Kopylova
 * @version 28.11.2019
 */
public class SheepGenerationUtil {

    public static List<Sheep> getSheeps(int amount, State state) {
        return IntStream.range(0, amount)
                .mapToObj(s -> new Sheep(state)).collect(Collectors.toList());
    }
}
