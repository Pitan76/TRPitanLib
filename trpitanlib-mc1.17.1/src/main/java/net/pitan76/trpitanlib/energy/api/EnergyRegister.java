package net.pitan76.trpitanlib.energy.api;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

import java.util.function.BiFunction;

public class EnergyRegister {

    // when 1.17 ~ return null, when 1.16.x return Direction
    @Nullable
    public static Direction getDirection(CompatibleEnergyStorage energyStorage) {
        return null;
    }

    public static <T extends BlockEntity> void registerForBlockEntity2(BiFunction<? super T, Direction, @Nullable EnergyStorage> provider, BlockEntityType<T> blockEntityType) {
        EnergyStorage.SIDED.registerForBlockEntity(provider, blockEntityType);
    }

    public static <T extends BlockEntity> void registerForBlockEntity(BiFunction<? super T, Direction, @Nullable CompatibleEnergyStorage> provider, BlockEntityType<T> blockEntityType) {
        registerForBlockEntity2(provider::apply, blockEntityType);
    }
}
