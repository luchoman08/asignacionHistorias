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
public class Persona extends Caracteristica{
    public String nombre;
    public ArrayList<Double> caracteristicas;
    
    public Persona(){
        this.nombre = "";
        this.caracteristicas = new ArrayList<>();
    }
}
