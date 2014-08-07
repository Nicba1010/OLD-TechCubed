package com.nicba1010.techcubed.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.nicba1010.techcubed.init.ModItems;
import com.nicba1010.techcubed.reference.Reference;

public class ChemCreativeTabs
{
    public static final CreativeTabs CHEM_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.mapleLeaf;
        }
    };
}