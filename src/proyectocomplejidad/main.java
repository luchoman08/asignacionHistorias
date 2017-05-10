/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocomplejidad;

import java.io.FileNotFoundException;
import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LinearProgram;

/**
 *
 * @author invitado
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here


        ProyectoComplejidad proyecto = new ProyectoComplejidad();
        proyecto.leerPedidos("pedidos.txt");
        proyecto.printEntrada();
        proyecto.crearFuncionObjetivo(proyecto.getTotalArticulosSolicitados());
        proyecto.adicionarRestricciones(proyecto.getTotalArticulosSolicitados());
        System.out.println(proyecto.obtenerResultado());
    }
}
