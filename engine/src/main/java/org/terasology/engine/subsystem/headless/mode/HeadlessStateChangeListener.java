/*
 * Copyright 2014 MovingBlocks
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
package org.terasology.engine.subsystem.headless.mode;

import org.terasology.engine.GameEngine;
import org.terasology.engine.StateChangeSubscriber;
import org.terasology.engine.modes.GameState;
import org.terasology.engine.modes.StateMainMenu;
import org.terasology.registry.CoreRegistry;

/**
 * This listener checks whether the engine goes back to the main menu, which for a headless server signals the server
 * should be shut down. This happens mainly in cases where the loading process was not successful.
 */
public class HeadlessStateChangeListener implements StateChangeSubscriber {

    @Override
    public void onStateChange() {
        GameEngine engine = CoreRegistry.get(GameEngine.class);
        GameState state = engine.getState();
        if (state instanceof StateMainMenu) {
            engine.shutdown();
        }
    }

}
