/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;

/**
 *
 * @author lucho
 */
public class Proyecto {
    public ArrayList<Historia> historias;
    public ArrayList<Persona> desarrolladores;
    /** 
     * Define las caracteristicas que se quieren observar de cada desarrollador
     * y de cada historia, ej: ComponenteUX, ComponenteJavaScript etc 
     */
    public ArrayList<Caracteristica> caracteristicasObservadas; 
    public int numeroDesarroladores;
    public int numeroHistorias;
    public int costoPromedio;
    public double habilidadPromedio;
    public double[][] matriz_costos;
    public int numero_caracteristicas;
    public void init() {
        this.historias = new ArrayList<>();
        this.desarrolladores = new ArrayList<>();
        matriz_costos = new double[numeroDesarroladores][numeroHistorias];
        this.costoPromedio = 0;
        this.habilidadPromedio = 0;
        this.calcularHabilidadPromedio();
        this.calcularCostoPromedio();
    }
    public Proyecto(){
        this.numero_caracteristicas = 6;
        this.historias = new ArrayList<>();
        this.desarrolladores = new ArrayList<>();
        this.costoPromedio = 0;
        this.habilidadPromedio = 0;
    }
    public double costoDesarrolladorHistoria(Persona desarrollador, Historia historia){
        double costo = 0;
        for( int i = 0; i < desarrollador.caracteristicas.size(); i++){
            costo+= historia.caracteristicas.get(i) / desarrollador.caracteristicas.get(i);
            
        }
        return costo;
    }
    public void calcularHabilidadPromedio(){
        for(int i = 0; i < this.desarrolladores.size(); i++){
            for(int j = 0; j < this.desarrolladores.get(i).caracteristicas.size(); j++){
                this.habilidadPromedio+= desarrolladores.get(i).caracteristicas.get(j);
            }
        }
        this.habilidadPromedio =
                this.habilidadPromedio /
                (this.numeroDesarroladores*this.numero_caracteristicas);
        
    }
    public void calcularCostoPromedio(){
        for(int i = 0; i < this.historias.size(); i++){
            for(int j = 0; j < this.historias.get(i).caracteristicas.size(); j++){
                this.costoPromedio+= historias.get(i).caracteristicas.get(j)/this.habilidadPromedio;
                        
            }
        }
        this.costoPromedio = this.costoPromedio/this.numeroDesarroladores;
            
    }
    public void inicializarCostos(){
        for(int i = 0; i < numeroDesarroladores; i++){
            for(int j = 0; j < numeroHistorias; j++){
                matriz_costos[i][j] = costoDesarrolladorHistoria(desarrolladores.get(i), historias.get(j));
            }
        }
    }
    
    public double[] retornarArrayCostos(){
        double[] arrayCostos = new double [this.numeroDesarroladores*this.numeroHistorias];
        for(int i = 0; i < numeroDesarroladores; i++){
            for(int j = 0; j < numeroHistorias; j++){
                arrayCostos[i*numeroHistorias + j] = - 1 *  matriz_costos[i][j];
            }
            }
        return arrayCostos;
    }
    
    
           
}
