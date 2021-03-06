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
package au.gov.asd.tac.constellation.algorithms.sna.global;

import au.gov.asd.tac.constellation.algorithms.AlgorithmPluginRegistry;
import au.gov.asd.tac.constellation.graph.node.GraphNode;
import au.gov.asd.tac.constellation.pluginframework.PluginExecution;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 * Graph density action.
 *
 * @author canis_majoris
 */
@ActionID(category = "Global", id = "au.gov.asd.tac.constellation.algorithms.sna.global.GraphDensityAction")
@ActionRegistration(displayName = "#CTL_GraphDensityAction")
@NbBundle.Messages("CTL_GraphDensityAction=Graph Density")
public class GraphDensityAction implements ActionListener {

    private final GraphNode context;

    public GraphDensityAction(final GraphNode context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(final ActionEvent ev) {
        PluginExecution.withPlugin(AlgorithmPluginRegistry.GRAPH_DENSITY).executeLater(context.getGraph());
    }
}
