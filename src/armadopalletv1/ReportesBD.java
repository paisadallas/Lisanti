/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armadopalletv1;

/**
 *
 * @author Fredy
 */
public class ReportesBD {
    
    String descripcion, cantidad, locacion;

    public ReportesBD(String descripcion, String cantidad, String locacion) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.locacion = locacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

 
    
    
    
}
