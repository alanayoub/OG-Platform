/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.timeseries.util;

import org.testng.annotations.Test;

import com.opengamma.util.timeseries.DoubleTimeSeries;
import com.opengamma.util.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.util.timeseries.fast.integer.FastArrayIntDoubleTimeSeries;
import com.opengamma.util.timeseries.fast.longint.FastArrayLongDoubleTimeSeries;

/**
 * 
 */
public class TimeSeriesDataTestUtilsTest {
  private static final DoubleTimeSeries<?> TS1 = new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new long[] {1, 2, 3, 4, 5}, new double[] {1, 2, 3, 4, 5});
  private static final DoubleTimeSeries<?> TS2 = new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new long[] {10, 20, 30, 40, 50}, new double[] {1, 2, 3, 4, 5});
  private static final DoubleTimeSeries<?> TS3 = new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new long[] {1, 2, 3, 4}, new double[] {1, 2, 3, 4});
  private static final DoubleTimeSeries<?> TS4 = new FastArrayLongDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new long[] {1, 2, 3, 4, 5}, new double[] {10, 20, 30, 40, 50});

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullOrEmptyWithNull() {
    TimeSeriesDataTestUtils.testNotNullOrEmpty(null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullOrEmptyWithEmpty() {
    TimeSeriesDataTestUtils.testNotNullOrEmpty(FastArrayIntDoubleTimeSeries.EMPTY_SERIES);
  }

  @Test
  public void testNullOrEmptyWithTS() {
    TimeSeriesDataTestUtils.testNotNullOrEmpty(TS1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesSizeWithNull() {
    TimeSeriesDataTestUtils.testTimeSeriesSize(null, 2);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesSizeWithEmpty() {
    TimeSeriesDataTestUtils.testTimeSeriesSize(FastArrayIntDoubleTimeSeries.EMPTY_SERIES, 2);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesSizeWithNegativeMinimum() {
    TimeSeriesDataTestUtils.testTimeSeriesSize(TS1, -2);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesSizeWithSmallTS() {
    TimeSeriesDataTestUtils.testTimeSeriesSize(TS1, 10);
  }

  @Test
  public void testTimeSeriesSize() {
    TimeSeriesDataTestUtils.testTimeSeriesSize(TS1, 3);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesDatesWithNull1() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(null, TS2);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesDatesWithNull2() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(TS1, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesDatesWithEmpty1() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(FastArrayIntDoubleTimeSeries.EMPTY_SERIES, TS2);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesDatesWithEmpty2() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(TS1, FastArrayIntDoubleTimeSeries.EMPTY_SERIES);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesDatesWithWrongDates2() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(TS1, TS2);

  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTimeSeriesDatesWithWrongDates1() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(TS1, TS3);
  }

  @Test
  public void testTimeSeriesDates() {
    TimeSeriesDataTestUtils.testTimeSeriesDates(TS1, TS4);
  }
}
