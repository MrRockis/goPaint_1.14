package gc.arcaniax.gopaint.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import gc.arcaniax.gopaint.objects.other.BlockPlacer;

public class Height {

	public static int getHeight(Player p, Location loc){
		BlockPlacer bp = new BlockPlacer();
		if (loc.getBlock().getType().equals(XMaterial.AIR.parseMaterial())){
			while (loc.getBlock().getType().equals(XMaterial.AIR.parseMaterial())){
				loc.add(0, -1, 0);
				if (loc.getBlockY()<0){return 1;}
			}
			return loc.getBlockY()+1;
		}
		else {
			while (!(loc.getBlock().getType().equals(XMaterial.AIR.parseMaterial()))){
				loc.add(0, 1, 0);
				if (loc.getBlockY()>254){return 254;}
			}
			return loc.getBlockY();
		}
	}
	
	public static double getAverageHeightDiffFracture(Location l, int height, int dis, Player p){
    	double totalHeight = 0;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(dis, 0, -dis)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(dis, 0, dis)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(-dis, 0, dis)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(-dis, 0, -dis)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(0, 0, -dis)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(0, 0, dis)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(-dis, 0, 0)))-height;
    	totalHeight += Math.abs(getHeight(p, l.clone().add(dis, 0, 0)))-height;
    	return (totalHeight/(double)8)/(double)dis;
    }
	
	public static double getAverageHeightDiffAngle(Location l, int dis, Player p){
    	double maxHeightDiff = 0;
    	double maxHeightDiff2 = 0;
    	double diff =  Math.abs(getHeight(p, l.clone().add(dis, 0, -dis))-getHeight(p, l.clone().add(-dis, 0, dis)));
    	if (diff>=maxHeightDiff){
    		maxHeightDiff = diff;
    		maxHeightDiff2 = maxHeightDiff;
    	}
    	diff =  Math.abs(getHeight(p, l.clone().add(dis, 0, dis))-getHeight(p, l.clone().add(-dis, 0, -dis)));
    	if (diff>maxHeightDiff){
    		maxHeightDiff = diff;
			maxHeightDiff2 = maxHeightDiff;
    	}
    	diff =  Math.abs(getHeight(p, l.clone().add(dis, 0, 0))-getHeight(p, l.clone().add(-dis, 0, 0)));
    	if (diff>maxHeightDiff){
    		maxHeightDiff = diff;
			maxHeightDiff2 = maxHeightDiff;
    	}
    	diff =  Math.abs(getHeight(p, l.clone().add(0, 0, -dis))-getHeight(p, l.clone().add(0, 0, dis)));
    	if (diff>maxHeightDiff){
    		maxHeightDiff = diff;
			maxHeightDiff2 = maxHeightDiff;
    	}

    	double height = (maxHeightDiff2+maxHeightDiff)/2.0;
    	return height/(double)(dis*2);
    }

	public static boolean isOnTop(Player p, Location loc, int thickness) {
		int height = getHeight(p, loc.clone());
		if (height - loc.getBlockY()<=thickness){
			return true;
		}
		return false;
	}
}