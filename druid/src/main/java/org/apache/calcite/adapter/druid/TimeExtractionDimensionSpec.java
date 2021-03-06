/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
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
package org.apache.calcite.adapter.druid;

/**
 * DimensionSpec implementation that uses a time format extraction function.
 */
public class TimeExtractionDimensionSpec extends ExtractionDimensionSpec {

  public TimeExtractionDimensionSpec(
      ExtractionFunction extractionFunction, String outputName) {
    super(DruidTable.DEFAULT_TIMESTAMP_COLUMN, extractionFunction, outputName);
  }

  /**
   * Creates a time extraction DimensionSpec that renames the '__time' column
   * to the given name.
   *
   * @param outputName name of the output column
   * @return the time extraction DimensionSpec instance
   */
  public static TimeExtractionDimensionSpec makeFullTimeExtract(String outputName) {
    return new TimeExtractionDimensionSpec(
        TimeExtractionFunction.createDefault(), outputName);
  }

  /**
   * Creates a time extraction DimensionSpec that formats the '__time' column
   * according to the given granularity and outputs the column with the given
   * name. Only YEAR, MONTH, and DAY granularity are supported.
   *
   * @param granularity granularity to apply to the column
   * @param outputName name of the output column
   * @return the time extraction DimensionSpec instance or null if granularity
   * is not supported
   */
  public static TimeExtractionDimensionSpec makeExtract(
      Granularity granularity, String outputName) {
    switch (granularity) {
    case YEAR:
      return new TimeExtractionDimensionSpec(
          TimeExtractionFunction.createFromGranularity(granularity), outputName);
    case MONTH:
      return new TimeExtractionDimensionSpec(
          TimeExtractionFunction.createFromGranularity(granularity), outputName);
    case DAY:
      return new TimeExtractionDimensionSpec(
          TimeExtractionFunction.createFromGranularity(granularity), outputName);
    // TODO: Support other granularities
    default:
      return null;
    }
  }
}

// End TimeExtractionDimensionSpec.java
