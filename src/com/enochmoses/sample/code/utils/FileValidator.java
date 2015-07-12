/*
 * Created on Aug 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.enochmoses.sample.code.utils;

/**
 * @author emoses
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;




public class FileValidator implements Constants {
    private static Log log = LogFactory.getLog(FileValidator.class);
    /**
     * Validates the mime type of Excel template file
     * @param templateFile
     *        the file that is uploaded
     * @param fileNameRoot
     *        file name
     * @return true if the the template is an Excel file
     */
    public boolean validateMimeType(FormFile templateFile, String fileNameRoot) {

        boolean isExcel;
        String fileType = templateFile.getContentType();
        log.debug("mime type: " + fileType);

        if (EXCEL_MIME_TYPE.equals(fileType)
                || OPEN_OFFICE_EXCEL_MIME_TYPE.equals(fileType)) {
            isExcel = true;
            log.debug("Upload file is an Excel file.");
        } else {
            isExcel = false;
            log.debug("Upload file is not an Excel file.");
        }
        log.debug("excel mime type: " + isExcel);
        return isExcel;
    }
    /**
     * Uploads the file and saves it in the desired location. The desired
     * location is in the mos.properties file
     * 
     * @param hibernateSession
     *            an open hibernate session to query
     * @param templateFile
     *            The uploaded file
     * @param fileNameRoot
     *            The name of the file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws HibernateException
     */
    public void uploadFile(Session hibernateSession, FormFile templateFile,
            String fileNameRoot) throws FileNotFoundException, IOException,
            HibernateException {

        String location = ConfigUtil.getReportLocation(hibernateSession);
        log.debug("Template directory: " + location);
        String fileName = fileNameRoot + EXCEL_SUFFIX;

        String newFileName = location + fileName;
        log.debug("New File Name: " + newFileName);

        FileOutputStream fos = null;
        OutputStream os = null;

        try {
            fos = new FileOutputStream(newFileName);
            os = new BufferedOutputStream(fos);

            byte[] fileContents = templateFile.getFileData();
            CopyUtils.copy(fileContents, os);
        } catch (FileNotFoundException fnfe) {
            log.error(fnfe);
            throw (fnfe);
        } catch (IOException ioe) {
            log.error(ioe);
            throw (ioe);
        } finally {
            if (os != null) {
                os.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

}