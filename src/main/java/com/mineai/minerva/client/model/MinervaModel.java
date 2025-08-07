// MinervaModel.java
package com.mineai.minerva.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class MinervaModel<T extends LivingEntity> extends HumanoidModel<Object> {
    public MinervaModel(ModelPart root) {
        super(root);
    }
}
