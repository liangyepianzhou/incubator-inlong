/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.dataproxy.sink.mqzone.impl.tubezone;

import org.apache.inlong.dataproxy.sink.mqzone.AbstractZoneSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TubeZoneSink
 */
public class TubeZoneSink extends AbstractZoneSink {

    public static final Logger LOG = LoggerFactory.getLogger(TubeZoneSink.class);

    /**
     * start
     */
    @Override
    public void start() {
        try {
            super.context = new TubeZoneSinkContext(getName(), parentContext, getChannel(), this.dispatchQueue);
            super.start((sinkName, workIndex, context) -> {
                return new TubeZoneWorker(sinkName, workIndex, (TubeZoneSinkContext) context);
            });
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        super.start();
    }
}
