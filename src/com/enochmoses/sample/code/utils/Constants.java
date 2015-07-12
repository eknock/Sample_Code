/*
 * Created on Aug 30, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.enochmoses.sample.code.utils;

/**
 * @author emoses
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Constants {
	
	//====================================================
	// ActionForwards
	//====================================================
	public static final String ACTION_FAILURE = "failure";
	public static final String ACTION_SUCCESS = "success";
	
	//====================================================
	// Excel Spreadsheets information
	//====================================================	
	public static final String UPLOAD_FILE_LOCATION = "C:/FILES/";
	public static final String EXCEL_SUFFIX = ".xls";
	public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
	public static final String OPEN_OFFICE_EXCEL_MIME_TYPE = "application/msexcell";
	
    //=====================================================
	// Basic Report Types
	//=====================================================
	public static final String REPORT_TYPE_1 = "Financial Summaries";
	public static final String REPORT_TYPE_2 = "Operational Reports";
	
	//=====================================================
	// Operation Report Types
    //=====================================================
	public static final String REPORT_OS_1 = "Comptroller";
	public static final String REPORT_OS_2 = "Contracts";
	public static final String REPORT_OS_3 = "Executives";
	public static final String REPORT_OS_4 = "Program";
	public static final String REPORT_OS_5 = "Quality";
	public static final String REPORT_OS_6 = "Technical Support";
	
    //=====================================================
	// Operation Report Types ID
	//=====================================================
	public static final int COMPTROLLER_ID = 210;
	public static final int CONTRACTS_ID = 220;
	public static final int ENGINEERING_ID = 230;
	public static final int PROJECTS_ID = 240;
	public static final int QUALITY_ID = 250;
	public static final int TECH_COMMAND_ID = 260;
    //=======================================================
	// For Display Purposes in Category.jsp
	//=======================================================
	public static final String CATEGORY = "CATEGORY - ";
	public static final String DEPARTMENT_LEVEL = "Department Level";
    //=======================================================
	// Category Types
	//=======================================================
	public static final String CONTRACTS_CATEGORY_1 = "Surveillance & Analysis";
	public static final String CONTRACTS_CATEGORY_2 = "A-mods";
	public static final String CONTRACTS_CATEGORY_3 = "Negotiated Changes";

	public static final String ENGINEERING_CATEGORY_1 = "Correspondence";
	public static final String ENGINEERING_CATEGORY_2 = "Param";

	public static final String PROJECTS_CATEGORY_1 = "Trial Cards";
	public static final String PROJECTS_CATEGORY_2 = "GI Trial Cards";
	public static final String PROJECTS_CATEGORY_3 = "KI Trial Cards";

	public static final String QUALITY_CATEGORY_1 = "Observations";
	public static final String QUALITY_CATEGORY_2 = "PVI-TW Observations";
	public static final String QUALITY_CATEGORY_3 = "PR Observations";
	public static final String QUALITY_CATEGORY_4 = "PE Observations";

	public static final String TECH_COMMAND_CATEGORY_1 = "Critical Item";
	public static final String TECH_COMMAND_CATEGORY_2 = "Corrective Action Requests";
	public static final String TECH_COMMAND_CATEGORY_3 = "Audits";
	//=======================================================
	// Category Types
	//=======================================================
	public static final int DEPARTMENT_LEVEL_ID = 0;
	
	public static final int CONTRACTS_CATEGORY_1_ID = 510;
	public static final int CONTRACTS_CATEGORY_2_ID = 511;
	public static final int CONTRACTS_CATEGORY_3_ID = 512;
	
	public static final int ENGINEERING_CATEGORY_1_ID = 500;
	public static final int ENGINEERING_CATEGORY_2_ID = 501;
	
	public static final int PROJECTS_CATEGORY_1_ID = 520;
	public static final int PROJECTS_CATEGORY_2_ID = 521;	
	public static final int PROJECTS_CATEGORY_3_ID = 522;
	
	public static final int QUALITY_CATEGORY_1_ID = 530;
	public static final int QUALITY_CATEGORY_2_ID = 531;
	public static final int QUALITY_CATEGORY_3_ID = 532;
	public static final int QUALITY_CATEGORY_4_ID = 533;
	
	public static final int TECH_COMMAND_CATEGORY_1_ID = 14;
    public static final int TECH_COMMAND_CATEGORY_2_ID = 15;
    public static final int TECH_COMMAND_CATEGORY_3_ID = 16;
	//============================================================
	// Session attributes
	//============================================================
	public static final String REPORT_COLLECTION = "reportCollection";
	public static final String REPORT_FORM = "reportForm";
	
	//============================================================
	// html:error properties
	//============================================================
	public static final String ERROR_DATE = "date";
	public static final String ERROR_FILE = "file";
	//============================================================
	// Excel Report Information
	//============================================================
	public static final int NUMBER_ROWS = 40;
	

}
