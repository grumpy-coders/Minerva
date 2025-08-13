package com.mineai.minerva.client.renderer;

import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.client.model.MinervaModel;
import com.mineai.minerva.entity.MinervaEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MinervaRenderer extends MobRenderer<MinervaEntity, HumanoidRenderState, MinervaModel> {

    public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(
            MinervaMod.MODID,
            "assets/textures/entity/minerva/minerva.png");

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(TEXTURE_LOCATION, "main");

    public MinervaRenderer(EntityRendererProvider.Context context) {
        super(context, new MinervaModel(context.bakeLayer(LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(HumanoidRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    @Override
    public HumanoidRenderState createRenderState() {
        return new HumanoidRenderState(); // return proper render state object
    }
}
