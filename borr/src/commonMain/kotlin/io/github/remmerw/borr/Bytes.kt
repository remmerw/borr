// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////
package io.github.remmerw.borr

/**
 * Immutable Wrapper around a byte array.
 *
 *
 * Wrap a bytearray so it prevents callers from modifying its contents. It does this by making a
 * copy upon initialization, and also makes a copy if the underlying bytes are requested.
 *
 * @since 1.0.0
 */
internal class Bytes(buf: ByteArray, start: Int, len: Int) {
    // We copy the data on input and output.
    private val data = ByteArray(len)

    init {
        buf.copyInto(data, 0, start, len)
    }

    /**
     * @return a copy of the bytes wrapped by this object.
     */
    fun toByteArray(): ByteArray {
        val result = ByteArray(data.size)
        data.copyInto(result, 0, 0, data.size)
        return result
    }
}
