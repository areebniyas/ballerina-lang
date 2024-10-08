/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerina.runtime.internal.scheduling;

import io.ballerina.runtime.api.PredefinedTypes;
import io.ballerina.runtime.internal.TypeChecker;

/**
 * WaitContext for Wait for any action.
 *
 * @since 1.0.0
 */
public class WaitAnyContext extends WaitContext {

    WaitAnyContext(SchedulerItem schedulerItem) {
        super(schedulerItem);
    }

    @Override
    boolean handlePanic() {
        waitCount.set(0);
        return true;
    }

    @Override
    boolean waitCompleted(Object result) {
        if (TypeChecker.checkIsType(result, PredefinedTypes.TYPE_ERROR)) {
            return waitCount.decrementAndGet() == 0;
        }
        return true;
    }
}
