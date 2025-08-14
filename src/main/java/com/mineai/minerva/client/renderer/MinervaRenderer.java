package com.mineai.minerva.client.renderer;

import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.client.model.MinervaModel;
import com.mineai.minerva.entity.MinervaEntity;
import com.mineai.minerva.entity.ModEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class MinervaRenderer extends HumanoidMobRenderer<MinervaEntity, HumanoidRenderState, MinervaModel> {

    public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(
            MinervaMod.MODID,
            "sourcesets/main/assets/textures/entity/minerva/minerva.png");

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(TEXTURE_LOCATION, "");

    public MinervaRenderer(EntityRendererProvider.Context context) {
        super(context, new MinervaModel(context.bakeLayer(LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HumanoidRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    @Override
    public HumanoidRenderState createRenderState() {
        // TODO Auto-generated method stub
        // return this.extractRenderState(ModEntities.MINERVA_ENTITY.get()., new
        // HumanoidRenderState(), 1.0f);
        return new HumanoidRenderState();
    }
}
