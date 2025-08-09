package com.mineai.minerva.client;

import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.entity.MinervaEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;

public class MinervaRenderer extends MobRenderer<MinervaEntity, MinervaModel<MinervaEntity>> {
    
    public MinervaRenderer(EntityRendererProvider.Context context) {
        //super(context, new MinervaModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    		super(context, new MinervaModel(context.bakeLayer(LAYER_LOCATION)));
    }

    private static final ResourceLocation TEXTURE_LOCATION =
            ResourceLocation.fromNamespaceAndPath(MinervaMod.MODID, "textures/entity/minerva_entity.png");
    
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(TEXTURE_LOCATION, "main");

    @Override
    public ResourceLocation getTextureLocation(MinervaEntity entity) {
        return TEXTURE_LOCATION;
    }

	@Override
	public MinervaEntity createRenderState() {
		// TODO Auto-generated method stub
		return null;
	}
}
