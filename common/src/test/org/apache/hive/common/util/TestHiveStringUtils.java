/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hive.common.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TestHiveStringUtils {
  @Test
  public void testSplitAndUnEscape() throws Exception {
    splitAndUnEscapeTestCase(
        null, null);

    splitAndUnEscapeTestCase(
        "'single element'",
        new String[] {
            "'single element'"
        });

    splitAndUnEscapeTestCase(
        "yyyy-MM-dd'T'HH:mm:ss,yyyy-MM-dd'T'HH:mm:ss.S",
        new String[] {
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.S"
        });

    splitAndUnEscapeTestCase(
        "single\\,element",
        new String[] {
            "single,element"
        });
    splitAndUnEscapeTestCase(
        "element\\,one\\\\,element\\\\two\\\\\\,",
        new String[] {
            "element,one\\",
            "element\\two\\,"
        });
  }

  public void splitAndUnEscapeTestCase(String testValue, String[] expectedResults) throws Exception {
    String[] testResults = HiveStringUtils.splitAndUnEscape(testValue);
    assertTrue(Arrays.toString(expectedResults) + " == " + Arrays.toString(testResults),
        Arrays.equals(expectedResults, testResults));
  }
}
