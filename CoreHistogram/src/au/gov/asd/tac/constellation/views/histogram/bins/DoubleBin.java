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
package au.gov.asd.tac.constellation.views.histogram.bins;

import au.gov.asd.tac.constellation.graph.GraphReadMethods;
import au.gov.asd.tac.constellation.views.histogram.Bin;

/**
 * A bin that holds double floating point values.
 *
 * @author sirius
 */
public class DoubleBin extends Bin {

    public double key;

    @Override
    public int compareTo(Bin o) {
        return Double.compare(key, ((DoubleBin) o).key);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.key) ^ (Double.doubleToLongBits(this.key) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DoubleBin) {
            DoubleBin bin = (DoubleBin) o;
            return key == bin.key;
        }
        return false;
    }

    @Override
    public void prepareForPresentation() {
        label = String.valueOf(key);
    }

    @Override
    public void setKey(GraphReadMethods graph, int attribute, int element) {
        key = graph.getDoubleValue(attribute, element);
    }

    @Override
    public Bin create() {
        return new DoubleBin();
    }

    @Override
    public Object getKeyAsObject() {
        return key;
    }
}
