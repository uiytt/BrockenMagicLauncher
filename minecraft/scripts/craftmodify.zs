recipes.remove(<botania:spawnerclaw>);

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

recipes.remove(<extrautils2:chickenring:1>);

recipes.addShaped("broken_extrautils2_chickenring1", <extrautils2:chickenring:1>, [
    [<ore:dyeBlack>, <bloodmagic:sigil_void>, <ore:dyeBlack>],
    [<extrautils2:goldenlasso:0>,<minecraft:cauldron>, <minecraft:ender_pearl>],
    [<ore:dyeBlack>, <naturesaura:aura_trove>,<ore:dyeBlack>]
]);


