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
package au.gov.asd.tac.constellation.views.analyticview.analytics;

import au.gov.asd.tac.constellation.algorithms.sna.SnaConcept;
import au.gov.asd.tac.constellation.algorithms.sna.similarity.ResourceAllocationIndexPlugin;
import au.gov.asd.tac.constellation.graph.schema.SchemaAttribute;
import au.gov.asd.tac.constellation.pluginframework.Plugin;
import au.gov.asd.tac.constellation.pluginframework.PluginInfo;
import au.gov.asd.tac.constellation.pluginframework.parameters.PluginParameters;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 * The Resource Allocation Index analytic for the Analytic View.
 *
 * @author canis_majoris
 */
@ServiceProviders({
    @ServiceProvider(service = AnalyticPlugin.class),
    @ServiceProvider(service = Plugin.class)
})
@PluginInfo(tags = {"ANALYTIC"})
@AnalyticInfo(analyticCategory = "Similarity")
@NbBundle.Messages("ResourceAllocationIndexAnalytic=Resource Allocation Index Analytic")
public class ResourceAllocationIndexAnalytic extends ScoreAnalyticPlugin {

    @Override
    public String getDocumentationUrl() {
        return "nbdocs://au.gov.asd.tac.constellation.views.analyticview/au/gov/asd/tac/constellation/views/analyticview/docs/analyticview-analytic-resource-allocation-index.html";
    }

    @Override
    public Set<SchemaAttribute> getAnalyticAttributes(final PluginParameters parameters) {
        final Set<SchemaAttribute> analyticAttributes = new HashSet<>();
        analyticAttributes.add(SnaConcept.TransactionAttribute.RESOURCE_ALLOCATION_INDEX);
        return Collections.unmodifiableSet(analyticAttributes);
    }

    @Override
    public Class<? extends Plugin> getAnalyticPlugin() {
        return ResourceAllocationIndexPlugin.class;
    }
}
