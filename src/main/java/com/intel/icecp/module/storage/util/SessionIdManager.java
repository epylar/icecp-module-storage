/*
 * ******************************************************************************
 *
 *  INTEL CONFIDENTIAL
 *
 *  Copyright 2013 - 2016 Intel Corporation All Rights Reserved.
 *
 *  The source code contained or described herein and all documents related to the
 *  source code ("Material") are owned by Intel Corporation or its suppliers or
 *  licensors. Title to the Material remains with Intel Corporation or its
 *  suppliers and licensors. The Material contains trade secrets and proprietary
 *  and confidential information of Intel or its suppliers and licensors. The
 *  Material is protected by worldwide copyright and trade secret laws and treaty
 *  provisions. No part of the Material may be used, copied, reproduced, modified,
 *  published, uploaded, posted, transmitted, distributed, or disclosed in any way
 *  without Intel's prior express written permission.
 *
 *  No license under any patent, copyright, trade secret or other intellectual
 *  property right is granted to or conferred upon you by disclosure or delivery of
 *  the Materials, either expressly, by implication, inducement, estoppel or
 *  otherwise. Any license under such intellectual property rights must be express
 *  and approved by Intel in writing.
 *
 *  Unless otherwise agreed by Intel in writing, you may not remove or alter this
 *  notice or any other notice embedded in Materials by Intel or Intel's suppliers
 *  or licensors in any way.
 *
 * *********************************************************************
 */

package com.intel.icecp.module.storage.util;

import com.intel.icecp.core.Channel;
import com.intel.icecp.core.messages.BytesMessage;
import com.intel.icecp.module.storage.StorageModule;
import com.intel.icecp.module.storage.messages.PersistCallback;

/**
 * Class for managing sessionIds in the Channels Map and Subscription Callback Map
 */
public class SessionIdManager {
    private final long sessionId;
    private StorageModule context;

    /**
     * Constructor
     *
     * @param context Storage module processing this message
     * @param sessionId Current active sessionId
     */
    public SessionIdManager(StorageModule context, Long sessionId) {
        this.context = context;
        this.sessionId = sessionId;
    }

    /**
     * Public method to update channel and callback references for renamed sessionIds during
     * rename and delete session
     *
     * @param newSessionId New sessionId obtained during rename/delete
     * @param channel Active listening channel for sessionId
     * @param callback Active callback registered for session
     */
    public synchronized void updateNewSessionId(Long newSessionId, Channel<BytesMessage> channel, PersistCallback callback) {
        callback.setSessionId(newSessionId);
        context.addChannel(newSessionId, channel, callback);
    }

    /**
     * Public method to cleanup channel and callback references for non-renamed sessionIds during
     * delete session
     */
    public synchronized void cleanupSessionId() {
        context.removeChannel(sessionId);
        context.removeSubscriptionCallback(sessionId);
    }

}
