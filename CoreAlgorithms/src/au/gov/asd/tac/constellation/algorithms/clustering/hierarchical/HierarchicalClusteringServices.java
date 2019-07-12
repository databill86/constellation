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
package au.gov.asd.tac.constellation.algorithms.clustering.hierarchical;

import au.gov.asd.tac.constellation.graph.GraphWriteMethods;
import au.gov.asd.tac.constellation.pluginframework.PluginInteraction;
import au.gov.asd.tac.constellation.schema.analyticschema.concept.AnalyticConcept;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author twilight_sparkle
 */
public class HierarchicalClusteringServices {

    public static void fastNewmanWithPendantsClusteredFinal(GraphWriteMethods graph, final PluginInteraction interaction, final boolean interactiveMode) throws InterruptedException {
        Set<Integer> initialLinkIds = new HashSet<>();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            int vxId = graph.getVertex(i);
            if (graph.getVertexNeighbourCount(vxId) == 1) {
                initialLinkIds.add(graph.getVertexLink(vxId, 0));
            }
        }
        FastNewman.run(graph, interaction, interactiveMode, initialLinkIds, AnalyticConcept.VertexAttribute.WEIGHT.getName());
    }
}
