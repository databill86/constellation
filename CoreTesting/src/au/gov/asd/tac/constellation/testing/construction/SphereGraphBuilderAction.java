/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.testing.construction;

import au.gov.asd.tac.constellation.graph.node.GraphNode;
import au.gov.asd.tac.constellation.pluginframework.PluginExecution;
import au.gov.asd.tac.constellation.testing.CoreTestingPluginRegistry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

/**
 * Create a random graph arranged on the surface of a sphere.
 *
 * @author algol
 * @author antares
 * @author canis_majoris
 */
@ActionID(category = "Experimental", id = "au.gov.asd.tac.constellation.testing.construction.SphereGraphBuilderAction")
@ActionRegistration(displayName = "#CTL_SphereGraphBuilderAction", iconBase = "au/gov/asd/tac/constellation/testing/construction/sphere.png", surviveFocusChange = true)
@ActionReferences({
    @ActionReference(path = "Menu/Experimental/Build Graph", position = 0)
})
@Messages("CTL_SphereGraphBuilderAction=Sphere Graph Builder")
public final class SphereGraphBuilderAction implements ActionListener {

    private final GraphNode context;

    public SphereGraphBuilderAction(final GraphNode context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(final ActionEvent ev) {
        PluginExecution.withPlugin(CoreTestingPluginRegistry.SPHERE_GRAPH_BUILDER)
                .withParameter(SphereGraphBuilderPlugin.N_PARAMETER_ID, 10)
                .withParameter(SphereGraphBuilderPlugin.T_PARAMETER_ID, 10)
                .withParameter(SphereGraphBuilderPlugin.OPTION_PARAMETER_ID, "Random vertices")
                .withParameter(SphereGraphBuilderPlugin.ADD_CHARS_PARAMETER_ID, true)
                .withParameter(SphereGraphBuilderPlugin.USE_LABELS_PARAMETER_ID, true)
                .withParameter(SphereGraphBuilderPlugin.USE_RANDOM_ICONS_PARAMETER_ID, true)
                .withParameter(SphereGraphBuilderPlugin.USE_ALL_DISPLAYABLE_CHARS_PARAMETER_ID, false)
                .withParameter(SphereGraphBuilderPlugin.DRAW_MANY_TX_PARAMETER_ID, false)
                .withParameter(SphereGraphBuilderPlugin.DRAW_MANY_DECORATORS_PARAMETER_ID, false)
                .interactively(true)
                .executeLater(context.getGraph());
    }
}
