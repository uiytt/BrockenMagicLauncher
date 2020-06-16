//Spawner
recipes.remove(<botania:spawnerclaw>);
recipes.remove(<pneumaticcraft:spawner_agitator>);
recipes.remove(<actuallyadditions:item_spawner_changer>);
//DRUM
recipes.remove(<extrautils2:drum:0>);
recipes.remove(<extrautils2:drum:1>);
recipes.remove(<extrautils2:drum:2>);
recipes.remove(<extrautils2:drum:3>);

recipes.addShaped("broken_extrautils2_drum0", <extrautils2:drum:0>, [
    [<ore:stone>, <minecraft:stone_slab>, <ore:stone>],
    [<ore:cobblestone>,<minecraft:bowl>, <ore:cobblestone>],
    [<minecraft:obsidian>, <minecraft:stone_slab>,<minecraft:obsidian>]
]);
recipes.addShaped("broken_extrautils2_drum1", <extrautils2:drum:1>, [
    [<minecraft:iron_ingot>, <refinedstorage:fluid_storage_disk:2>, <minecraft:iron_ingot>],
    [<minecraft:iron_ingot>,<minecraft:cauldron>, <minecraft:iron_ingot>],
    [<minecraft:iron_ingot>, <minecraft:heavy_weighted_pressure_plate>,<minecraft:iron_ingot>]
]);

//Flying Ring
recipes.remove(<extrautils2:chickenring:1>);

recipes.addShaped("broken_extrautils2_chickenring1", <extrautils2:chickenring:1>, [
    [<ore:dyeBlack>, <bloodmagic:sigil_void>, <ore:dyeBlack>],
    [<extrautils2:goldenlasso:0>,<minecraft:cauldron>, <minecraft:ender_pearl>],
    [<ore:dyeBlack>, <naturesaura:aura_trove>,<ore:dyeBlack>]
]);

//Solar panel
recipes.remove(<galacticraftcore:solar:4>);

recipes.addShaped("broken_solar4", <galacticraftcore:solar:4>, [
    [<galacticraftcore:basic_item:9>, <galacticraftcore:basic_item:1>, <galacticraftcore:basic_item:9>],
    [<galacticraftcore:basic_item:9>,<enderio:block_solar_panel:3>, <galacticraftcore:basic_item:9>],
    [<ore:ingotDarkSteel>, <galacticraftcore:basic_item:14>,<ore:ingotDarkSteel>]
]);



