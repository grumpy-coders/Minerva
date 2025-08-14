package com.mineai.minerva.client.renderer;

import com.mineai.minerva.client.MinervaMod;
import com.mineai.minerva.client.model.MinervaModel;
import com.mineai.minerva.entity.MinervaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class MinervaRenderer extends EntityRenderer<MinervaEntity> {

    private final PlayerRenderer playerRenderer;

    public static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(
            MinervaMod.MODID,
            "sourcesets/main/assets/textures/entity/minerva/minerva.png");

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(TEXTURE_LOCATION, ""); //TODO: changed from main has shit should be derived from the root

        public MinervaRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.playerRenderer = new PlayerRenderer(context, false); // false = Steve
    }

    @Override
    public ResourceLocation getTextureLocation(HumanoidRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    @Override
    public void render(HumanoidRenderState state, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Set up animation for the player model
        this.model.setupAnim(state);

        // Render with your texture
        VertexConsumer vertexconsumer = buffer.getBuffer(this.model.renderType(getTextureLocation(state)));
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }
}
