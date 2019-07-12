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
package au.gov.asd.tac.constellation.pluginframework.parameters.types;

import au.gov.asd.tac.constellation.pluginframework.parameters.PluginParameter;
import au.gov.asd.tac.constellation.pluginframework.parameters.PluginParameterType;
import org.openide.util.lookup.ServiceProvider;

/**
 * The ActionParameterType defines {@link PluginParameter} objects that are
 * intended to add an element to a plugin's GUI, where that element performs an
 * action that affects other parameters.
 * <p>
 * Parameters of this type should not be used to set or hold values.
 *
 * @author algol
 */
@ServiceProvider(service = PluginParameterType.class)
public class ActionParameterType extends PluginParameterType<ParameterValue> {

    public static final String ID = "action";

    public static final ActionParameterType INSTANCE = new ActionParameterType();

    /**
     * Build a new {@link PluginParameter} of this type.
     *
     * @param id The String id of the parameter to construct.
     * @return A {@link PluginParameter} of ActionParameterType.
     */
    public static PluginParameter<ParameterValue> build(String id) {
        return new PluginParameter<>(new StringParameterValue(), INSTANCE, id);
    }

    public ActionParameterType() {
        super(ID);
    }
}
