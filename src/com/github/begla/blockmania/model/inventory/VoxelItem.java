/*
 * Copyright 2011 Benjamin Glatzel <benjamin.glatzel@me.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.begla.blockmania.model.inventory;

import com.github.begla.blockmania.game.Blockmania;
import com.github.begla.blockmania.logic.characters.Player;
import com.github.begla.blockmania.logic.manager.ShaderManager;
import com.github.begla.blockmania.rendering.primitives.BlockMeshFactory;
import com.github.begla.blockmania.rendering.primitives.BlockTessellator;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Benjamin 'begla' Glatzel <benjamin.glatzel@me.com>
 */
public abstract class VoxelItem extends Item {

    private BlockTessellator.BlockMesh _itemMesh;

    public VoxelItem(Player parent) {
        super(parent);

        _toolId = (byte) 1;
        _stackSize = 8;
    }

    @Override
    public boolean renderFirstPersonView() {
        ShaderManager.getInstance().enableShader("block");
        int light = GL20.glGetUniformLocation(ShaderManager.getInstance().getShader("block"), "light");
        GL20.glUniform1f(light, Blockmania.getInstance().getActiveWorldRenderer().getRenderingLightValue());
        int textured = GL20.glGetUniformLocation(ShaderManager.getInstance().getShader("block"), "textured");
        GL20.glUniform1i(textured, 0);

        glPushMatrix();

        glTranslatef(1.0f, -1.3f + (float) getParent().calcBobbingOffset((float) Math.PI / 8f, 0.05f, 2.5f) - getParent().getHandMovementAnimationOffset() * 0.5f, -1.5f - getParent().getHandMovementAnimationOffset() * 0.5f);
        glRotatef(-getParent().getHandMovementAnimationOffset() * 64.0f, 1.0f, 0.0f, 0.0f);
        glRotatef(-20f, 1.0f, 0.0f, 0.0f);
        glRotatef(-80f, 0.0f, 1.0f, 0.0f);
        glRotatef(45f, 0.0f, 0.0f, 1.0f);

        if (_itemMesh == null)
            _itemMesh = BlockMeshFactory.getInstance().generateItemMesh(_iconX, _iconY);

        _itemMesh.render();

        glPopMatrix();

        ShaderManager.getInstance().enableShader(null);

        return true;
    }
}