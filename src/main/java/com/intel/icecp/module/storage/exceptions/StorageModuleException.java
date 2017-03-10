/*
 * Copyright (c) 2016 Intel Corporation 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.icecp.module.storage.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 */
public class StorageModuleException extends Exception {
    private static final Logger LOGGER = LogManager.getLogger();

    public StorageModuleException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        LOGGER.error(errorMessage);
    }

    public StorageModuleException(Throwable t) {
        super(t);
    }

    public StorageModuleException(String errorMessage) {
        super(errorMessage);
        LOGGER.error(errorMessage);
    }
}

