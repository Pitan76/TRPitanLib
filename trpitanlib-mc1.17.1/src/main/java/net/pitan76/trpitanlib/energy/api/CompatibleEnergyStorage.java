package net.pitan76.trpitanlib.energy.api;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

@SuppressWarnings("UnstableApiUsage")
public interface CompatibleEnergyStorage extends EnergyStorage {
    @Override
    @Deprecated
    default boolean supportsExtraction() {
        return canExtract();
    }

    @Override
    @Deprecated
    default boolean supportsInsertion() {
        return canInsert();
    }

    @Override
    @Deprecated
    default long insert(long maxAmount, TransactionContext transaction) {
        return insertEnergy(maxAmount, transaction);
    }

    @Override
    @Deprecated
    default long extract(long maxAmount, TransactionContext transaction) {
        return extractEnergy(maxAmount, transaction);
    }

    @Override
    @Deprecated
    default long getAmount() {
        return getEnergy();
    }

    @Override
    @Deprecated
    default long getCapacity() {
        return getMaxEnergy();
    }

    // when 1.17 ~ return null, when 1.16.x return Direction
    @Nullable
    default Direction getDirection() {
        return null;
    }

    default boolean canExtract() {
        return true;
    }

    default boolean canInsert() {
        return true;
    }

    long getEnergy();

    long getMaxEnergy();

    long insertEnergy(long maxAmount, TransactionContext transaction);

    long extractEnergy(long maxAmount, TransactionContext transaction);

    void setEnergy(long amount);

    default long getMaxInput() {
        return getMaxEnergy();
    }

    default long getMaxOutput() {
        return getMaxEnergy();
    }
}
