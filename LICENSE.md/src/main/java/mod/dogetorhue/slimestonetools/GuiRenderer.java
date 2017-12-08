package mod.dogetorhue.slimestonetools;

import java.util.List;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class GuiRenderer extends Gui {
	
	public GuiRenderer(Minecraft mc) {
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		RayTraceResult ray = mc.getRenderViewEntity().rayTrace(20, 1.0F);
		if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState blockState = mc.world.getBlockState(ray.getBlockPos());
			Block hitBlock = blockState.getBlock();
			
			if (hitBlock == Blocks.PISTON || hitBlock == Blocks.STICKY_PISTON) {
				EnumFacing facing = blockState.getValue(BlockPistonBase.FACING);
				boolean extended = blockState.getValue(BlockPistonBase.EXTENDED);
				BlockPistonStructureHelper helper = new BlockPistonStructureHelper(mc.world, ray.getBlockPos(), facing, !extended);
				
				boolean canMove = helper.canMove(); // called before the other one always
				List<BlockPos> toMove = helper.getBlocksToMove();
				
				String text = "Blocks: "+toMove.size();
				String color = "00FF00"; 
				if (!canMove && !extended) {
					color = "FF0000";
					text = "Can't move!";
				}
				if (extended) {
					color = "FFFF00";
					text += " (innacurate)";
				}
				drawCenteredString(mc.fontRenderer, text, width/3, height/2, Integer.parseInt(color, 16));
			}
		}
	}
}
