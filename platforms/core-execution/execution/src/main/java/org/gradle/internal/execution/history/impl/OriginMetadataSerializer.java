/*
 * Copyright 2024 the original author or authors.
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

package org.gradle.internal.execution.history.impl;

import org.gradle.caching.internal.origin.OriginMetadata;
import org.gradle.internal.hash.HashCode;
import org.gradle.internal.serialize.AbstractSerializer;
import org.gradle.internal.serialize.Decoder;
import org.gradle.internal.serialize.Encoder;
import org.gradle.internal.serialize.HashCodeSerializer;

import java.io.IOException;
import java.time.Duration;

public class OriginMetadataSerializer extends AbstractSerializer<OriginMetadata> {

    private final HashCodeSerializer hashCodeSerializer = new HashCodeSerializer();

    @Override
    public OriginMetadata read(Decoder decoder) throws IOException {
        String buildInvocationId = decoder.readString();
        HashCode buildCacheKey = hashCodeSerializer.read(decoder);
        Duration executionTime = Duration.ofMillis(decoder.readSmallLong());
        return new OriginMetadata(
            buildInvocationId,
            buildCacheKey,
            executionTime
        );
    }

    @Override
    public void write(Encoder encoder, OriginMetadata originMetadata) throws IOException {
        encoder.writeString(originMetadata.getBuildInvocationId());
        hashCodeSerializer.write(encoder, originMetadata.getBuildCacheKey());
        encoder.writeSmallLong(originMetadata.getExecutionTime().toMillis());
    }

}
