package com.mineai.minerva.client.model;

import com.mineai.minerva.MinervaMod;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.LivingEntity;

public class MinervaModel<T extends LivingEntity> extends HumanoidRenderState {

	public MinervaModel() {
        super();
    }
}
