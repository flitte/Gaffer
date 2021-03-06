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
package gaffer.graph.hook;

import gaffer.operation.OperationChain;
import gaffer.user.User;

/**
 * A <code>GraphHook</code> can be registered with a {@link gaffer.graph.Graph} and will be
 * triggered before and after operation chains are executed on the graph.
 */
public interface GraphHook {
    /**
     * Called from {@link gaffer.graph.Graph} before an {@link OperationChain}
     * is executed.
     *
     * @param opChain the {@link OperationChain} being executed.
     * @param user    the {@link User} executing the operation chain
     */
    void preExecute(final OperationChain<?> opChain, final User user);

    /**
     * Called from {@link gaffer.graph.Graph} after an {@link OperationChain}
     * is executed.
     *
     * @param result  the result from the operation chain
     * @param opChain the {@link OperationChain} that was executed.
     * @param user    the {@link User} who executed the operation chain
     */
    void postExecute(final Object result, final OperationChain<?> opChain, final User user);
}
