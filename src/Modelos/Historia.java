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
public class Historia extends Caracteristica{
    public String nombre;
    public ArrayList<Double> caracteristicas;
    
    public Historia(){
        this.caracteristicas = new ArrayList<>();
        this.nombre = "";
    }
}
