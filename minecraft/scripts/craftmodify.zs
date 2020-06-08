recipes.remove(<botania:spawnerclaw>);

recipes.remove(<extrautils2:drum:1>);
recipes.remove(<extrautils2:drum:2>);
recipes.remove(<extrautils2:drum:3>);

recipes.addShaped("broken_extrautils2_drum", <extrautils2:drum:1>, [
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
