// MinervaRenderer.java
package com.mineai.minerva.client.renderer;

import com.mineai.minerva.EntityMinerva;
import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.client.model.MinervaModel;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MinervaRenderer extends HumanoidMobRenderer<EntityMinerva, MinervaModel<EntityMinerva>> {
    
    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(MinervaMod.MODID, "textures/entity/minerva_entity.png");

    public MinervaRenderer(EntityRendererProvider.Context context) {
        //super(context, new MinervaModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    	super(context, MinervaModel<>(context.bakeLayer(MinervaModel.MODEL_Y_OFFSET)), 1.0f);
    }

    public ResourceLocation getTextureLocation(EntityMinerva entity) {
        return TEXTURE;
    }
}
