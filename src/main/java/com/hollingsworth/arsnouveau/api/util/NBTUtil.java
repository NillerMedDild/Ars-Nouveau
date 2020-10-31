package com.hollingsworth.arsnouveau.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class NBTUtil {

    public static CompoundNBT storeBlockPos(CompoundNBT tag, String prefix, BlockPos pos){
        tag.putDouble(prefix + "_x", pos.getX());
        tag.putDouble(prefix + "_y", pos.getY());
        tag.putDouble(prefix + "_z", pos.getZ());
        return tag;
    }

    public static BlockPos getBlockPos(CompoundNBT tag, String prefix){
        if(!tag.contains(prefix + "_x"))
            return null;
        return new BlockPos(tag.getDouble(prefix + "_x"), tag.getDouble(prefix + "_y"),tag.getDouble(prefix + "_z"));
    }

    public static boolean hasBlockPos(CompoundNBT tag, String prefix){
        return tag.contains(prefix + "_x");
    }


    public static List<ItemStack> readItems(CompoundNBT tag, String prefix){
        List<ItemStack> stacks = new ArrayList<>();
        if(tag == null)
            return stacks;

        for(String s : tag.keySet()){
            if(s.contains(prefix)){
                stacks.add(ItemStack.read(tag.getCompound(s)));
            }
        }
        return stacks;
    }

    public static void writeItems(CompoundNBT tag, String prefix, List<ItemStack> items){
        for(ItemStack item : items) {
            CompoundNBT itemTag = new CompoundNBT();
            item.write(itemTag);
            tag.put(getItemKey(item, prefix), itemTag);
        }
    }

    public static String getItemKey(ItemStack stack, String prefix){
        return prefix + stack.getItem().getRegistryName().toString();
    }
}
