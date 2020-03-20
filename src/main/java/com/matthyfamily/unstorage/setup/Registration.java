package com.matthyfamily.unstorage.setup;

import com.matthyfamily.unstorage.UnStorage;
import com.matthyfamily.unstorage.blocks.BasicBlock;
import com.matthyfamily.unstorage.blocks.BasicBlockTile;
import com.matthyfamily.unstorage.blocks.StandardStorageServer;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.matthyfamily.unstorage.UnStorage.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<BasicBlock> SSSBLOCK = BLOCKS.register("standard_storage_server", BasicBlock::new);
    public static final RegistryObject<Item> FIRSTBLOCK_ITEM = ITEMS.register("firstblock", () -> new BlockItem(SSSBLOCK.get(), new Item.Properties()));
    public static final RegistryObject<TileEntityType<BasicBlockTile>> StandardStorageServerTile  = TILES.register("standard_storage_server", () -> TileEntityType.Builder.create(BasicBlockTile::new, SSSBLOCK.get()).build(null));

    public static final RegistryObject<ContainerType<StandardStorageServer>> StandardStorageServerContainer = CONTAINERS.register("standard_storage_server", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new StandardStorageServer(windowId, UnStorage.proxy.getClientWorld(), pos, inv, UnStorage.proxy.getClientPlayer());
    }));

}
