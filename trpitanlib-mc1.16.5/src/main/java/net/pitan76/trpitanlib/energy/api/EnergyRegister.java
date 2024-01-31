package net.pitan76.trpitanlib.energy.api;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.Energy;
import team.reborn.energy.EnergyStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class EnergyRegister {
    private static final Map<CompatibleEnergyStorage, Direction> energyStorageDirectionMap = new HashMap<>();

    protected static void setDirection(CompatibleEnergyStorage energyStorage, Direction direction) {
        if (energyStorageDirectionMap.containsKey(energyStorage))
            energyStorageDirectionMap.replace(energyStorage, direction);
        else
            energyStorageDirectionMap.put(energyStorage, direction);
    }

    protected static void removeDirection(CompatibleEnergyStorage energyStorage) {
        energyStorageDirectionMap.remove(energyStorage);
    }

    // when 1.17 ~ return null, when 1.16.x return Direction
    @Nullable
    public static Direction getDirection(CompatibleEnergyStorage energyStorage) {
        return energyStorageDirectionMap.get(energyStorage);
    }

    public static <T extends BlockEntity> void registerForBlockEntity2(BiFunction<? super T, Direction, @Nullable EnergyStorage> provider, BlockEntityType<T> blockEntityType) {
        Energy.registerHolder(object -> object.getClass() == blockEntityType.instantiate().getClass(), (object) -> provider.apply((T) object, Direction.DOWN));
    }

    public static <T extends BlockEntity> void registerForBlockEntity(BiFunction<? super T, Direction, @Nullable CompatibleEnergyStorage> provider, BlockEntityType<T> blockEntityType) {
        registerForBlockEntity2(provider::apply, blockEntityType);
    }
}
