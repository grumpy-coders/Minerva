// MinervaModel.java
// Made with Blockbench 4.12.6
// Exported for Minecraft 1.17+ with Mojang mappings

package com.mineai.minerva.client.model;

import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.client.renderer.MinervaRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;

public class MinervaModel extends HumanoidModel<HumanoidRenderState> {

        private final ModelPart head;
        private final ModelPart body;
        private final ModelPart rightArm;
        private final ModelPart leftArm;
        private final ModelPart rightLeg;
        private final ModelPart leftLeg;

        public MinervaModel(ModelPart root) {
                super(root);
                this.head = root.getChild("head");
                this.body = root.getChild("body");
                this.rightArm = root.getChild("right_arm");
                this.leftArm = root.getChild("left_arm");
                this.rightLeg = root.getChild("right_leg");
                this.leftLeg = root.getChild("left_leg");
        }

        public static LayerDefinition createBodyLayer() {
                MeshDefinition meshDefinition = new MeshDefinition();
                PartDefinition partDefinition = meshDefinition.getRoot();
                PartDefinition main = partDefinition.addOrReplaceChild("main", CubeListBuilder.create(),
                                PartPose.offset(0.0F, 24.0F, 0.0F));

                PartDefinition head = main.addOrReplaceChild("head",
                                CubeListBuilder.create().texOffs(0, 0)
                                                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(32, 0)
                                                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F,
                                                                new CubeDeformation(0.5F)),
                                PartPose.ZERO);

                PartDefinition body = main.addOrReplaceChild("body",
                                CubeListBuilder.create().texOffs(16, 16)
                                                .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(16, 32)
                                                .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.25F)),
                                PartPose.ZERO);

                PartDefinition rightArm = main.addOrReplaceChild("right_arm",
                                CubeListBuilder.create().texOffs(40, 16)
                                                .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(40, 32)
                                                .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.25F)),
                                PartPose.offset(-5.0F, 2.0F, 0.0F));

                PartDefinition leftArm = main.addOrReplaceChild("left_arm",
                                CubeListBuilder.create().texOffs(32, 48)
                                                .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(48, 48)
                                                .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.25F)),
                                PartPose.offset(5.0F, 2.0F, 0.0F));

                PartDefinition rightLeg = main.addOrReplaceChild("right_leg",
                                CubeListBuilder.create().texOffs(0, 16)
                                                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(0, 32)
                                                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.25F)),
                                PartPose.offset(-1.9F, 12.0F, 0.0F));

                PartDefinition leftLeg = main.addOrReplaceChild("left_leg",
                                CubeListBuilder.create().texOffs(16, 48)
                                                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(0, 48)
                                                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F,
                                                                new CubeDeformation(0.25F)),
                                PartPose.offset(1.9F, 12.0F, 0.0F));

                return LayerDefinition.create(meshDefinition, 64, 64);
        }

        public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
                head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay);

                super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
}
