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
package au.gov.asd.tac.constellation.arrangements.tree;

import au.gov.asd.tac.constellation.functionality.BBoxf;
import java.util.List;

/**
 *
 * @author algol
 */
public class BoundingCircle {

    public double x;
    public double y;
    public double radius;

    public BoundingCircle() {
        this(0, 0, 0);
    }

    public BoundingCircle(final double x, final double y, final double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public static BoundingCircle enclosingCircle(final List<BoundingCircle> circles) {
        final BBoxf box = new BBoxf();
        for (final BoundingCircle c : circles) {
            box.add((float) (c.x + c.radius), (float) (c.y + c.radius), 0f);
            box.add((float) (c.x - c.radius), (float) (c.y - c.radius), 0f);
        }

        final float[] centre = box.getCentre();
        final float[] min = box.getMin();
        final float[] max = box.getMax();
        final float minx = min[BBoxf.X];
        final float maxx = max[BBoxf.X];
        final float miny = min[BBoxf.Y];
        final float maxy = max[BBoxf.Y];
        final float radius = Math.max((maxx - minx) / 2, (maxy - miny) / 2);

        final BoundingCircle enclosing = new BoundingCircle(centre[BBoxf.X], centre[BBoxf.Y], radius);

        return enclosing;
    }

    @Override
    public String toString() {
        return String.format("[%s: x=%f y=%f r=%f]", BoundingCircle.class.getSimpleName(), x, y, radius);
    }
}
