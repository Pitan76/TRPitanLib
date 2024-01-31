package net.pitan76.trpitanlib.energy.api;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

@SuppressWarnings("UnstableApiUsage")
public interface CompatibleEnergyStorage extends EnergyStorage {

    @Override
    @Deprecated
    default void setStored(double amount) {
        setEnergy((long) amount);
    }

    @Override
    @Deprecated
    default double getMaxInput(EnergySide side) {
        EnergyRegister.setDirection(this, Direction.valueOf(side.name()));
        return (double) getMaxInput();
    }

    @Override
    @Deprecated
    default double getMaxOutput(EnergySide side) {
        EnergyRegister.setDirection(this, Direction.valueOf(side.name()));
        return (double) getMaxOutput();
    }

    @Override
    @Deprecated
    default double getMaxStoredPower() {
        return (double) getMaxEnergy();
    }

    @Override
    @Deprecated
    default double getStored(EnergySide face) {
        EnergyRegister.setDirection(this, Direction.valueOf(face.name()));
        return (double) getEnergy();
    }

    @Override
    @Deprecated
    // Not used in 1.17 ~ , instead use getMaxInput & getMaxOutput
    default EnergyTier getTier() {
        return EnergyTier.MEDIUM;
    }

    // when 1.17 ~ return null, when 1.16.x return Direction
    @Nullable
    default Direction getDirection() {
        return EnergyRegister.getDirection(this);
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
