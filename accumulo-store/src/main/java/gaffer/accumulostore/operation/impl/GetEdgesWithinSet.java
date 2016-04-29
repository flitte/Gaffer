/*
 * Copyright 2016 Crown Copyright
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

package gaffer.accumulostore.operation.impl;

import gaffer.data.element.Edge;
import gaffer.data.elementdefinition.view.View;
import gaffer.operation.AbstractGetOperation;
import gaffer.operation.GetOperation;
import gaffer.operation.data.EntitySeed;

/**
 * Returns {@link gaffer.data.element.Edge}s where both ends are in a given set.
 **/
public class GetEdgesWithinSet extends GetElementsWithinSet<Edge> {

    public GetEdgesWithinSet() {
    }

    public GetEdgesWithinSet(final Iterable<EntitySeed> seeds) {
        super(seeds);
    }

    public GetEdgesWithinSet(final View view) {
        super(view);
    }

    public GetEdgesWithinSet(final View view, final Iterable<EntitySeed> seeds) {
        super(view, seeds);
    }

    public GetEdgesWithinSet(final GetOperation<EntitySeed, ?> operation) {
        super(operation);
    }

    @Override
    public void setIncludeEdges(final IncludeEdgeType includeEdges) {
        if (IncludeEdgeType.NONE == includeEdges) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " requires edges to be included");
        }

        super.setIncludeEdges(includeEdges);
    }

    @Override
    public boolean isIncludeEntities() {
        return false;
    }

    @Override
    public void setIncludeEntities(final boolean includeEntities) {
        if (includeEntities) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " does not support including entities");
        }
    }

    public static class Builder extends AbstractGetOperation.Builder<GetEdgesWithinSet, EntitySeed, Edge> {
        public Builder() {
            super(new GetEdgesWithinSet());
        }

        @Override
        public Builder summarise(final boolean summarise) {
            return (Builder) super.summarise(summarise);
        }

        @Override
        public Builder populateProperties(final boolean populateProperties) {
            return (Builder) super.populateProperties(populateProperties);
        }

        @Override
        public Builder view(final View view) {
            return (Builder) super.view(view);
        }

        @Override
        public Builder option(final String name, final String value) {
            return (Builder) super.option(name, value);
        }

        @Override
        public Builder seeds(final Iterable<EntitySeed> newSeeds) {
            return (Builder) super.seeds(newSeeds);
        }

        @Override
        public Builder addSeed(final EntitySeed seed) {
            return (Builder) super.addSeed(seed);
        }

        @Override
        public Builder includeEdges(final IncludeEdgeType includeEdgeType) {
            return (Builder) super.includeEdges(includeEdgeType);
        }
    }
}
