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


//EFLN (explosion)
recipes.remove(<tconstruct:throwball:1>);
recipes.addShapeless("broken_tconstructEFLN", <tconstruct:throwball:1>, [<minecraft:flint>,<minecraft:gunpowder>,<ore:blockCopper>]);

//SecurityCraft
recipes.removeByRegex("securitycraft:(.*)mine");
recipes.addShaped("broken_mine", <securitycraft:mine> * 3, [
    [null, <minecraft:diamond>, null],
    [<enderio:item_basic_capacitor:1>,<minecraft:iron_ingot>, <enderio:item_basic_capacitor:1>],
    [<minecraft:iron_ingot>, <minecraft:tnt>,<minecraft:iron_ingot>]
]);
recipes.remove(<securitycraft:universal_block_reinforcer_lvl1>);
recipes.remove(<securitycraft:universal_block_reinforcer_lvl2>);
recipes.remove(<securitycraft:universal_block_reinforcer_lvl3>);
recipes.addShaped("broken_reinforcer", <securitycraft:universal_block_reinforcer_lvl1>, [
    [null, <pneumaticcraft:programming_puzzle:1>, <extrautils2:opinium:6>],
    [<minecraft:emerald_block>,<minecraft:nether_star>, <bewitchment:sanguine_cloth>],
    [<enderio:item_material:71>, <minecraft:emerald_block>,null]
]);
recipes.remove(<securitycraft:codebreaker>);
recipes.addShaped("broken_codebreaker",<securitycraft:codebreaker>, [
    [<minecraft:diamond>, <enderio:item_basic_capacitor:2>, <minecraft:diamond>],
    [<minecraft:gold_ingot>,<minecraft:skull:5>, <minecraft:gold_ingot>],
    [<quark:rune:*>, <refinedstorage:storage_part:3>,<quark:rune:*>]
]);

// Tablet of cupidity
recipes.remove(<tombstone:tablet_of_cupidity>);
recipes.addShaped("broken_tablet_of_cupidity",<tombstone:tablet_of_cupidity>, [
    [<enderio:block_enderman_skull:2>, <minecraft:gold_block>, <enderio:block_enderman_skull:2>],
    [<botania:lightrelay:0>,<minecraft:diamond_block>, <botania:lightrelay:0>],
    [<enderio:block_enderman_skull:2>, <minecraft:gold_block>,<enderio:block_enderman_skull:2>]
]);

var reinforced_metals = <securitycraft:reinforced_metals>.definition;
for i in 0 to 5 {
    recipes.replaceAllOccurences(reinforced_metals.makeStack(i), <minecraft:redstone_block>);
}
recipes.replaceAllOccurences(<securitycraft:reinforced_glass_block>, <minecraft:glass>);
recipes.replaceAllOccurences(<securitycraft:reinforced_stone>, <minecraft:obsidian>);

recipes.addShaped("broken_retinal_scanner",<securitycraft:retinal_scanner>, [
    [<minecraft:obsidian>, <minecraft:obsidian>, <minecraft:obsidian>],
    [<minecraft:obsidian>,<botania:endereyeblock>, <minecraft:obsidian>],
    [<minecraft:obsidian>, <minecraft:obsidian>,<minecraft:obsidian>]
]);

