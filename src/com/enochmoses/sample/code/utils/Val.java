package com.enochmoses.sample.code.utils;

import java.util.Date;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * The Val class contains validation routines which are used by the MOS application.
 * 
 * @author emoses
 * 
 * 
 */
public class Val {
	private static Log log = LogFactory.getLog(Val.class);
    /*
     * A private constructor so that this object CANNOT be instantiated.
     */
	private Val(){
    	
    }
	/**
	 * Tests whether a field is null or not. It returns a boolean answer.
	 * 
	 * @param field
	 *            The field that is being tested for null or not.
	 * 
	 * @return
	 * <ul>
	 * <li>TRUE if the field is NULL</li>
	 * <li>FALSE if the field is not NULL</li>
	 * </ul>
	 *  
	 */
	public static boolean isNull(String field) {
		boolean valid = false;
		if (null == field) {
			valid = true;
		}
		return valid;
	}

	/**
	 * Tests whether a String field is blank or not. 
	 * It returns a boolean answer.
	 * 
	 * @param field
	 *            The String field that is being checked.
	 * @return
	 *            <ul>
	 *            <li>TRUE if the field is BLANK</li>
	 *            <li>FALSE if the field is not BLANK</li>
	 *            </ul>
	 */
	public static boolean isBlank(String field) {
		boolean valid = false;
		
		if (isNull(field)||"".equals(field.trim())) {
			valid = true;
		}
		return valid;
	}

	/**
	 * Checks the field whether if it's a date or not.
	 * 
	 * @param inputDate
	 *            The field is that being checked
	 * @return
	 *            <ul>
	 *            <li>TRUE if the field is a valid date</li>
	 *            <li>FALSE if inputDate is not a valid date</li>
	 *            </ul>
	 */
	public static boolean isDate(String inputDate) {
		boolean valid = false;
		
		try {
			if (!isBlank(inputDate)) {
				StringTokenizer st = new StringTokenizer(inputDate, "-/.");
				if (st.countTokens() == 3) {
					// double check the date
					valid = doubleCheckDate(inputDate);
				} else {
					valid = false;
				}

			}
		} catch (NumberFormatException nfe) {

			valid = false;
		}
		return valid;
	}
	/**
	 * Double checks the field to see if field is a valid Date. It checks if the
	 * date has months greater
	 * @param inputDate
	 *            The field is that being checked
	 * @return
     *            <ul>
	 *            <li>TRUE if the field is a valid date</li>
	 *            <li>FALSE if inputDate is not a valid date</li>
	 *            </ul>
	 */
	private static boolean doubleCheckDate (String inputDate){
		boolean valid = false;
		Date date = DateUtil.getDateObject(inputDate);
		Calendar cal = Calendar.getInstance();
		if(date != null){
		cal.setTime(date);
		}
		StringTokenizer st = new StringTokenizer(inputDate, "-/.");
        
		String month = st.nextToken();
        String day = st.nextToken();
        String year = st.nextToken();
      
        int intYear = Integer.parseInt(year);
        int intMonth = Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        // If the user entered a two digit or a single digit year, then it is
        // assumed that the user meant the year in the 21st century.
        if(intYear < 100){
        	intYear += 2000;
            
        }
        // Checks to see if the entered month, date, and year match the calendar
        // object's month, date and year. If not then the date entered by the user
        // is not valid.
        if(intYear == cal.get(Calendar.YEAR) && intMonth == cal.get(Calendar.MONTH)+1 
        		&& intDay == cal.get(Calendar.DAY_OF_MONTH)){
        	valid = true;
        }
        return valid;
	}
	/**
	 * Checks to see if the field is a valid number.
	 * 
	 * @param inputNumber
	 *            The field which is being checked.
	 * @return
	 *            <ul>
	 *            <li>TRUE if the field is a valid number like '234'</li>
	 *            <li>FALSE if the field is not a valid number like '234*'
	 *            </li>
	 *            </ul>
	 *  
	 */
	public static boolean isNumber(String inputNumber)
			throws MalformedPatternException {
		boolean valid = true;
		
		if (isBlank(inputNumber)) {
			valid = false;
		}
		
		if (valid) {
			String numberFormat = matchedPattern(inputNumber,
			//"^[0-9][0-9]{0,}[.]{0,}[0-9]{0,}$");
					"^\\s*-?\\d*(?:\\.\\d*)?\\s*$");
			if ("".equals(numberFormat)) {
				valid = false;
			}
		}

		return valid;
	}

	/**
	 * Checks to see if the string is a positive number. It also checks if the
	 * number is a valid number and it is not empty string
	 * 
	 * @param inputNumber
	 *            The field that is being checked
	 * @return
	 *            <ul>
	 *            <li>TRUE if the field is a valid positive number like '234'
	 *            </li>
	 *            <li>FALSE if the field is not a valid number like '-243' 
	 *            </li>
	 *            </ul>
	 */

	public static boolean isPositiveNumber(String inputNumber)
			throws MalformedPatternException {
		
		boolean valid = true;
		if (isBlank(inputNumber)) {
			valid = false;
		}
		
		if (valid && !isNumber(inputNumber)) {
			valid = false;
		}
		
		if (valid && Float.parseFloat(inputNumber) < 0) {
			valid = false;
		}

		return valid;
	}

	/**
	 * Checks to see if the field is not greater in length than the length
	 * specified. 
	 * 
	 * @param field
	 *            The field that is being checked.
	 * @param stringLength
	 *            The maximum length of the field.
	 * @return    
	 * 			  <ul>
	 *            <li>TRUE if the field is not greater than the maximum length specified
	 *                and it is not null.
	 *            </li>
	 *            <li>FALSE if the field is greater than the maximum length specified or 
	 *                if the field is null.
	 *            </li>
	 *            </ul>
	 */

	public static boolean isLengthNotGreater(String field, int stringLength) {
		boolean valid = true;
		valid = !isNull(field);
		if (valid && field.length() > stringLength) {
			valid = false;
		}
		return valid;
	}

	/**
	 * Checks to see if the supplied password is valid or not.
	 * 
	 * @param strPassword
	 *            The supplied password
	 * @return    
	 * 			  <ul>
	 *            <li>TRUE if the suppied password is valid.
	 *                These are a few examples of a valid password: 
	 * <ol><li>Hello2World</li><li>hello*World</li><li>hello32worlD</li></ol>
	 *            </li>
	 *            <li>FALSE if the suppied password is NOT valid.
	 *            </li>
	 *            </ul>
	 *  @throws MalformedPatternException
	 *             This exception is thrown when the regular expression is not
	 *             right. 
	 */
	public static boolean isPasswordValidFormat(String strPassword)
			throws MalformedPatternException {
		boolean valid = false;

		if (!isBlank(strPassword) && strPassword.length() > 7) {
			String overall = matchedPattern(
					strPassword,
					"^[A-Za-z][-A-Za-z0-9`~!@#$%^&*(){}\\?\\[\\]\\\\|\\:;\\\"',./<>\\\\=\\\\_]{6,}[A-Za-z]$");

			String uppercase = matchedPattern(strPassword, "[A-Z]");
			String lowercase = matchedPattern(strPassword, "[a-z]");
			String digit = matchedPattern(strPassword.substring(1, strPassword
					.length() - 1), "[0-9]");
			String special = matchedPattern(strPassword.substring(1,
					strPassword.length() - 1),
					"[-`~!@#$%^&*(){}\\?\\[\\]\\\\|\\:;\\\"',./<>\\\\=\\\\_]");

			int criteria = 0;
			if (!"".equals(uppercase)) {
				criteria++;
			}
			if (!"".equals(lowercase)) {
				criteria++;
			}
			if (!"".equals(digit)) {
				criteria++;
			}
			if (!"".equals(special)) {
				criteria++;
			}

			// need at least 3 of the 4
			if (!"".equals(overall) && (criteria >= 3)) {
				valid = true;
			}
			log.debug("password valid: " + valid);
		}

		return valid;
	}

	/**
	 * Using Java ORO regular expressions, a field is checked against a regular
	 * expression.
	 * 
	 * @param inputString
	 *            The field that is being checked.
	 * @param strPattern
	 *            The regular expression pattern which is matched against the
	 *            field.
	 * @return <ul><li>TRUE if the field matches the regular expression</li>
	 *             <li>FALSE if the field doesn't match the regular expression</li>
	 *         </ul>
	 * @throws MalformedPatternException
	 *             This exception is thrown when the regular expression is not
	 *             right.
	 */
	private static String matchedPattern(String inputString, String strPattern)
			throws MalformedPatternException {
		String matched = "";
		PatternMatcher matcher;
		PatternCompiler compiler;
		Pattern realPattern;
		PatternMatcherInput input;
		MatchResult result;

		compiler = new Perl5Compiler();
		matcher = new Perl5Matcher();

		try {
			realPattern = compiler.compile(strPattern);
			input = new PatternMatcherInput(inputString);
			matcher.contains(input, realPattern);
			result = matcher.getMatch();

			if (result != null) {
				matched = result.toString();
			}
		} catch (MalformedPatternException mpe) {
			log.error(mpe);
			throw (mpe);
		}

		return matched;
	}
	/**
	 * Checks whether given field value is the specified range or not
	 * @param field validating field
	 * @param begin min value of range
	 * @param end max value of range
	 * @return
	 *     TRUE if the field is in the range
	 *     FALSE if the field is not in the range 
	 * @throws MalformedPatternException
	 */
	public static boolean isBetween(String field, int begin, int end)
	                           throws MalformedPatternException {
		boolean valid = true;
		log.debug("in validation method");
		valid = isPositiveNumber(field);
		int fieldValue = Integer.parseInt(field);
		if (!valid || end < begin || fieldValue < begin || fieldValue > end) {
		    log.debug("in loop");
			valid = false;
		}
		
		return valid;
	}

}