/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Procesos.GeneradorSeguridadSocial;
import Procesos.GenerarPdfDesprendibles;
import Procesos.LiquidacionContable;
import java.io.IOException;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author administrador
 */
@WebService(serviceName = "Expal")
public class Expal {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "SeguridadSocial")
    public String SeguridadSocial(@WebParam(name = "Empresa") String Empresa, @WebParam(name = "aniomes") int aniomes) {
        GeneradorSeguridadSocial t = new GeneradorSeguridadSocial();
        try {
            t.InicioProceso(Empresa.trim(), aniomes);
        } catch (IOException ex) {
            return ex.toString();
        }

        return "Empresa :" + Empresa + " Aniomes:" + aniomes;
    }

    @WebMethod(operationName = "LiqContable")
    public String LiqContable() {
        LiquidacionContable liq = new LiquidacionContable();
        liq.InicioProceso();
        return "-";
    }

    @WebMethod(operationName = "Enviardesprendibles")
    public String Enviar() {
        String mensaje = "";
        try {
            GenerarPdfDesprendibles d = new GenerarPdfDesprendibles();
            mensaje = d.pdf();            
        } catch (SQLException | JRException ex) {
            mensaje = ex.getMessage();
        }
        return mensaje;
    }
}
