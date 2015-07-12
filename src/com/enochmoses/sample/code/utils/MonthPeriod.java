package com.enochmoses.sample.code.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * A period representing a calendar month.
 * 
 * @author emoses
 */
public class MonthPeriod extends Period {

	/**
	 * Format for representing a month in a WebCEO fact table.
	 */
	private static SimpleDateFormat webCeoFormatter = new SimpleDateFormat(
			"yyyyMM");

	/**
	 * Datetype for representing a month in a WebCEO fact table.
	 */
	private String datetype = "M";

	/**
	 * Creates a month period containing the date specified.
	 * 
	 * @param focus
	 *            Some day during the plan period
	 */
	public MonthPeriod(Date focus) {
		Calendar focusDay = Calendar.getInstance();
		focusDay.setTime(focus);
		this.setStart(focusDay);
	}

	/**
	 * Creates a month period based on the WebCEO DATEDATA representation.
	 * 
	 * @param data
	 *            WebCEO DATEDATA representation
     * @throws ParseException
     *             if the WebCEO datedata cannot be converted
	 */
	public MonthPeriod(String data) throws ParseException {
	    if (data.length() != 6) {
	        throw new ParseException("Date must be in format YYYYMM", 6);
	    }
		Date focus = (Date) webCeoFormatter.parse(data);

		Calendar focusDay = Calendar.getInstance();
		focusDay.setTime(focus);
		this.setStart(focusDay);
	}

	public Period getPreviousPeriod() {
		Calendar prevPeriod = Calendar.getInstance();
		prevPeriod.setTime(this.getEnd());
		prevPeriod.add(Calendar.MONTH, -1); // takes last month
		int endOfPrevMonth = prevPeriod.getActualMaximum(Calendar.DAY_OF_MONTH);
		prevPeriod.set(Calendar.DAY_OF_MONTH, endOfPrevMonth);

		return new MonthPeriod(prevPeriod.getTime());
	}

	/**
	 * @return date used to identify the next month period (i.e., next month)
	 */
	public Period getNextPeriod() {
		Calendar nextPeriod = Calendar.getInstance();
		nextPeriod.setTime(this.getEnd());
		nextPeriod.add(Calendar.MONTH, 1);
		int endOfNextMonth = nextPeriod.getActualMaximum(Calendar.DAY_OF_MONTH);
		nextPeriod.set(Calendar.DAY_OF_MONTH, endOfNextMonth);

		return new MonthPeriod(nextPeriod.getTime());
	}

	/**
	 * Sets the start of the month period to first of the month at the beginning
	 * of the day.
	 * 
	 * @param focus
	 *            Some day during the month period
	 */
	private void setStart(Calendar focus) {
		Calendar startDay = (Calendar) focus.clone();
		startDay.setLenient(true);

		// Set the first of the month as the start of the period
		int startDate = startDay.getMinimum(Calendar.DAY_OF_MONTH);
		startDay.set(Calendar.DAY_OF_MONTH, startDate);

		//  Done
		this.start = DateUtil.startOfDay(startDay);

		//  Now set the end of the week to be in sync
		this.setEnd(startDay);
	}

	/**
	 * Sets the end of the month period to the last day of the month at the end
	 * of the day.
	 * 
	 * @param focus
	 *            Some day during the month period
	 */
	private void setEnd(Calendar startDay) {
		Calendar endDay = (Calendar) startDay.clone();
		endDay.setLenient(true);

		// Set the last day of the month as the end of the period
		int endDate = endDay.getActualMaximum(Calendar.DAY_OF_MONTH);
		endDay.set(Calendar.DAY_OF_MONTH, endDate);

		//  Done
		this.end = DateUtil.endOfDay(endDay);
	}

    /**
     * Returns the representation for the DATETYPE field.
     *
     * <ul>
     * <li>D = Day</li>
     * <li>W = Plan Period (Week)</li>
     * <li>M = Month</li>
     * <li>Q = Fiscal Quarter</li>
     * <li>Y = Year</li>
     * </ul>
     *  
     * @return representation for the DATETYPE field
     */
    public String getDatetype() {
        return this.datetype;
    }

	/**
	 * Returns the representation for the DATEDATA field.
	 * 
	 * <ul>
	 * <li>Day: YYYYMMDD</li>
	 * <li>Plan Period (Week): YYYYWW</li>
	 * <li>Month: YYYYMM</li>
	 * <li>Fiscal Quarter: YYYYQ</li>
	 * <li>Year: YYYY</li>
	 * </ul>
	 * 
	 * @return representation for the DATEDATA field
	 */
	public String getDatedata() {
		return webCeoFormatter.format(this.getPeriodDate());
	}

	/**
	 * Compares two <code>MonthPeriod</code> objects for equality.
	 * 
	 * <p>
	 * They are equal if they are the same object. They are not equal if they
	 * are not the same class. Otherwise, they are equal if they have the same
	 * start and end.
	 * </p>
	 * 
	 * @param other
	 *            the object to compare with.
	 * @return true if the objects are the same; false otherwise.
	 * @see org.apache.commons.lang.builder.EqualsBuilder
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MonthPeriod)) {
			return false;
		}
		MonthPeriod castOther = (MonthPeriod) other;
		return new EqualsBuilder()
				.append(this.getStart(), castOther.getStart()).append(
						this.getEnd(), castOther.getEnd()).isEquals();
	}

}