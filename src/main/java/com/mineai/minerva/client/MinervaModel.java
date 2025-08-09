package com.mineai.minerva.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class MinervaModel<T extends HumanoidRenderState> extends HumanoidModel<T> {
    public MinervaModel(ModelPart root) {
        super(root);
    }
}

    // TODO: Blockbench model code will go here
