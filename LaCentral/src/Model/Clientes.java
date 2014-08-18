package Model;
// Generated 18/08/2013 10:09:46 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes  implements java.io.Serializable {


     private Integer claveCliente;
     private Personas personas;
     private Date fechaRegistro;
     private boolean activo;
     private boolean tipoCliente;
     private Set<Presupuestos> presupuestoses = new HashSet<Presupuestos>(0);
     private Set<Historiales> historialeses = new HashSet<Historiales>(0);

    public Clientes() {
    }

	
    public Clientes(Date fechaRegistro, boolean activo, boolean tipoCliente) {
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.tipoCliente = tipoCliente;
    }
    public Clientes(Personas personas, Date fechaRegistro, boolean activo, boolean tipoCliente, Set<Presupuestos> presupuestoses, Set<Historiales> historialeses) {
       this.personas = personas;
       this.fechaRegistro = fechaRegistro;
       this.activo = activo;
       this.tipoCliente = tipoCliente;
       this.presupuestoses = presupuestoses;
       this.historialeses = historialeses;
    }
   
    public Integer getClaveCliente() {
        return this.claveCliente;
    }
    
    public void setClaveCliente(Integer claveCliente) {
        this.claveCliente = claveCliente;
    }
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public boolean isActivo() {
        return this.activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public boolean isTipoCliente() {
        return this.tipoCliente;
    }
    
    public void setTipoCliente(boolean tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public Set<Presupuestos> getPresupuestoses() {
        return this.presupuestoses;
    }
    
    public void setPresupuestoses(Set<Presupuestos> presupuestoses) {
        this.presupuestoses = presupuestoses;
    }
    public Set<Historiales> getHistorialeses() {
        return this.historialeses;
    }
    
    public void setHistorialeses(Set<Historiales> historialeses) {
        this.historialeses = historialeses;
    }




}

