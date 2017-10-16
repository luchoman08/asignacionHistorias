/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorSCP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LinearProgram;
import java.util.Scanner;
import Modelos.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import scpsolver.constraints.LinearEqualsConstraint;
/**
 *
 * @author invitado
 */
/*    
LinearProgram lp = new LinearProgram(new double[]{5.0,10.0}); 
lp.addConstraint(new LinearBiggerThanEqualsConstraint(new double[]{3.0,1.0}, 8.0, "c1")); 
lp.addConstraint(new LinearBiggerThanEqualsConstraint(new double[]{0.0,4.0}, 4.0, "c2")); 
lp.addConstraint(new LinearSmallerThanEqualsConstraint(new double[]{2.0,0.0}, 2.0, "c3")); 
lp.setMinProblem(true); 
LinearProgramSolver solver  = SolverFactory.newDefault(); 
double[] sol = solver.solve(lp);
         System.out.println(sol);
 */
public class ProyectoAsignacionDesarrolladores {

    public Proyecto proyecto;
    LinearProgram linearProgram;
    LinearProgramSolver solver;
    
    public void printMatrix(double[][] matrix){
    for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
    }
    



    public ProyectoAsignacionDesarrolladores() {
        this.linearProgram = new LinearProgram();
        this.solver = SolverFactory.newDefault();
        this.proyecto = new Proyecto();
    }

     public BufferedReader getBuffered(String link){

    FileReader lector  = null;
    BufferedReader br = null;
    try {
         File Arch=new File(link);
        if(!Arch.exists()){
           System.out.println("No existe el archivo");
        }else{
           lector = new FileReader(link);
           br = new BufferedReader(lector);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return br;
}
    public ArrayList<Double> convertirArrayStringADouble (ArrayList<String> arrString){
        ArrayList<Double> arrDouble = new ArrayList<>();
        for(String item: arrString){
            arrDouble.add(Double.parseDouble(item));
        }
        return arrDouble;
    } 
    public void leerPedidos(String nombreArchivo) throws FileNotFoundException, IOException {
               //ruta de tu archivo
        BufferedReader br = getBuffered(nombreArchivo);
        String auxiliarSplitString[];  //auxiliar para hacer split a los string
        ArrayList<Double> auxiliar_caracteristicas;
        Persona persona = new Persona(); //auxiliar persona de usos varios
        Historia historia =  new Historia(); //auxiliar historia de usos varios
        //leemos la primera linea
        String linea =  br.readLine();
        
        this.proyecto.numeroDesarroladores = Integer.parseInt(linea);
        System.out.println(this.proyecto.numeroDesarroladores);
         linea =  br.readLine();
        this.proyecto.numeroHistorias = Integer.parseInt(linea);
        System.out.println(this.proyecto.numeroHistorias);
        this.proyecto.init();
        for(int i = 0 ; i < this.proyecto.numeroDesarroladores ; i++){
             linea =  br.readLine();
            
             auxiliarSplitString = linea.split(" ");
             persona.caracteristicas = convertirArrayStringADouble(new ArrayList<>(Arrays.asList(auxiliarSplitString)));
             this.proyecto.desarrolladores.add(persona);
             persona = new Persona();
        }
        
        linea =  br.readLine();
         for(int i = 0 ; i < this.proyecto.numeroHistorias ; i++){
             linea =  br.readLine();
             auxiliarSplitString = linea.split(" ");
             historia.caracteristicas = convertirArrayStringADouble(new ArrayList<>(Arrays.asList(auxiliarSplitString)));
             proyecto.historias.add(historia);
             historia= new Historia();
        }
         this.proyecto.calcularHabilidadPromedio();
         this.proyecto.calcularCostoPromedio();
    }
    
    /**
     *
     * @param cantidadArticulos
     */
    public void traducirResultados(double[] resultados){
        
        
        
        for (int i = 0; i < this.proyecto.numeroDesarroladores; i++) {
            for(int j = 0; j < this.proyecto.numeroHistorias; j++){
                
                if(resultados[i*this.proyecto.numeroHistorias + j]==1)
               System.out.println("Al desarrollador " + i + " se le ha asignado la historia " + j);    
            }
            
        }
    }
    
    public double[] obtenerResultado(){
        LinearProgramSolver solver  = SolverFactory.newDefault(); 
        double[] sol = solver.solve(this.linearProgram);
        return sol;
    }
    
    public void adicionarRestricciones() {
        /*
        //restricciones de equilibrio la puntuacion de historias (puede haber desequilibrio en numero de historias)
        double[] costos = this.proyecto.retornarArrayCostos();
        double[] coeficientesRestriccion = new double[this.proyecto.numeroDesarroladores * this.proyecto.numeroHistorias];
        for (int i = 0; i < this.proyecto.numeroDesarroladores; i++) {
            coeficientesRestriccion = new double[this.proyecto.numeroDesarroladores * this.proyecto.numeroHistorias];
            for (int j = 0; j < this.proyecto.numeroHistorias; j++) {
                coeficientesRestriccion[i * this.proyecto.numeroHistorias + j] = -1 * costos[i * this.proyecto.numeroHistorias + j];
            }
           System.out.println(Arrays.toString(coeficientesRestriccion));
           // System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientesRestriccion, this.proyecto.costoPromedio, "c" + i));

        }
*/
        
       //se adicionan las restricciones de equilibrio en el numero de historias
       
        double[]  coeficientesRestriccion = new double[this.proyecto.numeroDesarroladores * this.proyecto.numeroHistorias];
        for (int i = 0; i < this.proyecto.numeroDesarroladores; i++) {
            coeficientesRestriccion = new double[this.proyecto.numeroDesarroladores * this.proyecto.numeroHistorias];
            for (int j = 0; j < this.proyecto.numeroHistorias; j++) {
                coeficientesRestriccion[i * this.proyecto.numeroHistorias + j] = 1;
            }
           // System.out.println(Arrays.toString(coeficientesRestriccion));
           // System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientesRestriccion, Math.ceil((double) this.proyecto.numeroHistorias/(double) this.proyecto.numeroDesarroladores), "c" + i));

        }
        coeficientesRestriccion = new double[this.proyecto.numeroDesarroladores * this.proyecto.numeroHistorias];
        for (int i = 0; i < this.proyecto.numeroHistorias; i++) {
            coeficientesRestriccion = new double[this.proyecto.numeroDesarroladores * this.proyecto.numeroHistorias];
            for (int j = 0; j <this.proyecto.numeroDesarroladores ; j++) {
                coeficientesRestriccion[i + this.proyecto.numeroHistorias * j] = 1;
            }
          //  System.out.println(Arrays.toString(coeficientesRestriccion));
           // System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearEqualsConstraint(coeficientesRestriccion,1, "c" + i));
          
        }
        
        // se adicionan las restricciones de una unica asignacion de historia (una historia no puede ser 
        //respuesta por dos desarrolladores
        
      /*

        // se adicionan las restricciones para asegurar que no se supera capacidad de los contenedores
        double[] coeficientesRestriccion = new double[cantidadVariables];
        for (int i = 0; i < mejorNEncontrado; i++) {
            coeficientesRestriccion = new double[cantidadVariables];
            for (int j = 0; j < cantidadPedidos(); j++) {
                coeficientesRestriccion[i * cantidadPedidos() + j] = this.pedidos.get(j).tamanioUnidad;
            }
           // System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientesRestriccion, this.capacidadContenedores, "c" + i));

        }
        // se adicionan las restriciones para asegurar que todos los pedidos           se cumplan
        for (int i = 0; i < cantidadPedidos() ; i++) {
            coeficientesRestriccion = new double[cantidadVariables];
            for (int j = 0; j < mejorNEncontrado; j++) {
                coeficientesRestriccion[i  + j * cantidadPedidos()] = 1;
            }
         //   System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientesRestriccion, this.pedidos.get(i).cantidadUnidades, "c" + i));
            this.linearProgram.addConstraint(new LinearBiggerThanEqualsConstraint(coeficientesRestriccion, this.pedidos.get(i).cantidadUnidades, "c" + i));
            
        }
        
       
        coeficientesRestriccion = new double[cantidadVariables];
        for (int i = 0; i < cantidadVariables; i++) {
          coeficientesRestriccion[i] = 1;
        }
        
        this.linearProgram.addConstraint(new LinearBiggerThanEqualsConstraint(coeficientesRestriccion, 0, "positivos inc el 0"));
        
        */

    }

    /**
     * @param mejorNEncontrado Una cantidad n de contenedores que soluciona al
     * problea Preferiblemente deberia ser un n lo mas cercano posible a la
     * solucion optima
     *
     */
    public void crearFuncionObjetivo() {
        
        double[] coeficientesFuncionObjetivo = this.proyecto.retornarArrayCostos();
         double[] lowerBound = new double[coeficientesFuncionObjetivo.length];
        double[] upperBound = new double[coeficientesFuncionObjetivo.length];
        boolean[] restriccionTodosEnteros = new boolean[coeficientesFuncionObjetivo.length];
         linearProgram = new LinearProgram(coeficientesFuncionObjetivo);
         for(int i = 0; i < coeficientesFuncionObjetivo.length; i++){
             lowerBound[i] = 0;
             upperBound[i] = 1;
             restriccionTodosEnteros[i] = true;
         }
         System.out.println(coeficientesFuncionObjetivo.length);
        linearProgram.setIsinteger(restriccionTodosEnteros);
       

        linearProgram.setLowerbound(lowerBound);
        linearProgram.setUpperbound(upperBound);
       /* double[] coeficientesFuncionObjetivo = new double[cantidadPedidos() * mejorNEncontrado];
        double[] lowerBound = new double[cantidadPedidos() * mejorNEncontrado];
        boolean[] restriccionTodosEnteros = new boolean[cantidadPedidos() * mejorNEncontrado];
        for (int i = 0; i < coeficientesFuncionObjetivo.length; i ++) {
            coeficientesFuncionObjetivo[i] = -( ((int) Math.floor(i / cantidadPedidos()) ) + 1);
            
            restriccionTodosEnteros[i] = true;
             lowerBound[i] = 0;
        }
        
        System.out.println(Arrays.toString(coeficientesFuncionObjetivo));

      //  System.out.println(Arrays.toString(coeficientesFuncionObjetivo));
      //la funcion objetivo es minimizar el uso de contenedores 
        linearProgram = new LinearProgram(coeficientesFuncionObjetivo);
        linearProgram.setIsinteger(restriccionTodosEnteros);
        linearProgram.setLowerbound(lowerBound);*/

        //cada 
    }

}
