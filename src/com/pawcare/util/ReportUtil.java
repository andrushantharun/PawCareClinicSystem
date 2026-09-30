/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.util;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Arulthas
 */
public class ReportUtil {
     public static void showAppointmentReport() {
        try (InputStream reportStream =
                ReportUtil.class.getResourceAsStream("/Reports/Appointment.jasper");
             Connection conn = DBConnection.getConnection()) {

            if (reportStream == null) {
                throw new RuntimeException("Report file not found: /reports/Appointment.jasper");
            }

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameters, conn);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Could not generate report: " + e.getMessage(),
                    "Report Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
