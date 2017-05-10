/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocomplejidad;

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
public class ProyectoComplejidad {

    /**
     * @return the totalArticulosSolicitados
     */
    public int getTotalArticulosSolicitados() {
        return totalArticulosSolicitados;
    }

    /**
     * @param totalArticulosSolicitados the totalArticulosSolicitados to set
     */
    public void setTotalArticulosSolicitados(int totalArticulosSolicitados) {
        this.totalArticulosSolicitados = totalArticulosSolicitados;
    }

    private int capacidadContenedores;
    private int totalArticulosSolicitados;
    private ArrayList<Pedido> pedidos;
    LinearProgram linearProgram;
    LinearProgramSolver solver;

    public int cantidadPedidos() {
        return this.pedidos.size();
    }

    public ProyectoComplejidad() {
        this.pedidos = new ArrayList<>();
        this.capacidadContenedores = 0;
        this.linearProgram = new LinearProgram();
        this.solver = SolverFactory.newDefault();
        this.totalArticulosSolicitados = 0;
    }

    public void leerPedidos(String nombreArchivo) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(nombreArchivo));
        Pedido pedido = new Pedido(0, 0);
        this.setCapacidadContenedores(sc.nextInt());
        for (int i = 0; sc.hasNextInt(); i++) {

            if (i % 2 == 0) {
                pedido.cantidadUnidades = sc.nextInt();
                this.setTotalArticulosSolicitados(this.getTotalArticulosSolicitados() + pedido.cantidadUnidades);
            } else {
                pedido.tamanioUnidad = sc.nextInt();
                this.pedidos.add(pedido);
                pedido = new Pedido(0, 0);
            }

        }

    }
    
    public double[] obtenerResultado(){
        LinearProgramSolver solver  = SolverFactory.newDefault(); 
        double[] sol = solver.solve(this.linearProgram);
        return sol;
    }
    
    public void adicionarRestricciones(int mejorNEncontrado) {
        int cantidadVariables = cantidadPedidos() * mejorNEncontrado;

        /* se adicionan las restricciones para asegurar que no se supera
           la capacidad de los contenedores*/
        double[] coeficientesRestriccion = new double[cantidadVariables];
        for (int i = 0; i < mejorNEncontrado; i++) {
            coeficientesRestriccion = new double[cantidadVariables];
            for (int j = 0; j < cantidadPedidos(); j++) {
                coeficientesRestriccion[i * cantidadPedidos() + j] = this.pedidos.get(j).tamanioUnidad;
            }
            System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientesRestriccion, this.capacidadContenedores, "c" + i));

        }
        /* se adicionan las restriciones para asegurar que todos los pedidos
           se cumplan*/
        for (int i = 0; i < cantidadPedidos() ; i++) {
            coeficientesRestriccion = new double[cantidadVariables];
            for (int j = 0; j < mejorNEncontrado; j++) {
                coeficientesRestriccion[i  + j * cantidadPedidos()] = 1;
            }
            System.out.println(Arrays.toString(coeficientesRestriccion));
            this.linearProgram.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientesRestriccion, this.pedidos.get(i).cantidadUnidades, "d" + i));
            this.linearProgram.addConstraint(new LinearBiggerThanEqualsConstraint(coeficientesRestriccion, this.pedidos.get(i).cantidadUnidades, "d" + i));
        }

    }

    /**
     * @param mejorNEncontrado Una cantidad n de contenedores que soluciona al
     * problea Preferiblemente deberia ser un n lo mas cercano posible a la
     * solucion optima
     *
     */
    public void crearFuncionObjetivo(int mejorNEncontrado) {
        double[] coeficientesFuncionObjetivo = new double[cantidadPedidos() * mejorNEncontrado];
        for (int i = 0; i < coeficientesFuncionObjetivo.length; i ++) {
            coeficientesFuncionObjetivo[i] = 1;
        }
        System.out.println(Arrays.toString(coeficientesFuncionObjetivo));
        linearProgram = new LinearProgram(coeficientesFuncionObjetivo);
    }

    public void printEntrada() {
        System.out.println(capacidadContenedores);
        for (Pedido pedido : this.pedidos) {
            System.out.println(pedido.cantidadUnidades + " " + pedido.tamanioUnidad);
        }
    }

    /**
     * @return the capacidadContenedores
     */
    public int getCapacidadContenedores() {
        return capacidadContenedores;
    }

    /**
     * @param capacidadContenedores the capacidadContenedores to set
     */
    public void setCapacidadContenedores(int capacidadContenedores) {
        this.capacidadContenedores = capacidadContenedores;
    }

    /**
     * @return the pedidos
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
